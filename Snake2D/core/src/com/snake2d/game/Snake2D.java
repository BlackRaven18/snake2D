package com.snake2d.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Snake2D extends ApplicationAdapter {
	private SpriteBatch batch;



	private Textures textures;
	private Snake snake;
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
		snake.initialize();
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
