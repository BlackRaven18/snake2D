package com.snake2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * klasa obsługująca czcionki w aplikacji, reprezentuje wzorzec projektowy Singleton
 */
public class FontManager {

    private final int BUTTON_LABEL_FONT_SIZE = 30;

    private final FreeTypeFontGenerator generator;
    private final FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private final BitmapFont font;

    private static FontManager fontManager;


    private FontManager(){

        generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = BUTTON_LABEL_FONT_SIZE;
        font = generator.generateFont(parameter);

    }

    /**
     * metoda zwraca obiekt reprezentujący czcionkę o dużym wymiarze
     * @return
     */
    public  BitmapFont getNewBigFont(){
        BitmapFont bigFont;
        final FreeTypeFontGenerator newGenerator = new FreeTypeFontGenerator(Gdx.files.internal("big_font.ttf"));
        final FreeTypeFontGenerator.FreeTypeFontParameter newParameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        newParameter.size = 75;
        bigFont = newGenerator.generateFont(parameter);

        return bigFont;

    }

    /**
     * metoda zwraca instancję klasy FontManager
     * @return
     */
    // singleton
    public static FontManager getInstance(){
        if(fontManager == null){
            fontManager = new FontManager();
        }
        return fontManager;
    }

    public BitmapFont getFont() {
        return font;
    }

}
