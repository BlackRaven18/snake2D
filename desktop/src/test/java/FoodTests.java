import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.snake2d.game.Food;
import com.snake2d.game.Snake;
import com.snake2d.game.Snake2D;
import com.snake2d.game.SnakeTextureType;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FoodTests {

    @Test
    public void shouldGetNewFood(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            Food food = new Food(new TextureRegion());

            Assertions.assertNotNull(food.getPosition());

        }catch(Exception e){
            e.getMessage();
        }

    }
}
