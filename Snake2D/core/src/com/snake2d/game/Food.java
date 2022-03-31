package com.snake2d.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.List;

class NoMorePlaceForFoodException extends Exception {}

public class Food {
    private final GridPoint2 position;
    private final TextureRegion textureR;
    private final PositionRandomizer positionRandomizer;

    public Food(TextureRegion textureR){
        this.position = new GridPoint2();
        this.textureR  = textureR;
        this.positionRandomizer = new PositionRandomizer();
    }

    public void draw(Batch batch){
        batch.draw(textureR, position.x, position.y);
    }

    public void randomizePosition(List<GridPoint2> snakeSegmentsPositions) throws NoMorePlaceForFoodException {

        GridPoint2 foodPosition;
        // generating random available food position
        try {
            foodPosition = positionRandomizer.getRandomAvailablePosition(snakeSegmentsPositions);
        }
        catch(NoMorePositionsAvailable e){
            throw new NoMorePlaceForFoodException();
        }
        this.position.set(foodPosition.x, foodPosition.y);
    }

    public GridPoint2 getPosition() {
        return position;
    }
}
