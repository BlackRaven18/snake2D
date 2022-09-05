package com.snake2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * klasa będąca fabryką przycisków
 */
public class ButtonFactory {

    Skin skin;
    TextureAtlas buttonAtlas;
    FontManager fontManager;

    public ButtonFactory(){

        fontManager = FontManager.getInstance();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons.txt"));
        skin.addRegions(buttonAtlas);

        //defaultTextButtonStyle = new TextButton.TextButtonStyle();
        //defaultTextButtonStyle.font = fontManager.getFont();
        //defaultTextButtonStyle.up = skin.getDrawable("button_up");
        //defaultTextButtonStyle.over = skin.getDrawable("button_over");
    }

    /**
     * metoda tworzy i zwraca obiekt „TextButton”. Jako parametry przyjmuje napis, styl, wartość x i y odpowiedzialną za położenie oraz obiekt „ChangeListener”
     * obsługujący akcje, które mają się zdarzyć po naciśnięciu przycisku
     * @param caption
     * @param style
     * @param x
     * @param y
     * @param changeListener
     * @return
     */
    public TextButton getTextButton(String caption, ButtonStyle style, float x, float y, ChangeListener changeListener){

        TextButton.TextButtonStyle defaultTextButtonStyle = new TextButton.TextButtonStyle();
        defaultTextButtonStyle.font = fontManager.getFont();

        if(style == ButtonStyle.NORMAL){
            defaultTextButtonStyle.up = skin.getDrawable("button_up");
            defaultTextButtonStyle.over = skin.getDrawable("button_over");
        } else if(style == ButtonStyle.SMALL){
            defaultTextButtonStyle.up = skin.getDrawable("arrow_up");
            defaultTextButtonStyle.over = skin.getDrawable("arrow_over");
        }



        final TextButton textButton = new TextButton(caption, defaultTextButtonStyle);
        textButton.setX(x);
        textButton.setY(y);
        textButton.addListener(changeListener);

        ChangeListener playMusicWhenClickedListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                    SoundsManager.getInstance().playSelectButtonSound();
            }
        };
        textButton.addListener(playMusicWhenClickedListener);

        return textButton;
    }

    /**
     * metoda tworzy i zwraca obiekt „ImageButton”. Jako parametry przyjmuje styl, wartość x i y odpowiedzialną za położenie oraz obiekt „ChangeListener”
     * obsługujący akcje, które mają się zdarzyć po naciśnięciu przycisku
     * @param style
     * @param x
     * @param y
     * @param changeListener
     * @return
     */
    public ImageButton getImageButton(ButtonStyle style, float x, float y, ChangeListener changeListener){


        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        switch(style){
            case BLUE:
                imageButtonStyle.up = skin.getDrawable("frame_blue_up");
                imageButtonStyle.checked = imageButtonStyle.over = skin.getDrawable("frame_blue_over");
                break;
            case GREEN:
                imageButtonStyle.up = skin.getDrawable("frame_green_up");
                imageButtonStyle.checked = imageButtonStyle.over = skin.getDrawable("frame_green_over");
                break;
            case PURPLE:
                imageButtonStyle.up = skin.getDrawable("frame_purple_up");
                imageButtonStyle.checked = imageButtonStyle.over = skin.getDrawable("frame_purple_over");
                break;
            case RED:
                imageButtonStyle.up = skin.getDrawable("frame_red_up");
                imageButtonStyle.checked = imageButtonStyle.over = skin.getDrawable("frame_red_over");
                break;
            case BLACK:
                imageButtonStyle.up = skin.getDrawable("frame_black_up");
                imageButtonStyle.checked = imageButtonStyle.over = skin.getDrawable("frame_black_over");
                break;
            case COLORFUL:
                imageButtonStyle.up = skin.getDrawable("frame_colorful_up");
                imageButtonStyle.checked = imageButtonStyle.over = skin.getDrawable("frame_colorful_over");
                break;
            case FIRST_BACKGROUND:
                imageButtonStyle.up = skin.getDrawable("frame_background_1");
                imageButtonStyle.checked = imageButtonStyle.over = skin.getDrawable("frame_background_1_over");
                break;
            case SECOND_BACKGROUND:
                imageButtonStyle.up = skin.getDrawable("frame_background_2");
                imageButtonStyle.checked = imageButtonStyle.over = skin.getDrawable("frame_background_2_over");
                break;
            case THIRD_BACKGROUND:
                imageButtonStyle.up = skin.getDrawable("frame_background_3");
                imageButtonStyle.checked = imageButtonStyle.over = skin.getDrawable("frame_background_3_over");
                break;
        }

        ImageButton imageButton = new ImageButton(imageButtonStyle);
        imageButton.setX(x);
        imageButton.setY(y);
        imageButton.addListener(changeListener);

        ChangeListener playMusicWhenClickedListener = new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                SoundsManager.getInstance().playSelectButtonSound();
            }
        };
        imageButton.addListener(playMusicWhenClickedListener);
        return imageButton;
    }


}
