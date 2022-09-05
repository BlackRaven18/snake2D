package com.snake2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

enum MovementDirection { LEFT, RIGHT, UP, DOWN}


/**
 * Klasa reprezentujaca weza
 */
public class Snake {

    public static final int SEGMENT_WIDTH = 40;
    public static final int SEGMENT_HEIGHT = 40;
    public static final int SNAKE_STARTING_SEGMENTS_NUMBER = 5;


    private final int keyUp, keyDown, keyLeft, keyRight;

    private int points;

    private final float timeBeforeMovement = 0.15f;
    private final List<GridPoint2> snakeParts;


    private MovementDirection direction;
    private float timeElapsedSinceLastMove;
    private boolean canChangeDirection;
    private SnakeTextures snakeTextures;
    private SnakeTextureType snakeTextureType;


    public Snake(SnakeTextureType snakeTextureType){
        snakeTextures = new SnakeTextures(Textures.getInstance().getSpritesheet(), snakeTextureType);
        this.snakeTextureType = snakeTextureType;
        snakeParts = new ArrayList<>();

        points = 0;

        keyUp = Input.Keys.UP;
        keyDown = Input.Keys.DOWN;
        keyLeft = Input.Keys.LEFT;
        keyRight = Input.Keys.RIGHT;
    }

    public Snake(SnakeTextureType snakeTextureType,
                 int keyUp, int keyDown, int keyLeft, int keyRight){

        snakeTextures = new SnakeTextures(Textures.getInstance().getSpritesheet(), snakeTextureType);
        this.snakeTextureType = snakeTextureType;
        snakeParts = new ArrayList<>();

        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    /**
     * metoda odpowiedzialna za inicjalizacje węża, przyjmuje dwa parametry odzwierciedlające położenie węża na mapie
     * @param startX
     * @param startY
     */
    public void initialize(int startX, int startY){
        timeElapsedSinceLastMove = 0;
        direction = MovementDirection.RIGHT;

        snakeParts.clear();
        for(int i = 0; i < SNAKE_STARTING_SEGMENTS_NUMBER; i++){
            snakeParts.add(new GridPoint2(startX - i * SEGMENT_WIDTH, startY));
        }
    }

    /**
     * metoda pozwalająca na zmianę tekstur węża w zależności od typu tekstur przekazanego poprzez parametr
     */

    public void changeSnakeTextures(SnakeTextureType type){
        this.snakeTextures = new SnakeTextures(Textures.getInstance().getSpritesheet(), type);
        this.snakeTextureType = type;
    }

    /**
     * metoda zwracająca listę punktów na mapie zajmowanych przez węża
     */

    public List<GridPoint2> getSnakeSegmentsPositions(){
        return snakeParts;
    }

    /**
     * metoda pomocnicza zwracająca „głowę węża”
     */

    public GridPoint2 head(){
        return snakeParts.get(0);
    }

    /**
     * metoda pomocnicza zwracająca „ogon węża”
     */

    private GridPoint2 tail() {return snakeParts.get(snakeParts.size() - 1);}


    /**
     * metoda pomocnicza zwracająca fragment węża „przed ogonem”
     */
    private GridPoint2 befTail() {return snakeParts.get(snakeParts.size() - 2);}


    /**
     * metoda pozwalająca na zmianę tekstur węża w zależności od typu tekstur przekazanego poprzez parametr
     */
    public boolean isFoodFound(GridPoint2 foodPosition){
        return head().equals(foodPosition);
    }

    /**
     * metoda powiększająca węża po zjedzeniu pożywienia
     */

    public void extendSnake(){
        snakeParts.add(new GridPoint2(snakeParts.get(snakeParts.size() - 1)));
    }

    /**
     * metoda sprawdza kolizję z samym sobą
     */

    public boolean hasHitHimself(){
        for(int i = 1; i < snakeParts.size(); i++){
            if(snakeParts.get(i).equals(head())){
                return true;
            }
        }
        return false;
    }

    /**
     * metoda sprawdza kolizję z innym wężem, którego położenie jest przekazywane przez parametr
     */

    public boolean hasHitOtherSnake(List<GridPoint2> otherSnakeParts){
        for (GridPoint2 otherSnakePart : otherSnakeParts) {
            if (otherSnakePart.equals(head())) {
                return true;
            }
        }
        return false;
    }

    /**
     * metoda sprawdza kolizję z przeszkodami, których położenie jest przekazywane poprzez parametr
     */

    public boolean hasHitObstacle(List<GridPoint2> obstaclePositions){
        for (GridPoint2 obstaclePosition : obstaclePositions) {
            if (obstaclePosition.equals(head())) {
                return true;
            }
        }
        return false;
    }

    /**
     * metoda pozwalająca na rysowanie węża, przyjmuje obiekt Batch jako parametr
     */


    public void draw(Batch batch) {
        //drawing body
        drawBody(batch);

        //drawing tail
        drawTail(batch);

        //drawing head
        drawHead(batch);
    }

    /**
     * metoda odpowiedzialna za rysowanie fragmentów ciała węża, przyjmuje obiekt Batch jako parametr
     */

    // drawing body without head and tail
    private  void drawBody(Batch batch){
        // i is counting how many body parts it is
        for(int i = 1; i < snakeParts.size() - 1; i++){
            switch( i % snakeTextures.getHowManyBodyTextures()){
                case 1:
                    batch.draw(snakeTextures.getSnakePartOne(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 2:
                    batch.draw(snakeTextures.getSnakePartTwo(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 3:
                    batch.draw(snakeTextures.getSnakePartThree(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 4:
                    batch.draw(snakeTextures.getSnakePartFour(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 5:
                    batch.draw(snakeTextures.getSnakePartFive(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 0:
                    batch.draw(snakeTextures.getSnakePartSix(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
            }
        }
    }

    /**
     * metoda odpowiedzialna za rysowanie „głowy” węża, przyjmuje obiekt Batch jako parametr
     */

    private void drawHead(Batch batch){
        switch(direction){
            case UP:
                batch.draw(snakeTextures.getSnakeHeadUp(), head().x, head().y);
                break;
            case DOWN:
                batch.draw(snakeTextures.getSnakeHeadDown(), head().x, head().y);
                break;
            case LEFT:
                batch.draw(snakeTextures.getSnakeHeadLeft(), head().x, head().y);
                break;
            case RIGHT:
                batch.draw(snakeTextures.getSnakeHeadRight(), head().x, head().y);
                break;
        }
    }

    /**
     * metoda odpowiedzialna za rysowanie „ogona” węża, przyjmuje obiekt Batch jako parametr
     */

    private void drawTail(Batch batch){

        if(befTail().x > tail().x){
            batch.draw(snakeTextures.getTailRight(),tail().x,tail().y);
        }
        else if (befTail().x < tail().x){
            batch.draw(snakeTextures.getTailLeft(),tail().x,tail().y);
        }
        else if (befTail().y > tail().y){
            batch.draw(snakeTextures.getTailUp(),tail().x,tail().y);
        }
        else if (befTail().y < tail().y){
            batch.draw(snakeTextures.getTailDown(),tail().x,tail().y);
        }
    }

    /**
     * metoda odpowiedzialna za działanie węża, jako parametr przyjmuje różnicę czasu pomiędzy klatkami w grze
     */


    public void act(float deltaTime){
        if(canChangeDirection) {
            handleDirectionChange();
        }

        timeElapsedSinceLastMove += deltaTime;

        if(timeElapsedSinceLastMove >= timeBeforeMovement) {
            timeElapsedSinceLastMove = 0;
            canChangeDirection = true;
            move();
        }
    }
    private void handleDirectionChange(){
        MovementDirection newDirection = direction;

        if(Gdx.input.isKeyJustPressed(keyLeft) && direction != MovementDirection.RIGHT){
            newDirection = MovementDirection.LEFT;
        }

        if(Gdx.input.isKeyJustPressed(keyRight) && direction != MovementDirection.LEFT){
            newDirection = MovementDirection.RIGHT;
        }

        if(Gdx.input.isKeyJustPressed(keyUp) && direction != MovementDirection.DOWN){
            newDirection = MovementDirection.UP;
        }

        if(Gdx.input.isKeyJustPressed(keyDown) && direction != MovementDirection.UP){
            newDirection = MovementDirection.DOWN;
        }

        if(direction != newDirection){
            direction = newDirection;
            canChangeDirection = false;
        }
    }

    /**
     * metoda odpowiedzialna za zmianę kierunku poruszania się węża
     */

    private void move(){

        // move all parts except head
        for (int i = snakeParts.size() - 1; i > 0; i--) {
            snakeParts.get(i).set(snakeParts.get(i - 1));
        }

        int segmentWidth = snakeTextures.getSnakePartOne().getRegionWidth();
        int segmentHeight =  snakeTextures.getSnakePartOne().getRegionHeight();

        // x, y of the last part of snake
        // in front of upper and right border
        int lastWindowSegmentX = Gdx.graphics.getWidth() - segmentWidth;
        int lastWindowSegmentY = Gdx.graphics.getHeight() - segmentHeight;

        GridPoint2 head = head();

        switch (direction) {
            case LEFT:
                head.x = (head.x == 0) ? lastWindowSegmentX : head.x - segmentWidth;
                break;
            case RIGHT:
                head.x = (head.x == lastWindowSegmentX) ? 0 : head.x + segmentWidth;
                break;
            case UP:
                head.y = (head.y == lastWindowSegmentY) ? 0 : head.y + segmentHeight;
                break;
            case DOWN:
                head.y = (head.y == 0) ? lastWindowSegmentY : head.y - segmentHeight;
                break;
            }
    }

    /**
     * metoda zwraca listę punktów zajmowanych przez węża
     */

    public List<GridPoint2> getSnakeParts() {
        return snakeParts;
    }

    /**
     * metoda zwraca typ tekstur używanych przez węża
     */

    public SnakeTextureType getSnakeTextureType() {
        return snakeTextureType;
    }

    /**
     * metoda dodaje punkt do wyniku węża
     */


    public void addPoint(){
        points++;
    }

    /**
     * metoda zwraca liczbę punktów uzyskanych przez węża podczas rozgrywki
     */

    public int getPoints() {
        return points;
    }
}
