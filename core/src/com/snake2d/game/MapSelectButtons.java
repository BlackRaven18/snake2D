package com.snake2d.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;

/**
 * klasa reprezentująca moduł wyboru mapy
 */
public class MapSelectButtons {

    private final ImageButton firstBackgroundButton, secondBackgroundButton, thirdBackgroundButton;
    ArrayList<ImageButton> mapButtonArrayList;
    Snake2D myGame;

    public MapSelectButtons(Snake2D game){
        myGame = game;
        firstBackgroundButton = getInitiatedMapButton(ButtonStyle.FIRST_BACKGROUND);
        secondBackgroundButton = getInitiatedMapButton(ButtonStyle.SECOND_BACKGROUND);
        thirdBackgroundButton = getInitiatedMapButton(ButtonStyle.THIRD_BACKGROUND);

        initializeMapButtonArrayList();
    }

    /**
     * metoda inicjalizuje przyciski wyboru mapy
     */
    private void initializeMapButtonArrayList(){
        mapButtonArrayList = new ArrayList<>();
        mapButtonArrayList.add(firstBackgroundButton);
        mapButtonArrayList.add(secondBackgroundButton);
        mapButtonArrayList.add(thirdBackgroundButton);
    }

    /**
     * metoda zwraca zainicjowany przycisk wyboru koloru mapy
     * @param style
     * @return
     */
    private ImageButton getInitiatedMapButton(ButtonStyle style){

        final ButtonStyle tmpStyle = style;
        ButtonFactory buttonFactory = new ButtonFactory();

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                // uncheckning all buttons
                // if button is checked, but pointer was not on it then uncheck it
                for(ImageButton button : mapButtonArrayList){
                    if(button.isChecked() && !button.isOver()){
                        button.setChecked(false);
                    }
                }

                // selecting choosen map
                switch (tmpStyle){
                    case FIRST_BACKGROUND:
                        Textures.getInstance().setBackground(Textures.getInstance().getFirst_background());
                        break;
                    case SECOND_BACKGROUND:
                        Textures.getInstance().setBackground(Textures.getInstance().getSecond_background());
                        break;
                    case THIRD_BACKGROUND:
                        Textures.getInstance().setBackground(Textures.getInstance().getThird_background());
                        break;
                }

            }
        };

        // changing color of the snake
        //myGame.reloadGame();
        return buttonFactory.getImageButton(style, 200, 200, changeListener);
    }

    /**
     * metoda zwraca obiekt „HorizontalGroup” zwierający przyciski wyboru mapy
     * @return
     */
    public HorizontalGroup getHorizontalGroupWithMapButtons(){
        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.space(20);

        horizontalGroup.addActor(firstBackgroundButton);
        horizontalGroup.addActor(secondBackgroundButton);
        horizontalGroup.addActor(thirdBackgroundButton);

        return horizontalGroup;
    }

}
