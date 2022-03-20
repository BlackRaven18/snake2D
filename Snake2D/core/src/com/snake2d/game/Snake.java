package com.snake2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

enum MovementDirection { LEFT, RIGHT, UP, DOWN}

public class Snake {

    public static final int SEGMENT_WIDTH = 35;
    public static final int SEGMENT_HEIGHT = 35;

    private float timeBeforeMovement = 0.1f;
    private final List<GridPoint2> snakeParts;
    private MovementDirection direction;
    private float timeElapsedSinceLastMove;
    private boolean canChangeDirection;
    private final Textures textures;



    public Snake(Textures textures){
        this.textures = textures;
        snakeParts = new ArrayList<>();
    }


    public void initialize(){
        timeElapsedSinceLastMove = 0;
        direction = MovementDirection.RIGHT;

        snakeParts.clear();
        snakeParts.add(new GridPoint2(210, 105));
        snakeParts.add(new GridPoint2(175, 105));
        snakeParts.add(new GridPoint2(140, 105));
        snakeParts.add(new GridPoint2(105, 105));
        snakeParts.add(new GridPoint2(70, 105));
    }

    public List<GridPoint2> getSnakeSegmentsPositions(){
        return snakeParts;
    }

    private GridPoint2 head(){
        return snakeParts.get(0);
    }

    private GridPoint2 tail() {return snakeParts.get(snakeParts.size() - 1);}

    private GridPoint2 befTail() {return snakeParts.get(snakeParts.size() - 2);}

    public boolean isFoodFound(GridPoint2 foodPosition){
        return head().equals(foodPosition);
    }

    public void extendSnake(){
        snakeParts.add(new GridPoint2(snakeParts.get(snakeParts.size() - 1)));
    }

    public boolean hasHitHimself(){
        for(int i = 1; i < snakeParts.size(); i++){
            if(snakeParts.get(i).equals(head())){
                return true;
            }
        }
        return false;
    }

    public void draw(Batch batch) {
        //drawing body
        drawBody(batch);

        //drawing tail
        drawTail(batch);

        //drawing head
        drawHead(batch);
    }

    // drawing body without head and tail
    private  void drawBody(Batch batch){
        // i is counting how many body parts it is
        for(int i = 1; i < snakeParts.size() - 1; i++){
            switch( i % textures.getHowManyBodyTextures()){
                case 1:
                    batch.draw(textures.getGreenBody(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 2:
                    batch.draw(textures.getYellowBody(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 3:
                    batch.draw(textures.getOrangeBody(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 4:
                    batch.draw(textures.getRedBody(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 5:
                    batch.draw(textures.getPurpleBody(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
                case 0:
                    batch.draw(textures.getBlueBody(), snakeParts.get(i).x, snakeParts.get(i).y);
                    break;
            }
        }
    }

    private void drawHead(Batch batch){
        switch(direction){
            case UP:
                batch.draw(textures.getSnakeHeadUp(), head().x, head().y);
                break;
            case DOWN:
                batch.draw(textures.getSnakeHeadDown(), head().x, head().y);
                break;
            case LEFT:
                batch.draw(textures.getSnakeHeadLeft(), head().x, head().y);
                break;
            case RIGHT:
                batch.draw(textures.getSnakeHeadRight(), head().x, head().y);
                break;
        }
    }

    private void drawTail(Batch batch){

        if(befTail().x > tail().x){
            batch.draw(textures.getTailRight(),tail().x,tail().y);
        }
        else if (befTail().x < tail().x){
            batch.draw(textures.getTailLeft(),tail().x,tail().y);
        }
        else if (befTail().y > tail().y){
            batch.draw(textures.getTailUp(),tail().x,tail().y);
        }
        else if (befTail().y < tail().y){
            batch.draw(textures.getTailDown(),tail().x,tail().y);
        }
        /*switch(direction){
            case UP:
                batch.draw(textures.getTailUp(),tail().x,tail().y);
                break;
            case DOWN:
                batch.draw(textures.getTailDown(),tail().x,tail().y);
                break;
            case LEFT:
                batch.draw(textures.getTailLeft(),tail().x,tail().y);
                break;
            case RIGHT:
                batch.draw(textures.getTailRight(),tail().x,tail().y);
                break;
        }

         */
    }


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

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && direction != MovementDirection.RIGHT){
            newDirection = MovementDirection.LEFT;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && direction != MovementDirection.LEFT){
            newDirection = MovementDirection.RIGHT;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && direction != MovementDirection.DOWN){
            newDirection = MovementDirection.UP;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && direction != MovementDirection.UP){
            newDirection = MovementDirection.DOWN;
        }

        if(direction != newDirection){
            direction = newDirection;
            canChangeDirection = false;
        }
    }

    private void move(){

        // move all parts except head
        for (int i = snakeParts.size() - 1; i > 0; i--) {
            snakeParts.get(i).set(snakeParts.get(i - 1));
        }

        int segmentWidth = textures.getSnakeImg().getRegionWidth();
        int segmentHeight = textures.getSnakeImg().getRegionHeight();

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
}
