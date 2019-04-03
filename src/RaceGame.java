import org.newdawn.slick.*;

public class RaceGame extends org.newdawn.slick.BasicGame {


    Player player = new Player(new Punto(300, 400), new Velocitat(new Punto(0, 0)));
    World world = new World();
    int progresio = 1;
    int progressioVelocitat = 1;
    int progressioScore = 1000;
    Estat actualEstat;
    public enum Estat {
        INICI, JUGANT, PAUSE, GAMEOVER, REINICI
    }

    public RaceGame(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.player.init(gameContainer);
        actualEstat = Estat.INICI;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input n = gameContainer.getInput();
        System.out.println(actualEstat);

        if (player.xoc) {
            actualEstat = Estat.GAMEOVER;
            if (n.isKeyDown(Input.KEY_ENTER)) {
                actualEstat = Estat.REINICI;
                world.obstacles.removeAll(world.obstacles);
                player.xoc = false;
                player.setScore(0);
                if (actualEstat == Estat.REINICI) {
                    player.update(gameContainer, i, world.obstacles);
                    world.update(gameContainer, i);
                }
            }

            if (n.isKeyDown(Input.KEY_ESCAPE)) {
                gameContainer.exit();
            }

        } else {
            if (actualEstat == Estat.JUGANT || actualEstat == Estat.REINICI) {
                player.update(gameContainer, i, world.obstacles);
                world.update(gameContainer, i);
            }

            if (n.isKeyDown(Input.KEY_P)) {
                actualEstat = Estat.PAUSE;
                gameContainer.pause();
            }

            if (actualEstat == Estat.PAUSE) {
                if (n.isKeyDown(Input.KEY_ESCAPE)) {
                    actualEstat = Estat.JUGANT;
                    gameContainer.resume();
                }
            }

            if (actualEstat == Estat.INICI) {
                if (n.isKeyDown(Input.KEY_SPACE)) {
                    actualEstat = Estat.JUGANT;
                }
            }
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        if (actualEstat == Estat.JUGANT || actualEstat == Estat.REINICI) {
            this.world.render(gameContainer, graphics);
            this.player.render(gameContainer, graphics);
        }

        if (player.xoc) {
            if (actualEstat == Estat.GAMEOVER) {
                int actualScore;

                if (player.getScore() > player.getMaxScore()) {
                    actualScore = player.getScore();
                    player.setMaxScore(actualScore);
                }
                graphics.drawString("Has xocat!!", 230, 200);
                graphics.drawString("Score: " + player.getScore(), 230, 240);
                graphics.drawString("MaxScore: " + player.getMaxScore(), 230, 280);

                if (actualEstat == Estat.REINICI) {
                    graphics.drawString("Estat reiniciat", 230, 200);
                    this.world.render(gameContainer, graphics);
                    this.player.render(gameContainer, graphics);
                }
            }
        }
        if (actualEstat == Estat.PAUSE) {
            graphics.drawString("Game Paused", 230, 200);
        }

        if (actualEstat == Estat.INICI) {
            graphics.drawString("Press SPACE BAR to start game", 130, 200);
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
