package com.snake2d.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum Gamemode { SINGLE_PLAYER, TWO_PLAYERS}

@SuppressWarnings("FieldCanBeLocal")
public class Snake2D extends ApplicationAdapter {

	private final int SNAKE_START_X = 210;
	private final int SNAKE_START_Y = 245;

	private final int SECOND_SNAKE_START_X = 210;
	private final int SECOND_SNAKE_START_Y = 350;

	private final int NUMBER_OF_OBSTACLES = 5;

	private SpriteBatch batch;

	private List<Snake> snakeList;

	private Gamemode gamemode;

	private Textures textures;
	private Snake snake;
	private Snake secondSnake;
	private Food food;
	private Obstacle obstacle;

	private boolean gameOver;
	private boolean isGamePaused;
	private boolean secondSnakeHitOthersnake;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		gamemode = Gamemode.SINGLE_PLAYER;

		isGamePaused = false;

		textures = new Textures();


		snake = new Snake(textures, SnakeTextureType.GREEN);

		snakeList = new ArrayList<>();
		snakeList.add(snake);

		// if two players mode
		if(gamemode == Gamemode.TWO_PLAYERS) {
			secondSnake = new Snake(textures, SnakeTextureType.COLORFUL,
					Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D);
			snakeList.add(secondSnake);
		}

		food = new Food(textures.getFoodImg());
		obstacle = new Obstacle(textures.getObstacleImg(), NUMBER_OF_OBSTACLES);

		initializeNewGame();
	}



	private void initializeNewGame(){
		obstacle.getObstaclePositions().clear();
		snakeList.get(0).initialize(SNAKE_START_X, SNAKE_START_Y);

		// if two players mode
		if(gamemode == Gamemode.TWO_PLAYERS) {
			snakeList.get(1).initialize(SECOND_SNAKE_START_X, SECOND_SNAKE_START_Y);
		}

		gameOver = false;
		isGamePaused = false;
		secondSnakeHitOthersnake = false;
		generateObstacle();
		generateFood();
	}

	private Snake getFirstSnake(){
		if(snakeList.size() > 0)
			return snakeList.get(0);
		return null;
	}

	private Snake getSecondSnake(){
		if(snakeList.size() > 1)
			return snakeList.get(1);
		return null;
	}

	private void generateObstacle(){
		try{
			if(gamemode == Gamemode.SINGLE_PLAYER) {
				obstacle.generateObstaclePotisions(getFirstSnake().getSnakeSegmentsPositions());
			}
			else {
				obstacle.generateObstaclePotisions(getTwoSnakesPositions());
			}
		} catch(NoMorePlaceForObstacleException e) {
			gameOver = true;
		}
	}

	private void generateFood(){
		try{
			List<GridPoint2> occupiedPositions;

			if(gamemode == Gamemode.SINGLE_PLAYER) {
				occupiedPositions = mergeTwoGridPoint2List(getFirstSnake().getSnakeSegmentsPositions(),
						obstacle.getObstaclePositions());
			}
			else {
				occupiedPositions = mergeTwoGridPoint2List(getTwoSnakesPositions(), obstacle.getObstaclePositions());
			}

			food.randomizePosition(occupiedPositions);

		} catch(NoMorePlaceForFoodException e) {
			gameOver = true;
		}
	}

	private List<GridPoint2> mergeTwoGridPoint2List(List<GridPoint2> list1, List<GridPoint2> list2){
		List<GridPoint2> mergedList = new ArrayList<>();
		mergedList.addAll(list1);
		mergedList.addAll(list2);
		return mergedList;
	}


	private List<GridPoint2> getTwoSnakesPositions(){
		List<GridPoint2> mergedSnakesPotisions = new ArrayList<>();
		mergedSnakesPotisions.addAll(Objects.requireNonNull(getFirstSnake()).getSnakeParts());
		mergedSnakesPotisions.addAll(Objects.requireNonNull(getSecondSnake()).getSnakeParts());
		return mergedSnakesPotisions;
	}



	 void drawSnakes(SpriteBatch batch){
		if(secondSnakeHitOthersnake){
			drawSnakesNormal(batch);
		}else {
			drawSnakesReverse(batch);
		}
	}

	void drawSnakesNormal(SpriteBatch batch){
		for(Snake snake : snakeList){
			snake.draw(batch);
		}
	}

	void drawSnakesReverse(SpriteBatch batch){
		for(int i = snakeList.size() - 1; i >= 0; i--){
			snakeList.get(i).draw(batch);
		}
	}


	public void render () {
		updateGame();

		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.draw(textures.getBackground(), 0, 0);
		obstacle.draw(batch);
		food.draw(batch);

		//drawing Snake or Snakes
		drawSnakes(batch);

		batch.end();
	}

	//TODO changing resolution
	/*private void changeResolution(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.Y)){
			Gdx.graphics.setWindowedMode(1610,780);
		}
	}

	 */

	private void pauseGame(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
			isGamePaused = !isGamePaused;
		}
	}

	private void updateGame(){
		pauseGame();
		//changeResolution();

		if(gamemode == Gamemode.TWO_PLAYERS){
			if(checkCollisionWithSnakes()){
				gameOver = true;
			}
		}

		if(!gameOver && !isGamePaused){


			// doing things of every snake on the list
			for(Snake snake : snakeList) {
				snake.act(Gdx.graphics.getDeltaTime());


				if (snake.isFoodFound(food.getPosition())) {
					snake.extendSnake();
					generateFood();
				}

				if (snake.hasHitHimself() || snake.hasHitObstacle(obstacle.getObstaclePositions())) {
					gameOver = true;
				}
			}
		}else {
			if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
				initializeNewGame();
			}
		}
	}

	private boolean checkCollisionWithSnakes(){
		if(getFirstSnake().hasHitOtherSnake(getSecondSnake().getSnakeParts())) {
			return true;
		}

		// we are rising the secondSnakeHitOther snake flag to draw his head over the first snake body
		if (getSecondSnake().hasHitOtherSnake(getFirstSnake().getSnakeParts())) {
			secondSnakeHitOthersnake = true;
			return true;
		}

		return false;
	}



	
	@Override
	public void dispose () {
		batch.dispose();
		textures.disposeTextures();
	}


}
