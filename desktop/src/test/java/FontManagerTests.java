import com.snake2d.game.*;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FontManagerTests {

    @Test
    public void shouldGetInstance() {
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            FontManager manager = FontManager.getInstance();

            Assertions.assertNotNull(manager);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void shouldGetBigFont() {
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            FontManager manager = FontManager.getInstance();

            Assertions.assertNotNull(manager.getNewBigFont());

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void shouldGetNormalFont() {
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            FontManager manager = FontManager.getInstance();

            Assertions.assertNotNull(manager.getFont());

        } catch (Exception e) {
            e.getMessage();
        }
    }

}
