package com.snake2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

/**
 * klasa reprezentująca ekran wyboru trybu gry
 */

public class SelectGameModeScreen implements Screen {

    private final Snake2D myGame;
    private final SpriteBatch spriteBatch;
    private final Textures textures;

    private final Stage stage;

    private final ButtonFactory buttonFactory;

    private Table mainTable, tableLeft, tableRight;

    private final HorizontalGroup horizontalGroup;
    private final VerticalGroup verticalGroup;
    private TextButton returnButton, onePlayerModeButton, twoPlayerModeButton;

    public SelectGameModeScreen(Snake2D game){
        myGame = game;
        spriteBatch = new SpriteBatch();
        textures = Textures.getInstance();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        buttonFactory = new ButtonFactory();

        verticalGroup = new VerticalGroup();
        horizontalGroup = new HorizontalGroup();

        //tableLeft = new Table().add()
        //stack = Stack;


        initializeReturnButton("Return", ButtonStyle.SMALL);
        initializeOnePlayerModeButton("One player", ButtonStyle.NORMAL);
        initializeTwoPlayerModeButton("Two players", ButtonStyle.NORMAL);

        mainTable = new Table();
        tableLeft = new Table();
        tableRight = new Table();

        tableLeft.add(onePlayerModeButton);
        tableRight.add(twoPlayerModeButton);

        horizontalGroup.addActor(tableLeft);
        horizontalGroup.addActor(tableRight);
        horizontalGroup.space(100);

        verticalGroup.setFillParent(true);
        verticalGroup.align(Align.topLeft);
        verticalGroup.addActor(returnButton);
        verticalGroup.pad(20);

        mainTable.setFillParent(true);
        mainTable.align(Align.center);
        mainTable.add(horizontalGroup);



        stage.addActor(verticalGroup);
        stage.addActor(mainTable);
    }

    /**
     * metoda odpowiedzialna za inicjalizację przycisku „ReturnButton”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */
    private void initializeReturnButton(String caption, ButtonStyle style){

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                myGame.setScreen(new MenuScreen(myGame));
            }
        };

        returnButton = buttonFactory.getTextButton(caption, style, 20, 600, changeListener);
    }

    /**
     * metoda odpowiedzialna za inicjalizację przycisku „OnePlayerModeButton”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */
    private void initializeOnePlayerModeButton(String caption, ButtonStyle style){

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                myGame.setScreen(new SelectGamePropertiesForOnePlayerScreen(myGame));
            }
        };

        onePlayerModeButton = buttonFactory.getTextButton(caption, style, 20, 600, changeListener);
    }

    /**
     * metoda odpowiedzialna za inicjalizację przycisku „TwoPlayerModeButton”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */

    private void initializeTwoPlayerModeButton(String caption, ButtonStyle style){

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                myGame.setScreen(new SelectGamePropertiesForTwoPlayersScreen(myGame));
            }
        };

        twoPlayerModeButton = buttonFactory.getTextButton(caption, style, 20, 600, changeListener);
    }




    @Override
    public void show() {

    }

    /**
     * metoda odpowiedzialna za rysowanie ekranu wyboru trybu gry, jako parametr przyjmuje czas pomiędzy klatkami gry
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

    }
}
