package com.snake2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

/**
 * klasa reprezentująca ekran pozwalający na dostosowanie parametrów rozgrywki w trybie dla jednego gracza
 */

public class SelectGamePropertiesForOnePlayerScreen implements Screen {

    private final Snake2D myGame;
    private final SpriteBatch spriteBatch;
    private final Textures textures;

    private final Stage stage;

    private final ButtonFactory buttonFactory;
    private TextButton returnButton, playButton;

    SnakeColorButtons snakeColorButtons;
    MapSelectButtons mapSelectButtons;


    private final VerticalGroup topVerticalGroup, bottomVerticalGroup;
    private final HorizontalGroup pickColorGroup, pickMapGroup;
    private final Table mainTable;

    SelectGamePropertiesForOnePlayerScreen(Snake2D game){
        //basic initialization
        myGame = game;

        //selecting GAMEMODE
        game.changeGamemode(Gamemode.SINGLE_PLAYER);

        spriteBatch = new SpriteBatch();
        textures = Textures.getInstance();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // buttons initalization
        buttonFactory = new ButtonFactory();

        initiateReturnButton("Return", ButtonStyle.SMALL);
        initializePlayButton("Play", ButtonStyle.NORMAL);

        snakeColorButtons = new SnakeColorButtons(myGame, WhichSnake.FIRST);
        mapSelectButtons = new MapSelectButtons(myGame);



        // group with return button initialization
        topVerticalGroup = new VerticalGroup();
        topVerticalGroup.setFillParent(true);
        topVerticalGroup.align(Align.topLeft).pad(20);
        topVerticalGroup.addActor(returnButton);

        // group with play button initialization
        bottomVerticalGroup = new VerticalGroup();
        bottomVerticalGroup.setFillParent(true);
        bottomVerticalGroup.align(Align.bottomRight).pad(20);
        bottomVerticalGroup.addActor(playButton);

        //group with color buttons initialization
        pickColorGroup = snakeColorButtons.getHorizontalGroupWithColorButtons();

        //group with map buttons initialization
        pickMapGroup = mapSelectButtons.getHorizontalGroupWithMapButtons();



        // adding children to the mainTable
        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.align(Align.center);
        Image image = new Image(Textures.getInstance().getChooseSnakeColorLabel());
        mainTable.add(image).row();
        mainTable.add(pickColorGroup).pad(50).row();
        Image secondImage = new Image(Textures.getInstance().getChooseMapLabel());
        mainTable.add(secondImage).pad(10).row();
        mainTable.add(pickMapGroup).pad(10).row();

        // adding elements to stage
        stage.addActor(topVerticalGroup);
        stage.addActor(mainTable);
        stage.addActor(bottomVerticalGroup);
    }

    /**
     * metoda odpowiedzialna za inicjalizację przycisku „Return”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */
    private void initiateReturnButton(String caption, ButtonStyle style){
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                myGame.setScreen(new SelectGameModeScreen(myGame));
            }
        };

        returnButton = buttonFactory.getTextButton(caption, style, 20, 600, changeListener);
    }

    /**
     * metoda odpowiedzialna za inicjalizację przycisku „Play”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */
    private void initializePlayButton(String caption, ButtonStyle style){
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                myGame.setScreen(new GameScreen(myGame));
            }
        };

        playButton = buttonFactory.getTextButton(caption, style, 20, 20, changeListener);
    }



    @Override
    public void show() {

    }

    /**
     * metoda odpowiedzialna za rysowanie ekranu wyboru parametrów gry w trybie dla jednego gracza, jako parametr przyjmuje czas pomiędzy klatkami gry
     * @param delta
     */
    @Override
    public void render(float delta) {
        spriteBatch.begin();
            spriteBatch.draw(textures.getMenuBackground(), 0, 0);
        spriteBatch.end();

        stage.draw();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
