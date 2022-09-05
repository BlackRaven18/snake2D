import com.snake2d.game.FontManager;
import com.snake2d.game.Snake2D;
import com.snake2d.game.SoundsManager;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SoundManagerTests {

    @Test
    public void shouldGetInstance() {
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            Assertions.assertNotNull(SoundsManager.getInstance());

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
