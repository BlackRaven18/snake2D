package com.snake2d.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * klasa przechowywująca teksturę węża
 */
public class SnakeTextures {

    private final int howManyBodyTextures = 6;

    Texture spritesheet;

    private TextureRegion snakeHeadUp, snakeHeadDown, snakeHeadLeft, snakeHeadRight;
    private TextureRegion snakePartOne, snakePartTwo, snakePartThree, snakePartFour, snakePartFive, snakePartSix;
    private TextureRegion tailUp, tailDown, tailLeft, tailRight;

    public SnakeTextures(Texture spritesheet) {
        this.spritesheet = spritesheet;
        loadSnakeTexturesBasedOnType(SnakeTextureType.COLORFUL);

    }

    public SnakeTextures(Texture spritesheet, SnakeTextureType type){
        this.spritesheet = spritesheet;
        loadSnakeTexturesBasedOnType(type);
    }


    /**
     * metoda ładująca tekstury z pliku graficznego,
     * jako parametr przyjmuje współrzędną x, od której w pliku rozpoczynają się tekstury „ogona” węża
     * @param sheetX
     */
    private void loadSnakeTailTextures(int sheetX) {
        tailRight = new TextureRegion(spritesheet, sheetX, 413, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        tailLeft = new TextureRegion(spritesheet, sheetX, 413 + 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        tailDown = new TextureRegion(spritesheet, sheetX, 413 + 2 * 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        tailUp = new TextureRegion(spritesheet, sheetX, 413 + 3 * 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
    }

    /**
     * metoda ładująca tekstury z pliku graficznego, jako parametr przyjmuje współrzędną x,
     * od której w pliku rozpoczynają się tekstury ”głowy” węża
     * @param sheetX
     */
    private void loadSnakeHeadTextures(int sheetX) {
        snakeHeadDown = new TextureRegion(spritesheet, sheetX, 249, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakeHeadLeft = new TextureRegion(spritesheet, sheetX, 249 + 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakeHeadUp = new TextureRegion(spritesheet, sheetX, 249 + 2 * 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakeHeadRight = new TextureRegion(spritesheet, sheetX, 249 + 3 * 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
    }

    /**
     * metoda ładująca tekstury z pliku graficznego, jako parametr przyjmuje współrzędną x, od której w pliku rozpoczynają się tekstury „ciała” węża
     * @param sheetX
     * @param sheetY
     */
    private void loadSnakeBodyTextures(int sheetX, int sheetY) {
        snakePartOne = new TextureRegion(spritesheet, sheetX, sheetY, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakePartTwo = new TextureRegion(spritesheet, sheetX, sheetY + 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakePartThree = new TextureRegion(spritesheet, sheetX, sheetY + 2 * 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakePartFour = new TextureRegion(spritesheet, sheetX, sheetY + 3 * 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakePartFive = new TextureRegion(spritesheet, sheetX, sheetY + 4 * 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
        snakePartSix = new TextureRegion(spritesheet, sheetX, sheetY + 5 * 41, Snake.SEGMENT_WIDTH, Snake.SEGMENT_HEIGHT);
    }

    /**
     * metoda ładuje tekstury węża w zależności do typu tekstur przekazanego jako parametr
     * @param snakeType
     */
    public void loadSnakeTexturesBasedOnType(SnakeTextureType snakeType) {


        int sheetX = 0, sheetY = 0;


        // loading body textures
        switch (snakeType) {
            case BLUE:
                sheetX = 52;
                break;
            case GREEN:
                sheetX = 102;
                break;
            case PURPLE:
                sheetX = 152;
                break;
            case RED:
                sheetX = 202;
                break;
            case BLACK:
                sheetX = 252;
                break;
            case COLORFUL:
                sheetX = 2;
                break;
        }

        loadSnakeBodyTextures(sheetX, sheetY);

        // loading head textures

        loadSnakeHeadTextures(sheetX);

        loadSnakeTailTextures(sheetX);
    }

    public int getHowManyBodyTextures() {
        return howManyBodyTextures;
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

    public TextureRegion getSnakePartOne() {
        return snakePartOne;
    }

    public TextureRegion getSnakePartTwo() {
        return snakePartTwo;
    }

    public TextureRegion getSnakePartThree() {
        return snakePartThree;
    }

    public TextureRegion getSnakePartFour() {
        return snakePartFour;
    }

    public TextureRegion getSnakePartFive() {
        return snakePartFive;
    }

    public TextureRegion getSnakePartSix() {
        return snakePartSix;
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
}
