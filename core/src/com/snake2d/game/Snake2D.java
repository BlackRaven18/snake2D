package com.snake2d.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum Gamemode { SINGLE_PLAYER, TWO_PLAYERS}


/**
 * klasa główna gry implementująca interfejs Game
 */
public class Snake2D extends Game {

	private final int SNAKE_START_X = 200;
	private final int SNAKE_START_Y = 520;

	private final int SECOND_SNAKE_START_X = 200;
	private final int SECOND_SNAKE_START_Y = 240;

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


	/**
	 * metoda inicjująca, pozwalająca na inicjalizacje podstawowych zmiennych w grze
	 */
	@Override
	public void create () {

		this.batch = new SpriteBatch();
		this.gamemode = Gamemode.SINGLE_PLAYER;

		this.isGamePaused = false;

		this.textures = Textures.getInstance();

		this.snakeList = new ArrayList<>();

		createSnakes();

		food = new Food(textures.getFoodImg());
		obstacle = new Obstacle(textures.getObstacleImg(), NUMBER_OF_OBSTACLES);

		initializeNewGame();

		// seting menu as first screen
		this.setScreen(new MenuScreen(this));
	}

	/**
	 * metoda pozwalajaca na zmianę trybu gry
	 */
	public void changeGamemode(Gamemode gamemode){
		this.gamemode = gamemode;
		initializeNewGame();
	}

	/**
	 * metoda odpowiedzialna za utworzenie węży w zależności od wybranego trybu i parametrów rozgrywki
	 */

	void createSnakes(){
		this.snake = new Snake(SnakeTextureType.BLUE);

		this.snakeList.clear();
		this.snakeList.add(snake);

		// if two players mode
		if(gamemode == Gamemode.TWO_PLAYERS) {
			secondSnake = new Snake(SnakeTextureType.COLORFUL,
					Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D);
			snakeList.add(secondSnake);
		}
	}


	/**
	 * metoda zajmująca się się inicjalizacją podstawowych parametrów gry
	 */

	private void initializeNewGame(){
		obstacle.getObstaclePositions().clear();
		snakeList.clear();
		createSnakes();

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

	/**
	 * metoda pomocnicza zwracająca obiekt będący pierwszym wężem
	 */
	public Snake getFirstSnake(){
		if(snakeList.size() > 0)
			return snakeList.get(0);
		return null;
	}

	/**
	 * metoda pomocnicza zwracająca obiekt będący drugim wężem
	 */

	public Snake getSecondSnake(){
		if(snakeList.size() > 1)
			return snakeList.get(1);
		return null;
	}

	/**
	 * metoda pozwalająca na zmianę tekstur pierwszego węża
	 */

	public void reloadFirstSnake(SnakeTextureType type){
		getFirstSnake().changeSnakeTextures(type);
	}

	/**
	 * metoda pozwalająca na zmianę tekstur drugiego węża
	 */

	public void reloadSecondSnake(SnakeTextureType type){
		if(gamemode == Gamemode.TWO_PLAYERS) {
			getSecondSnake().changeSnakeTextures(type);
		}
	}

	/**
	 * metoda odpowiedzialna za generowanie przeszkód na mapie
	 */

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

	/**
	 * metoda odpowiedzialna za generowanie pożywienia na mapie
	 */

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

	/**
	 * metoda pomocnicza zwracająca listę punktów, powstałą z połączenia dwóch list punktów
	 */

	private List<GridPoint2> mergeTwoGridPoint2List(List<GridPoint2> list1, List<GridPoint2> list2){
		List<GridPoint2> mergedList = new ArrayList<>();
		mergedList.addAll(list1);
		mergedList.addAll(list2);
		return mergedList;
	}

	/**
	 * metoda zwracająca listę punktów zajmowanych przez węże
	 */
	private List<GridPoint2> getTwoSnakesPositions(){
		List<GridPoint2> mergedSnakesPotisions = new ArrayList<>();
		mergedSnakesPotisions.addAll(Objects.requireNonNull(getFirstSnake()).getSnakeParts());
		mergedSnakesPotisions.addAll(Objects.requireNonNull(getSecondSnake()).getSnakeParts());
		return mergedSnakesPotisions;
	}

	/**
	 * metoda odpowiedzialna za rysowanie tła, przyjmuje obiekt SpriteBatch jako parametr, który używany jest do rysowania tekstur
	 */
	public void drawBackground(SpriteBatch batch){
		batch.draw(textures.getBackground(), 0, 0);
	}

	/**
	 * metoda odpowiedzialna za rysowanie węży, również przyjmuje obiekt SpriteBatch jako parametr
	 */

	 public void drawSnakes(SpriteBatch batch){
		if(secondSnakeHitOthersnake){
			drawSnakesNormal(batch);
		}else {
			drawSnakesReverse(batch);
		}
	}

	/**
	 * metoda pomocnicza, która pierwszego węża rysuje „nad” drugim, przyjmuje obiekt SpriteBatch jako parametr
	 */

	private void drawSnakesNormal(SpriteBatch batch){
		for(Snake snake : snakeList){
			snake.draw(batch);
		}
	}


	/**
	 * metoda pomocnicza, która rysuje drugiego węża „nad” pierwszym, przyjmuje obiekt SpriteBatch jako parametr
	 */

	private void drawSnakesReverse(SpriteBatch batch){
		for(int i = snakeList.size() - 1; i >= 0; i--){
			snakeList.get(i).draw(batch);
		}
	}

	/**
	 * metoda odpowiedzialna za rysowanie pożywienia na mapie, , przyjmuje obiekt SpriteBatch jako parametr
	 */

	public void drawFood(SpriteBatch batch){
		food.draw(batch);
	}

	/**
	 *metoda odpowiedzialna za rysowanie przeszkód na mapie, , przyjmuje obiekt SpriteBatch jako parametr
	 */

	public void drawObstacle(SpriteBatch batch){
		obstacle.draw(batch);
	}

	/**
	 * metoda umożliwiająca wstrzymanie rozgrywki
	 */

	public void pauseGame(){
		isGamePaused = true;
	}

	/**
	 * metoda umożliwiająca kontunuowanie rozgrywki po jej wstrzymaniu
	 */

	public void unpauseGame(){
		isGamePaused = false;
	}

	/**
	 * metoda obsługująca logikę gry
	 */

	public void updateGame(){

		if(gamemode == Gamemode.TWO_PLAYERS){
			if(checkCollisionWithSnakes()){
				SoundsManager.getInstance().playLoseSound();
				gameOver = true;
			}
		}

		if(!gameOver && !isGamePaused){


			// doing things of every snake on the list
			for(Snake snake : snakeList) {
				snake.act(Gdx.graphics.getDeltaTime());


				if (snake.isFoodFound(food.getPosition())) {
					SoundsManager.getInstance().playEatFoodSound();
					snake.extendSnake();
					generateFood();
					snake.addPoint();
				}

				if (snake.hasHitHimself() || snake.hasHitObstacle(obstacle.getObstaclePositions())) {
					SoundsManager.getInstance().playLoseSound();
					gameOver = true;
				}
			}
		}else {
			if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
				initializeNewGame();
			}
		}
	}

	/**
	 * metoda sprawdza, czy wystąpiły kolizje pomiędzy wężami. Jeżeli tak to zwraca true, w przeciwny wypadku zwraca false
	 * @return wartosc logiczna
	 */

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

	/**
	 * metoda pozwalająca na usunięcie niepotrzebnych obiektów
	 */

	
	@Override
	public void dispose () {
		batch.dispose();
		textures.disposeTextures();
	}

	/**
	 * metoda zwraca true, jeżeli rozgrywka się zakończyła lub false, jeżeli rozgrywka nadal jest w toku
	 */

	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * metoda zwracająca aktualny tryb gry
	 */

	public Gamemode getGamemode() {
		return gamemode;
	}
}
