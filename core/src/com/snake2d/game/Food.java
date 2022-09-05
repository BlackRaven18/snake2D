package com.snake2d.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.List;

class NoMorePlaceForFoodException extends Exception {}

/**
 * klasa reprezentująca pożywienie
 */

public class Food {
    private final GridPoint2 position;
    private final TextureRegion textureR;
    private final PositionRandomizer positionRandomizer;

    public Food(TextureRegion textureR){
        this.position = new GridPoint2();
        this.textureR  = textureR;
        this.positionRandomizer = new PositionRandomizer();
    }

    /**
     * metoda rysująca pożywienie, przyjmuje obiekt Batch jako parametr
     * @param batch
     */
    public void draw(Batch batch){
        batch.draw(textureR, position.x, position.y);
    }

    /**
     * metoda losująca położenie pożywienia, przyjmuje listę pozycji zajmowanych przez węże i przeszkody
     * @param snakeSegmentsPositionsAndObstaclePositions
     * @throws NoMorePlaceForFoodException
     */

    public void randomizePosition(List<GridPoint2> snakeSegmentsPositionsAndObstaclePositions) throws NoMorePlaceForFoodException {

        GridPoint2 foodPosition;
        // generating random available food position
        try {
            foodPosition = positionRandomizer.getRandomAvailablePosition(snakeSegmentsPositionsAndObstaclePositions);
        }
        catch(NoMorePositionsAvailable e){
            throw new NoMorePlaceForFoodException();
        }
        this.position.set(foodPosition.x, foodPosition.y);
    }

    /**
     * metoda zwraca pozycję pożywienia na mapie
     * @return
     */
    public GridPoint2 getPosition() {
        return position;
    }
}
