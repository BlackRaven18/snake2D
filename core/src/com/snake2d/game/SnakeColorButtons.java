package com.snake2d.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;

/**
 * klasa reprezentująca moduł wyboru koloru węża
 */
public class SnakeColorButtons {

    private final ImageButton blueButton, greenButton, purpleButton, redButton, blackButton, colorfulButton;
    private ArrayList<ImageButton> imageButtonArrayList;
    Snake2D myGame;
    WhichSnake whichSnake;

    public SnakeColorButtons(Snake2D myGame, WhichSnake whichSnake){
        this.myGame = myGame;
        this.whichSnake = whichSnake;

        if(myGame.getGamemode() == Gamemode.SINGLE_PLAYER && whichSnake == WhichSnake.SECOND){
            whichSnake = WhichSnake.FIRST;
        }




        blueButton = getInitiatedImageButton(ButtonStyle.BLUE);
        greenButton = getInitiatedImageButton(ButtonStyle.GREEN);
        purpleButton = getInitiatedImageButton(ButtonStyle.PURPLE);
        redButton = getInitiatedImageButton(ButtonStyle.RED);
        blackButton = getInitiatedImageButton(ButtonStyle.BLACK);
        colorfulButton = getInitiatedImageButton(ButtonStyle.COLORFUL);

        initializeImageButtonArrayList();
    }

    /**
     * metoda inicjalizuje przyciski wyboru koloru węża
     */
    private void initializeImageButtonArrayList(){
        imageButtonArrayList = new ArrayList<>();
        imageButtonArrayList.add(blueButton);
        imageButtonArrayList.add(greenButton);
        imageButtonArrayList.add(purpleButton);
        imageButtonArrayList.add(redButton);
        imageButtonArrayList.add(blackButton);
        imageButtonArrayList.add(colorfulButton);
    }

    /**
     * metoda zwraca zainicjowany przycisk wyboru koloru węża
     * @param style
     * @return
     */
    private ImageButton getInitiatedImageButton(ButtonStyle style){

        final ButtonStyle tmpStyle = style;
        ButtonFactory buttonFactory = new ButtonFactory();

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                // uncheckning all buttons
                // if button is checked, but pointer was not on it then uncheck it
                for(ImageButton button : imageButtonArrayList){
                    if(button.isChecked() && !button.isOver()){
                        button.setChecked(false);
                    }
                }

                // selecting choosen snake color
                SnakeTextureType tmpType;
                switch(tmpStyle){
                    case BLUE:
                        tmpType = SnakeTextureType.BLUE;
                        break;
                    case GREEN:
                        tmpType = SnakeTextureType.GREEN;
                        break;
                    case PURPLE:
                        tmpType = SnakeTextureType.PURPLE;
                        break;
                    case RED:
                        tmpType = SnakeTextureType.RED;
                        break;
                    case BLACK:
                        tmpType = SnakeTextureType.BLACK;
                        break;
                    case COLORFUL:
                    default:
                        tmpType = SnakeTextureType.COLORFUL;
                        break;
                }

                // changing color of the snake

                if(whichSnake == WhichSnake.FIRST) {
                    myGame.reloadFirstSnake(tmpType);
                }

                if(whichSnake == WhichSnake.SECOND){
                    myGame.reloadSecondSnake(tmpType);
                }

            }
        };
        return buttonFactory.getImageButton(style, 200, 200, changeListener);
    }

    /**
     * metoda zwraca obiekt „HorizontalGroup” zwierający przyciski wyboru koloru węża
     * @return
     */
    public HorizontalGroup getHorizontalGroupWithColorButtons(){
        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.space(20);

        horizontalGroup.addActor(blueButton);
        horizontalGroup.addActor(greenButton);
        horizontalGroup.addActor(purpleButton);
        horizontalGroup.addActor(redButton);
        horizontalGroup.addActor(blackButton);
        horizontalGroup.addActor(colorfulButton
        );
        return horizontalGroup;
    }

}
