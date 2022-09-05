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
 * klasa reprezentująca ekran pozwalający na dostosowanie parametrów rozgrywki w trybie dla dwóch graczy
 */

public class SelectGamePropertiesForTwoPlayersScreen implements Screen {

    private final Snake2D myGame;
    private final Stage stage;
    private final SpriteBatch spriteBatch;
    private final Textures textures;
    private final ButtonFactory buttonFactory;

    private TextButton returnButton, playButton;

    private VerticalGroup topVerticalGroup, bottomVerticalGroup, leftGroup, rightGroup;
    private HorizontalGroup pickFirstSnakeColor, pickSecondSnakeColor, pickMapHorizontalGroup;
    private Table mainTable, smallerTable;

    MapSelectButtons mapSelectButtons;


    SelectGamePropertiesForTwoPlayersScreen(Snake2D game){
        myGame = game;

        //selecting GAMEMODE
        game.changeGamemode(Gamemode.TWO_PLAYERS);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        spriteBatch = new SpriteBatch();
        textures = Textures.getInstance();
        buttonFactory = new ButtonFactory();


        initiateReturnButton("Return", ButtonStyle.SMALL);
        initializePlayButton("Play", ButtonStyle.NORMAL);

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

        // group with color buttons for first snake
        pickFirstSnakeColor = new SnakeColorButtons(myGame, WhichSnake.FIRST).getHorizontalGroupWithColorButtons();

        // group with color buttons for second snake
        pickSecondSnakeColor = new SnakeColorButtons(myGame, WhichSnake.SECOND).getHorizontalGroupWithColorButtons();

        //group with maps selection
        pickMapHorizontalGroup = new MapSelectButtons(myGame).getHorizontalGroupWithMapButtons();

        // left group in table
        leftGroup = new VerticalGroup();
        leftGroup.space(40);
        leftGroup.addActor(new Image(textures.getSnake1Label()));
        leftGroup.addActor(new Image(textures.getChooseSnakeColorLabel()));
        leftGroup.addActor(pickFirstSnakeColor);

        // rigth group in table
        rightGroup = new VerticalGroup();
        rightGroup.space(40);
        rightGroup.addActor(new Image(textures.getSnake2Label()));
        rightGroup.addActor(new Image(textures.getChooseSnakeColorLabel()));
        rightGroup.addActor(pickSecondSnakeColor);


        //smaller table
        smallerTable = new Table();
        smallerTable.pad(0, 0, 40, 0);
        smallerTable.add(leftGroup).pad(0, 0, 0, 150);
        smallerTable.add(rightGroup);

        //main table
        mainTable = new Table();;
        mainTable.setFillParent(true);
        mainTable.align(Align.center);
        mainTable.add(smallerTable).row();
        mainTable.add(new Image(textures.getChooseMapLabel())).row();
        mainTable.add(pickMapHorizontalGroup);


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
     * metoda odpowiedzialna za rysowanie ekranu wyboru parametrów gry w trybie dla dwóch gracza, jako parametr przyjmuje czas pomiędzy klatkami gry
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
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
