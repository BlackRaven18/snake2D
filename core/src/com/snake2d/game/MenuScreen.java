package com.snake2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


/**
 * klasa reprezentująca menu główne gry
 */
public class MenuScreen implements Screen {

    private final Snake2D myGame;

    private final Table table;
    private final Stage stage;

    private final ButtonFactory buttonManager;

    private final SpriteBatch spriteBatch;
    private final Textures textures;

    private TextButton playButton, exitButton;


    public MenuScreen(Snake2D game){

        this.myGame = game;
        spriteBatch = new SpriteBatch();

        textures = Textures.getInstance();

        table = new Table();
        table.setFillParent(true);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        buttonManager = new ButtonFactory();



        initializePlayButton("Play", ButtonStyle.NORMAL);
        initializeExitButton("Exit", ButtonStyle.NORMAL);
        Image image = new Image(textures.getMenuLogo());

        table.add(image).padBottom(100f);
        table.row();
        table.add(playButton).space(20);
        table.row();
        table.add(exitButton).space(20);

        stage.addActor(table);
    }


    /**
     * metoda odpowiedzialna za inicjalizację przycisku „PlayButton”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */
    private void initializePlayButton(String caption, ButtonStyle style){
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                myGame.setScreen(new SelectGameModeScreen(myGame));
            }
        };
        playButton = buttonManager.getTextButton(caption, style, 200, 200, changeListener);
    }

    /**
     * metoda odpowiedzialna za inicjalizację przycisku „ExitButton”, jako parametr przyjmuje napis i styl przycisku
     * @param caption
     * @param style
     */
    private void initializeExitButton(String caption, ButtonStyle style){
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        };

        exitButton = buttonManager.getTextButton(caption, style, 200, 200, changeListener);
    }

    /**
     * metoda odpowiedzialna za rysowanie okna Menu, jako parametr przyjmuje czas pomiędzy klatkami gry
     * @param delta
     */
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(textures.getMenuBackground(), 0, 0);

        spriteBatch.end();

        stage.draw();
        stage.act();

    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height){}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();

    }
}
