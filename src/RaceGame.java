import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class RaceGame extends org.newdawn.slick.BasicGame {

    Player player = new Player();
    World world = new World();

    public RaceGame(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.player.init(gameContainer);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        this.player.update(gameContainer, i);

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.player.render(gameContainer, graphics);
        //this.obstacle.render(gameContainer, graphics);
    }
}
