package com.snake2d.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Snake2D extends ApplicationAdapter {

	private final int SNAKE_START_X = 210;
	private final int SNAKE_START_Y = 105;

	private final int SECOND_SNAKE_START_X = 210;
	private final int SECONT_SNAKE_START_Y = 350;

	private SpriteBatch batch;

	private Textures textures;
	private Snake snake;
	private Snake secondSnake;
	private Food food;
	private boolean gameOver;

	private boolean isGamePaused;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		isGamePaused = false;

		//loadTextures();
		textures = new Textures();
		snake = new Snake(textures);
		secondSnake = new Snake(textures, Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D);
		food = new Food(textures.getFoodImg());

		initializeNewGame();
	}

	private void generateFood(){
		try{
			food.randomizePosition(snake.getSnakeSegmentsPositions());
		} catch(NoMorePlaceForFood e) {
			gameOver = true;
		}
	}


	private void initializeNewGame(){
		snake.initialize(SNAKE_START_X, SNAKE_START_Y);
		secondSnake.initialize(SECOND_SNAKE_START_X, SECONT_SNAKE_START_Y);
		gameOver = false;

		generateFood();

		isGamePaused = false;
	}

	public void render () {
		updateGame();

		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.draw(textures.getBackground(), 0, 0);
		food.draw(batch);
		snake.draw(batch);
		secondSnake.draw(batch);

		batch.end();
	}

	private void pauseGame(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
			if(!isGamePaused){
				isGamePaused = true;
			} else {
				isGamePaused = false;
			}
		}
	}

	private void updateGame(){
		pauseGame();

		if(!gameOver && !isGamePaused){
			snake.act(Gdx.graphics.getDeltaTime());
			secondSnake.act(Gdx.graphics.getDeltaTime());

			if(snake.isFoodFound(food.getPosition())){
				snake.extendSnake();

				generateFood();
			}

			if(snake.hasHitHimself()){
				gameOver = true;
			}


		}else {
			if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
				initializeNewGame();
			}
		}
	}

	
	@Override
	public void dispose () {
		batch.dispose();

		textures.disposeTextures();
	}


}
