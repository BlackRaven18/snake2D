package com.snake2d.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

class NoMorePlaceForObstacleException extends Exception {}

/**
 * klasa reprezentująca przeszkody
 */
public class Obstacle {
    private int numberOfObstacles;

    private final List<GridPoint2> obstaclePositions;
    private final TextureRegion texture;
    private final PositionRandomizer positionRandomizer;

    public Obstacle(TextureRegion textureRegion, int numberOfObstacles){
        this.numberOfObstacles = numberOfObstacles;
        this.obstaclePositions = new ArrayList<>();
        this.texture = textureRegion;
        this.positionRandomizer = new PositionRandomizer();
    }

    /**
     * metoda generująca przeszkody na mapie
     * @param mergedSnakesPositions
     * @throws NoMorePlaceForObstacleException
     */
    public void generateObstaclePotisions(List<GridPoint2> mergedSnakesPositions) throws NoMorePlaceForObstacleException{
        List<GridPoint2> mergedPositions = new ArrayList<>();
        GridPoint2 point;

        try {
            for(int i = 0; i < numberOfObstacles; i++) {
                mergedPositions.addAll(mergedSnakesPositions);
                mergedPositions.addAll(obstaclePositions);

                point = positionRandomizer.getRandomAvailablePosition(mergedPositions);
                obstaclePositions.add(point);
                mergedPositions.clear();
            }
        }
        catch(NoMorePositionsAvailable e){
            throw new NoMorePlaceForObstacleException();
        }
    }

    /**
     * metoda rysująca przeszkody na mapie, przyjmuje obiekt Batch jako parametr
     * @param batch
     */
    public void draw(Batch batch){
        for(int i = 0; i < obstaclePositions.size(); i++){
            batch.draw(texture, obstaclePositions.get(i).x, obstaclePositions.get(i).y);
        }
    }

    /**
     * metoda zwracająca listę pozycji zajmowanych przez przeszkody
     * @return
     */
    public List<GridPoint2> getObstaclePositions() {
        return obstaclePositions;
    }
}
