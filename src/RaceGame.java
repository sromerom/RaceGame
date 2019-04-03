import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class RaceGame extends org.newdawn.slick.BasicGame {

    Obstacle obstacle = new Obstacle(new Punto(0, 0), new Punto(426, 0), new Velocitat(new Punto(0, 60)));
    Player player = new Player(new Punto(300, 400), new Velocitat(new Punto(0, 0)));
    World world = new World();
    int progresio = 1;
    int progressioVelocitat = 1;
    int progressioScore = 1000;
    String actualEstat = "";

    public RaceGame(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.player.init(gameContainer);
        actualEstat = "Funcionant";
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if (player.xoc) {
            actualEstat = "GameOver";
        }

        //Estats
        if (actualEstat.equals("Funcionant")) {
            this.player.update(gameContainer, i, world.obstacles);
            this.world.update(gameContainer, i);
            dificultat();
        }

        if (actualEstat.equals("GameOver")) {
            gameContainer.isPaused();
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        if (actualEstat.equals("Funcionant")) {
            this.world.render(gameContainer, graphics);
            this.player.render(gameContainer, graphics);
        }

        if (actualEstat.equals("GameOver")) {
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

    public void dificultat() {
        for (int j = 0; j < world.obstacles.size(); j++) {
            //System.out.println(world.obstacles.get(j).getPosicio().getY());
            /*
            if (player.getPasades() >= 15 && player.getPasades() < 35) {
                if (world.obstacles.get(j).getPosicio().getY() > 399.999 && world.obstacles.get(j).getPosicio().getY() <= 400.999) {
                    System.out.println("10++ " + player.getPasades());
                    System.out.println("He entrado >10");
                    player.setPasades(player.getPasades() + 1);
                    world.temps -= 20;
                }
            } else if (player.getPasades() >= 20 && player.getPasades() < 25) {
                if (world.obstacles.get(j).getPosicio().getY() > 399.999 && world.obstacles.get(j).getPosicio().getY() <= 400.999) {
                    System.out.println("20++ " + player.getPasades());
                    System.out.println("He entrado >20");
                    player.setPasades(player.getPasades() + 1);
                }
            } else if (player.getPasades() >= 25 && player.getPasades() < 42) {
                if (world.obstacles.get(j).getPosicio().getY() > 399.999 && world.obstacles.get(j).getPosicio().getY() <= 400.999) {
                    System.out.println("25++ " + player.getPasades());
                    System.out.println("He entrado >25");
                    player.setPasades(player.getPasades() + 1);
                    world.temps -= 25;
                }
            } else if (player.getPasades() >= 42) {
                if (world.obstacles.get(j).getPosicio().getY() > 399.999 && world.obstacles.get(j).getPosicio().getY() <= 400.999) {
                    System.out.println("42++ " + player.getPasades());
                    System.out.println("He entrado >42");
                    player.setPasades(player.getPasades() + 1);
                    world.temps = 1200;
                }
            } else {
            */
            if (player.getScore() == progressioScore) {
                System.out.println("inici " + player.getPasades());
                progressioScore += 1000;
                player.setPasades(player.getPasades() + 1);
            }
            /* } */
            if (player.getPasades() == progresio) {
                System.out.println(player.getPasades());
                System.out.println("Progressio: " + progresio);
                progresio += 1;
                player.setScore(player.getScore() + 500);

                if (progresio < 11) {
                    if (progresio % 2 != 0) {
                        world.temps -= 100;
                    }
                } else {
                    world.temps -= 50;
                }
                System.out.println(world.augmentaVelocitat);
            }

            if (player.getPasades() == progressioVelocitat) {
                System.out.println(world.augmentaVelocitat);
                world.augmentaVelocitat += 3;
                progressioVelocitat += 1;
            }
        }
    }
}
