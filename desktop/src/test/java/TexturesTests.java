import com.badlogic.gdx.Gdx;
import com.snake2d.game.Snake2D;
import com.snake2d.game.Textures;
import com.snake2d.game.desktop.DesktopLauncher;
import com.snake2d.game.desktop.GdxTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TexturesTests {

    @Test
    public void shoudGetInstance(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            Textures textures = Textures.getInstance();
            Assertions.assertNotNull(textures);
        }catch(Exception e){
            e.getMessage();
        }

    }

    @Test
    public void shouldGetBackgroundTexture(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            Textures textures = Textures.getInstance();
            Assertions.assertNotNull(textures.getBackground());
        }catch(Exception e){
            e.getMessage();
        }
    }

    @Test
    public void shouldGetFoodTexture(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            Textures textures = Textures.getInstance();
            Assertions.assertNotNull(textures.getFoodImg());
        }catch(Exception e){
            e.getMessage();
        }
    }

    @Test
    public void shouldGetMenuLogoTexture(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            Textures textures = Textures.getInstance();
            Assertions.assertNotNull(textures.getMenuLogo());
        }catch(Exception e){
            e.getMessage();
        }
    }

    @Test
    public void shouldGetFirstBackgroundTexture(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            Textures textures = Textures.getInstance();
            Assertions.assertNotNull(textures.getFirst_background());
        }catch(Exception e){
            e.getMessage();
        }
    }
    @Test
    public void shouldGetSecondBackgroundTexture(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            Textures textures = Textures.getInstance();
            Assertions.assertNotNull(textures.getSecond_background());
        }catch(Exception e){
            e.getMessage();
        }
    }
    @Test
    public void shouldGetThirdBackgroundTexture(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            Textures textures = Textures.getInstance();
            Assertions.assertNotNull(textures.getThird_background());
        }catch(Exception e){
            e.getMessage();
        }
    }
    @Test
    public void shouldGetMiniMenuTexture(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            Textures textures = Textures.getInstance();
            Assertions.assertNotNull(textures.getMiniMenuBackground());
        }catch(Exception e){
            e.getMessage();
        }
    }
    @Test
    public void shouldGetObstacleTexture(){
        try {
            GdxTestRunner runner = new GdxTestRunner(Snake2D.class);
            Textures textures = Textures.getInstance();
            Assertions.assertNotNull(textures.getObstacleImg());
        }catch(Exception e){
            e.getMessage();
        }
    }

}
