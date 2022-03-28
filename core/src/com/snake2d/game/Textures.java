package com.snake2d.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {

    private final int howManyBodyTextures = 6;

    private final Texture spritesheet;
    private final Texture foodSpritesheet;
    //private final Texture obstacleSpritesheet;
    private final Texture tailSpritesheet;
    private final Texture background;
    private final TextureRegion snakeImg;
    private final TextureRegion snakeHeadUp, snakeHeadDown, snakeHeadLeft, snakeHeadRight;
    private final TextureRegion greenBody, yellowBody, orangeBody, redBody, purpleBody, blueBody;
    private final TextureRegion tailUp, tailDown, tailLeft, tailRight;
    private final TextureRegion foodImg;
    //private final TextureRegion obstacleImg;

    Textures(){
        spritesheet = new Texture("spritesheet.png");
        foodSpritesheet = new Texture("food.png");
        //obstacleSpritesheet = new Texture("stone 35x35.png");
        tailSpritesheet = new Texture("tailspritesheet.png");
        background = new Texture("1.png");

        snakeHeadDown = new TextureRegion(spritesheet, 8, 11, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakeHeadLeft = new TextureRegion(spritesheet, 8, 48, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakeHeadUp = new TextureRegion(spritesheet, 8, 85, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakeHeadRight = new TextureRegion(spritesheet, 8, 122, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);

        greenBody = new TextureRegion(spritesheet, 144, 11, Snake.SEGMENT_WIDTH ,Snake.SEGMENT_HEIGHT);
        yellowBody = new TextureRegion(spritesheet, 144, 48, Snake.SEGMENT_WIDTH ,Snake.SEGMENT_HEIGHT);
        orangeBody = new TextureRegion(spritesheet, 144, 85, Snake.SEGMENT_WIDTH ,Snake.SEGMENT_HEIGHT);
        redBody = new TextureRegion(spritesheet, 144, 122, Snake.SEGMENT_WIDTH ,Snake.SEGMENT_HEIGHT);
        purpleBody = new TextureRegion(spritesheet, 144, 159, Snake.SEGMENT_WIDTH ,Snake.SEGMENT_HEIGHT);
        blueBody = new TextureRegion(spritesheet, 144, 196, Snake.SEGMENT_WIDTH ,Snake.SEGMENT_HEIGHT);

        tailUp = new TextureRegion(tailSpritesheet, 0, 0, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        tailDown = new TextureRegion(tailSpritesheet, 36, 0, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        tailRight = new TextureRegion(tailSpritesheet, 72, 0, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        tailLeft = new TextureRegion(tailSpritesheet, 108, 0, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);

        snakeImg = new TextureRegion(spritesheet, 144, 11, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        foodImg = new TextureRegion(foodSpritesheet, 0, 0, Snake.SEGMENT_WIDTH, Snake.SEGMENT_WIDTH);
        //obstacleImg = new TextureRegion(obstacleSpritesheet, 0, 0, Snake.SEGMENT_WIDTH, Snake.SEGMENT_WIDTH);
    }

    public void disposeTextures(){
        spritesheet.dispose();
        foodSpritesheet.dispose();
        tailSpritesheet.dispose();
        background.dispose();
    }

    public int getHowManyBodyTextures() {
        return howManyBodyTextures;
    }

    public Texture getBackground() {
        return background;
    }

    public TextureRegion getSnakeImg() {
        return snakeImg;
    }

    public TextureRegion getSnakeHeadUp() {
        return snakeHeadUp;
    }

    public TextureRegion getSnakeHeadDown() {
        return snakeHeadDown;
    }

    public TextureRegion getSnakeHeadLeft() {
        return snakeHeadLeft;
    }

    public TextureRegion getSnakeHeadRight() {
        return snakeHeadRight;
    }

    public TextureRegion getGreenBody() {
        return greenBody;
    }

    public TextureRegion getYellowBody() {
        return yellowBody;
    }

    public TextureRegion getOrangeBody() {
        return orangeBody;
    }

    public TextureRegion getRedBody() {
        return redBody;
    }

    public TextureRegion getPurpleBody() {
        return purpleBody;
    }

    public TextureRegion getBlueBody() {
        return blueBody;
    }

    public TextureRegion getTailUp() {
        return tailUp;
    }

    public TextureRegion getTailDown() {
        return tailDown;
    }

    public TextureRegion getTailLeft() {
        return tailLeft;
    }

    public TextureRegion getTailRight() {
        return tailRight;
    }

    public TextureRegion getFoodImg() {
        return foodImg;
    }


}
