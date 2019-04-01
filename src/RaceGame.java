import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class RaceGame extends org.newdawn.slick.BasicGame {


    Player player = new Player(new Punto(300, 400));
    World world = new World();
    boolean xoc = false;

    public RaceGame(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.player.init(gameContainer);

        world.controlador = 0;

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        this.player.update(gameContainer, i);
        this.world.update(gameContainer, i);
        for (int j = 0; j < world.obstacles.size(); j++) {
            if (world.obstacles.get(j).getPosicio().getY() >= 399 && world.obstacles.get(j).getPosicio().getY() <= 400) {
                if (player.posicioPlayer.getX() <= world.obstacles.get(j).random || player.posicioPlayer.getX() >= world.obstacles.get(j).tm) {
                    xoc = true;
                }
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.player.render(gameContainer, graphics);
        this.world.render(gameContainer, graphics);
        //this.obstacle.render(gameContainer, graphics);
        //this.world.render(gameContainer, graphics);
        if (xoc) {
            graphics.drawString("Has xocat!!!", 200,200);
        }
    }
}
