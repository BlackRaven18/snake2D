import com.snake2d.game.MapSelectButtons;
import com.snake2d.game.Snake2D;
import com.snake2d.game.SnakeColorButtons;
import com.snake2d.game.WhichSnake;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapSelectButtonsTests {

    @Test
    public void shouldGetGroupOfButtons() {
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            MapSelectButtons mapSelectButtons = new MapSelectButtons(new Snake2D());

            Assertions.assertNotNull(mapSelectButtons);

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
