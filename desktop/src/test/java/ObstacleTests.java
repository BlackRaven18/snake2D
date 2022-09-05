import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.snake2d.game.Food;
import com.snake2d.game.Obstacle;
import com.snake2d.game.Snake2D;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObstacleTests {

    @Test
    public void shouldGetNewObstacles(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);

            Obstacle obstacle = new Obstacle(new TextureRegion(), 5);

            Assertions.assertNotNull(obstacle.getObstaclePositions());

        }catch(Exception e){
            e.getMessage();
        }

    }


}
