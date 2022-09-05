package com.snake2d.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

/**
 * klasa reprezentująca ekran rozgrywki
 */
public class GameScreen implements Screen {

    private SpriteBatch spriteBatch;

    private Snake2D myGame;
    private Stage miniMenuStage, endScreenStage;

    private VerticalGroup backgroundGroup, backgroundGroup2;
    private Table miniMenuMainTable, endScreenTable;

    BitmapFont firstSnakePointsLabel, secondSnakePointsLabel;

    private TextButton continueButton, exitButton, endGameButton;


    private boolean isMiniMenuVisible;

    GameScreen(Snake2D game){
        this.myGame = game;
        miniMenuStage = new Stage();
        endScreenStage = new Stage();
        //Gdx.input.setInputProcessor(miniMenuStage);

        firstSnakePointsLabel = FontManager.getInstance().getNewBigFont();
        secondSnakePointsLabel = FontManager.getInstance().getNewBigFont();

        setLabelColorBasedOnSnakeType(firstSnakePointsLabel, myGame.getFirstSnake().getSnakeTextureType());

        if(myGame.getGamemode() == Gamemode.TWO_PLAYERS){
            setLabelColorBasedOnSnakeType(secondSnakePointsLabel, myGame.getSecondSnake().getSnakeTextureType());
        }


        isMiniMenuVisible = false;

        //buttons initialization
        initializeContinueButton("Continue", ButtonStyle.NORMAL);
        initializeExitButton("Exit", ButtonStyle.NORMAL);
        initializeEndGameButton("Exit", ButtonStyle.NORMAL);

        //group with background texture
        backgroundGroup = new VerticalGroup();
        backgroundGroup.setFillParent(true);
        backgroundGroup.align(Align.center);
        backgroundGroup.addActor(new Image(Textures.getInstance().getMiniMenuBackground()));


        backgroundGroup2 = new VerticalGroup();
        backgroundGroup2.setFillParent(true);
        backgroundGroup2.align(Align.center);
        backgroundGroup2.addActor(new Image(Textures.getInstance().getMiniMenuBackground()));


        //miniMenuStage main table
        miniMenuMainTable = new Table();
        miniMenuMainTable.setFillParent(true);
        miniMenuMainTable.align(Align.center);
        miniMenuMainTable.add(continueButton).space(20);
        miniMenuMainTable.row();
        miniMenuMainTable.add(exitButton);

        //adding element to miniMenuStage
        miniMenuStage.addActor(backgroundGroup);
        miniMenuStage.addActor(miniMenuMainTable);

        //end screen table
        endScreenTable = new Table();
        endScreenTable.setFillParent(true);
        endScreenTable.align(Align.center);
        endScreenTable.add(new Image(Textures.getInstance().getGameOverLabel())).space(40);
        endScreenTable.row();
        endScreenTable.add(endGameButton);

        endScreenStage.addActor(backgroundGroup2);
        endScreenStage.addActor(endScreenTable);


    }

    /**
     * metoda ustawiająca kolor napisu informującego o punktach zdobytych przez węża w zależności od jego koloru. Jako parametr przyjmuje obiekt BitmapFont odpowiedzialny za wyświetlanie informacji oraz typ tekstur węża
     * @param font
     * @param type
     */

    private void setLabelColorBasedOnSnakeType(BitmapFont font, SnakeTextureType type){

        switch (type){
            case BLUE:
                font.setColor(Color.BLUE);
                break;
            case GREEN:
                font.setColor(Color.GREEN);
                break;
            case PURPLE:
                font.setColor(Color.PINK);
                break;
            case RED:
                font.setColor(Color.RED);
                break;
            case BLACK:
                font.setColor(Color.BLACK);
                break;
            case COLORFUL:
            default:
                font.setColor(Color.WHITE);
                break;
        }
    }

    /**
     * metoda odpowiedzialna za inicjalizację przycisku „ContinueButton”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */
    private void initializeContinueButton(String caption, ButtonStyle style){
        ButtonFactory buttonFactory = new ButtonFactory();

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                isMiniMenuVisible = false;
                myGame.unpauseGame();
            }
        };

        continueButton = buttonFactory.getTextButton(caption, style, 20, 600, changeListener);
    }

    /**
     * metoda odpowiedzialna za inicjalizację przycisku „ExitButton”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */
    private void initializeExitButton(String caption, ButtonStyle style){
        ButtonFactory buttonFactory = new ButtonFactory();

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                myGame.setScreen(new MenuScreen(myGame));
            }
        };

        exitButton = buttonFactory.getTextButton(caption, style, 20, 600, changeListener);
    }

    /**
     * metoda odpowiedzialna za inicjalizację przycisku „EndGameButton”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */
    private void initializeEndGameButton(String caption, ButtonStyle style){
        ButtonFactory buttonFactory = new ButtonFactory();

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                myGame.setScreen(new MenuScreen(myGame));
            }
        };

        endGameButton = buttonFactory.getTextButton(caption, style, 20, 600, changeListener);;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
    }

    /**
     * metoda odpowiedzialna za rysowanie ekranu rozgrywki, jako parametr przyjmuje czas pomiędzy klatkami gry
     * @param delta
     */
    @Override
    public void render(float delta) {
        if(!myGame.isGameOver()) {
            myGame.updateGame();
        }
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && !myGame.isGameOver()){
            isMiniMenuVisible = !isMiniMenuVisible;
            if(isMiniMenuVisible){
                myGame.pauseGame();
            } else{
                myGame.unpauseGame();
            }
        }


        spriteBatch.begin();


        myGame.drawBackground(spriteBatch);
        myGame.drawFood(spriteBatch);
        myGame.drawObstacle(spriteBatch);
        myGame.drawSnakes(spriteBatch);

        if(myGame.getGamemode() == Gamemode.SINGLE_PLAYER) {
            firstSnakePointsLabel.draw(spriteBatch, "Points: " + myGame.getFirstSnake().getPoints(),
                    50, Gdx.graphics.getHeight() - 50);
        }else{
            firstSnakePointsLabel.draw(spriteBatch, "Snake " + myGame.getFirstSnake().getSnakeTextureType().name() + ": "
                            + myGame.getFirstSnake().getPoints(),
                    50, Gdx.graphics.getHeight() - 50);

            secondSnakePointsLabel.draw(spriteBatch, "Snake " + myGame.getSecondSnake().getSnakeTextureType().name() + ": "
                            + myGame.getSecondSnake().getPoints(),
                    50, Gdx.graphics.getHeight() - 100);
        }


        spriteBatch.end();

        if(isMiniMenuVisible){
            Gdx.input.setInputProcessor(miniMenuStage);
            miniMenuStage.draw();
            miniMenuStage.act();
        }

        if(myGame.isGameOver()){
            Gdx.input.setInputProcessor(endScreenStage);
            endScreenStage.draw();
            endScreenStage.act();
        }

    }


    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        myGame.dispose();
    }
}
