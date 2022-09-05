package com.snake2d.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * klas przechowywująca tekstury ogólnego przeznaczenia w aplikacji, reprezentuje wzorzec projektowy Singleton
 */
public class Textures {

    private static Textures textures;


    private final Texture spritesheet;
    private final Texture foodSpritesheet;
    private final Texture obstacleSpritesheet;
    private Texture background;
    private final Texture first_background;
    private final Texture second_background;
    private final Texture third_background;
    private final Texture menuLogo;
    private final Texture miniMenuBackground;
    private final Texture menuBackground;
    private final Texture chooseSnakeColorLabel;
    private final Texture gameOverLabel;
    private final Texture chooseMapLabel, snake1Label, snake2Label;

    private final TextureRegion foodImg;
    private final TextureRegion obstacleImg;

    //private final Texture button;

    private Textures(){
        spritesheet = new Texture("spritesheet.png");
        foodSpritesheet = new Texture("food.png");
        obstacleSpritesheet = new Texture("stone 35x35.png");
        background = new Texture("1.png");

        first_background = new Texture("1.png");
        second_background = new Texture("2.png");
        third_background = new Texture("3.png");

        menuLogo = new Texture("logo.png");
        miniMenuBackground = new Texture("mini_menu_background.png");
        chooseSnakeColorLabel = new Texture("choose_snake_color_label.png");
        chooseMapLabel = new Texture("choose_map_label.png");
        snake1Label = new Texture("player_1_label.png");
        snake2Label = new Texture("player_2_label.png");
        gameOverLabel = new Texture("game_over_label.png");

        menuBackground = new Texture("menu_background.png");

        foodImg = new TextureRegion(foodSpritesheet, 0, 0, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        obstacleImg = new TextureRegion(obstacleSpritesheet, 0, 0, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
    }


    /**
     * metoda pozbywająca się tekstur z pamięci
     */
    public void disposeTextures(){
        spritesheet.dispose();
        foodSpritesheet.dispose();
        background.dispose();
        first_background.dispose();
        second_background.dispose();
        third_background.dispose();
        menuLogo.dispose();
        miniMenuBackground.dispose();
        menuBackground.dispose();
        chooseMapLabel.dispose();
        chooseSnakeColorLabel.dispose();
        gameOverLabel.dispose();
        //button.dispose();
    }

    /**
     * metoda zwraca instancję klasy Textures
     * @return
     */
    // singleton
    public static Textures getInstance(){
        if(textures == null){
            textures = new Textures();
        }
        return textures;
    }


    public Texture getBackground() {
        return background;
    }



    public TextureRegion getFoodImg() {
        return foodImg;
    }

    public TextureRegion getObstacleImg() { return obstacleImg; }

    public Texture getMenuLogo() {
        return menuLogo;
    }

    public Texture getMenuBackground() {
        return menuBackground;
    }

    public Texture getChooseSnakeColorLabel() {
        return chooseSnakeColorLabel;
    }

    public Texture getChooseMapLabel() {
        return chooseMapLabel;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }


    public Texture getFirst_background() {
        return first_background;
    }

    public Texture getSecond_background() {
        return second_background;
    }

    public Texture getThird_background() {
        return third_background;
    }

    public Texture getSpritesheet() {
        return spritesheet;
    }

    public Texture getSnake1Label() {
        return snake1Label;
    }

    public Texture getSnake2Label() {
        return snake2Label;
    }

    public Texture getMiniMenuBackground() {
        return miniMenuBackground;
    }

    public Texture getGameOverLabel() {
        return gameOverLabel;
    }
}
