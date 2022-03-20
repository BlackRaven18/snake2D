package com.snake2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class NoMorePositionsAvailable extends Exception {}

public class FoodPositionRandomizer {

    private final List<GridPoint2> allPossiblePositions;

    public FoodPositionRandomizer(){
        int numberOfXPositions = Gdx.graphics.getWidth() / Snake.SEGMENT_WIDTH;
        int numberOfYPositions = Gdx.graphics.getHeight() / Snake.SEGMENT_HEIGHT;

        allPossiblePositions = new ArrayList<>();

        //Adding all positions where food can be places
        for(int i = 0; i < numberOfXPositions; i++){
            for(int j = 0; j < numberOfYPositions; j++){
                allPossiblePositions.add(new GridPoint2(i * Snake.SEGMENT_WIDTH, j * Snake.SEGMENT_HEIGHT));
            }
        }
    }

    public GridPoint2 getRandomAvailablePosition(List<GridPoint2> occupiedPositions)
            throws NoMorePositionsAvailable{

        HashSet<GridPoint2> unavailablePositions = new HashSet<>(occupiedPositions);

        List<GridPoint2> availablePositions = new ArrayList<>();

        // adding the positions which are no occupied by snake

        for(GridPoint2 position : allPossiblePositions){
            if(!unavailablePositions.contains(position)){
                availablePositions.add(position);
            }
        }

        // if snake is taking the whole screen
        if(availablePositions.size() <= 0){
            throw new NoMorePositionsAvailable();
        }

        // choosing the random position
        return availablePositions.get(
                (int) (Math.random() * availablePositions.size())
        );
    }




}