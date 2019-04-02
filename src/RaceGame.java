import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class RaceGame extends org.newdawn.slick.BasicGame {

    Obstacle obstacle = new Obstacle(new Punto(0, 0), new Punto(426, 0), new Velocitat(new Punto(0, 60)));
    Player player = new Player(new Punto(300, 400), new Velocitat(new Punto(0,0)));
    World world = new World();

    public RaceGame(String gamename) {
        super(gamename);
        bonificacioScore();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.player.init(gameContainer);

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        //aturar el lloc si fallam
        if (!player.xoc) {
            this.player.update(gameContainer, i, world.obstacles);
            this.world.update(gameContainer, i);
        } else {
            gameContainer.sleep(3000);
            gameContainer.exit();
        }

        //colisions
        /*
        for (int j = 0; j < world.obstacles.size(); j++) {
            //System.out.println(world.obstacles.get(j).getPosicio().getY());
            if (world.obstacles.get(j).getPosicio().getY() > 399 && world.obstacles.get(j).getPosicio().getY() < 401) {
                if ((player.getPosicioPlayer().getX()) + 30 >= world.obstacles.get(j).tm ||
                        player.getPosicioPlayer().getX() <= world.obstacles.get(j).random) {
                    xoc = true;
                }
            }
        }
*/

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (!player.xoc) {
            this.world.render(gameContainer, graphics);
            this.player.render(gameContainer, graphics);
        } else {
            int actualScore;

            if (player.getScore() > player.getMaxScore()) {
                actualScore = player.getScore();
                player.setMaxScore(actualScore);
            }
            graphics.drawString("Has xocat!!", 230, 200);
            graphics.drawString("Score: " + player.getScore(), 230, 240);
            graphics.drawString("MaxScore: " + player.getMaxScore(), 230, 280);
        }
    }

    public void bonificacioScore() {
        for (int j = 0; j < world.obstacles.size(); j++) {
            if (world.obstacles.get(j).getPosicio().getY() >= 395 && world.obstacles.get(j).getPosicio().getY() <= 405) {
                System.out.println("sumas un punto!!");
                player.setPasades(player.getPasades() + 1);
            }
            if (player.getPasades() == 2) {
                player.setScore(player.getScore() + 1000000);
            }
        }
    }
}
