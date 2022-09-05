package com.snake2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * klasa obsługująca dźwięki w aplikacji, reprezentuje wzorzec projektowy Singleton
 */
public class SoundsManager {

    private static SoundsManager soundsManager;

    Sound eatFoodSound, selectButtonSound, loseSound;

    private SoundsManager(){
        eatFoodSound = Gdx.audio.newSound(Gdx.files.internal("eat_food_sound.wav"));
        selectButtonSound = Gdx.audio.newSound(Gdx.files.internal("select_button_sound.wav"));
        loseSound = Gdx.audio.newSound(Gdx.files.internal("lose_sound.wav"));
    }

    /**
     * metoda odgrywa dźwięk po zjedzeniu pożywienia przez węża
     */
    public void playEatFoodSound(){
        eatFoodSound.play(1.0f);
    }

    /**
     * metoda odgrywa dźwięk po kliknięciu na przycisk
     */
    public void playSelectButtonSound(){
        selectButtonSound.play(1.0f);
    }

    /**
     * metoda odgrywa dźwięk, kiedy rozgrywka nie może być dalej kontynuowana
     */
    public void playLoseSound(){
        long id = loseSound.play(1.0f);
    }

    /**
     * metoda zwraca instancje klasy SoundsManager
     * @return
     */
    // singleton
    static public SoundsManager getInstance(){
        if(soundsManager == null){
            soundsManager = new SoundsManager();
        }
        return soundsManager;
    }
}
