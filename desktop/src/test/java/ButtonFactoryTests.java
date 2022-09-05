import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.snake2d.game.ButtonFactory;
import com.snake2d.game.ButtonStyle;
import com.snake2d.game.Snake2D;
import com.snake2d.game.Textures;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ButtonFactoryTests {

    @Test
    public void shouldGetButtonFactory(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            ButtonFactory factory = new ButtonFactory();

            Assertions.assertNotNull(factory);
        }catch(Exception e){
            e.getMessage();
        }

    }


    @Test
    public void shouldGetTextButton(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            ButtonFactory factory = new ButtonFactory();

            Assertions.assertNotNull(factory.getTextButton("", ButtonStyle.NORMAL, 0, 0, new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                }
            }));
        }catch(Exception e){
            e.getMessage();
        }

    }

    @Test
    public void shouldGetImageButton(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            ButtonFactory factory = new ButtonFactory();

            Assertions.assertNotNull(factory.getImageButton(ButtonStyle.NORMAL, 0, 0, new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                }
            }));
        }catch(Exception e){
            e.getMessage();
        }

    }


}
