import com.badlogic.gdx.math.GridPoint2;
import com.snake2d.game.PositionRandomizer;
import com.snake2d.game.Snake2D;
import com.snake2d.game.SnakeColorButtons;
import com.snake2d.game.WhichSnake;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SnakeColorButtonsTests {

    @Test
    public void shouldGetGroupOfButtons() {
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            SnakeColorButtons snakeColorButtons = new SnakeColorButtons(new Snake2D(), WhichSnake.FIRST);

            Assertions.assertNotNull(snakeColorButtons);

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
