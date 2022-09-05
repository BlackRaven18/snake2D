import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.snake2d.game.Obstacle;
import com.snake2d.game.PositionRandomizer;
import com.snake2d.game.Snake2D;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PositionRandomizerTests {

    @Test
    public void shouldGetRandomAvailablePosition(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            PositionRandomizer positionRandomizer = new PositionRandomizer();

            List<GridPoint2> positions = new ArrayList<>();
            GridPoint2 point = positionRandomizer.getRandomAvailablePosition(positions);

            Assertions.assertNotNull(point);

        }catch(Exception e){
            e.getMessage();
        }

    }
}
