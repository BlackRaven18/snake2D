import com.badlogic.gdx.math.GridPoint2;
import com.snake2d.game.ButtonFactory;
import com.snake2d.game.Snake;
import com.snake2d.game.Snake2D;
import com.snake2d.game.SnakeTextureType;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SnakeTests {

    @Test
    public void shouldGetNewSnake(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            Snake snake = new Snake(SnakeTextureType.GREEN);

            Assertions.assertNotNull(snake);
        }catch(Exception e){
            e.getMessage();
        }

    }

    @Test
    public void shouldGetSnakePartsPositions(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            Snake snake = new Snake(SnakeTextureType.GREEN);
            List<GridPoint2> list = snake.getSnakeParts();

            Assertions.assertFalse(list.isEmpty());
        }catch(Exception e){
            e.getMessage();
        }

    }

    @Test
    public void shouldPointBeAddes(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            Snake snake = new Snake(SnakeTextureType.GREEN);
            int points = snake.getPoints();
            snake.addPoint();

            Assertions.assertEquals(points, snake.getPoints() - 1);
        }catch(Exception e){
            e.getMessage();
        }

    }




}
