import org.newdawn.slick.*;

//Classe RaceGame on posarem el funcionament el joc i crearem els diferents estats que té el nostre joc.
public class RaceGame extends org.newdawn.slick.BasicGame {

    //Cream un player i un world
    Player player = new Player(new Coordenada(300, 400), new Velocitat(new Coordenada(0, 0)));
    World world = new World();
    private int progresio = 1;
    private int progressioVelocitat = 1;
    private int progressioScore = 1000;
    private Estat actualEstat;

    public enum Estat {
        INICI, JUGANT, PAUSE, GAMEOVER, REINICI
    }

    //Constructor RaceGame per cridar a la classe superior, la!!!!!!!!!!!!!!!!!!!!!
    public RaceGame(String gamename) {
        super(gamename);
    }

    @Override

    //Metode init on inicialitzam alguns parametres del joc
    public void init(GameContainer gameContainer) throws SlickException {
        this.player.init(gameContainer);
        actualEstat = Estat.INICI;
    }

    //Metode update on actualitzarem el player i el world segons sigui el estat actual.
    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input n = gameContainer.getInput();

        //Si el player xoca amb un obstacle passarem a actualitzar el estat actual del joc a "gameover". Una vegada aqui el jugador pot decidir si vol terminar el joc o seguir jugant.
        //Si es presiona la tecla enter el jugador reiniciara la partida i tornara a començar el joc, si presiona la tecla ESC el joc terminara
        if (player.xoc) {
            actualEstat = Estat.GAMEOVER;
            if (n.isKeyDown(Input.KEY_ENTER)) {

                player.setPosicioPlayer(new Coordenada(300, 400));
                actualEstat = Estat.REINICI;
                world.obstacles.removeAll(world.obstacles);
                player.xoc = false;
                player.setScore(0);
                player.setPasades(0);
                progresio = 1;
                progressioScore = 1000;
                progressioVelocitat = 1;
                world.setTemps(2000);
                world.setAugmentaVelocitat(60);

                if (actualEstat == Estat.REINICI) {
                    player.update(gameContainer, i, world.obstacles);
                    world.update(gameContainer, i);
                }
            }

            if (n.isKeyDown(Input.KEY_ESCAPE)) {
                gameContainer.exit();
            }

        } else {
            //Si el joc no esta en estat gameover vol dir que esta jugant (ja sigui la primera vegada que juga o quan ha reiniciat)
            //Si esta "jugant" o se ha "reiniciat" procedirem a actualitzar el player i el world un altra vegada. A més també carregarem la dificultat
            if (actualEstat == Estat.JUGANT || actualEstat == Estat.REINICI) {
                player.update(gameContainer, i, world.obstacles);
                world.update(gameContainer, i);
                dificultat();
            }

            //Si durant el joc el presiona la tecla p, la partida entrara en estat de pausa
            if (n.isKeyDown(Input.KEY_P)) {
                actualEstat = Estat.PAUSE;
                gameContainer.pause();
            }

            //Si el joc esta en estat de pause, haurem de sortir presionant la tecla ESC. Reanudarem la partida
            if (actualEstat == Estat.PAUSE) {
                if (n.isKeyDown(Input.KEY_ESCAPE)) {
                    actualEstat = Estat.JUGANT;
                    gameContainer.resume();
                }
            }

            //Si el actual estat es troba en el estat de inici vol dir que esta en el menu abans de jugar. Per iniciar la partida presionarem la tecla SPACE.
            if (actualEstat == Estat.INICI) {
                if (n.isKeyDown(Input.KEY_SPACE)) {
                    actualEstat = Estat.JUGANT;
                }
            }
        }

    }

    //Metode render on dibuixarem el player i el world sencer, també segons el estat variara el que es mostra per pantalla
    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        //Si el actual estat es jugant o reinici, simplement dibuixarem el player i el world.
        if (actualEstat == Estat.JUGANT || actualEstat == Estat.REINICI) {
            this.world.render(gameContainer, graphics);
            this.player.render(gameContainer, graphics);
            graphics.drawString("Nivell: " + this.progresio, 10, 50);
        }

        //En el moment que el player xoca i el estat es de gameover, deixarem de dibuixar el player i el world, i podem dir que dibuixarem el menu de quan es perd, de quan el estat esta
        //en gameover
        if (player.xoc) {
            if (actualEstat == Estat.GAMEOVER) {
                int actualScore;

                //Actualitzam maxscore actual nomes si el score actual el supera.
                if (player.getScore() > player.getMaxScore()) {
                    actualScore = player.getScore();
                    player.setMaxScore(actualScore);
                }

                //Pintam per pantalla el menu gameover
                graphics.drawString("Has xocat!!", 230, 200);
                graphics.drawString("Score: " + player.getScore(), 230, 240);
                graphics.drawString("MaxScore: " + player.getMaxScore(), 230, 280);

            }
        }

        //Si el estat esta en pausa, dibuixarem el menu de pausa
        if (actualEstat == Estat.PAUSE) {
            graphics.drawString("Game Paused", 230, 200);
        }

        //Si el estat actual esta en inici, dibuixarem el menu inicial del joc
        if (actualEstat == Estat.INICI) {
            graphics.drawString("Press SPACE BAR to start game", 175, 200);
            graphics.drawString("Controls:", 15, 380);
            graphics.drawString("Pause Mode: Press P", 15, 400);
            graphics.drawString("LEFT KEY AND RIGHT KEY to move the player", 15, 420);
            graphics.drawString("ESC to exit the game and the pause mode", 15, 440);

        }
    }

    //Metode dificultat on es crea un algoritme per anar pujant la dificultat segons el player va avançant en el lloc.
    //Es pot el progres de dificultat que té, o sigui, que la dificultat sigui mes exponencial
    public void dificultat() {
        for (int j = 0; j < world.obstacles.size(); j++) {

            //Cada vegada que el jugador va fent un score de 1000 en 1000 (1000, 1000, 2000, 3000 ,4000...etc) doncs sumarem la variable passada
            if (player.getScore() == progressioScore) {
                System.out.println("inici " + player.getPasades());
                progressioScore += 1000;
                player.setPasades(player.getPasades() + 1);
            }

            //Si les passada son iguals a la progressio, doncs augmentam el seu score en 250 i modificarem el controlador dels obstacles per tal de que es manteguin el mateix numero
            //de obstacles que el principi
            if (player.getPasades() == progresio) {
                System.out.println(player.getPasades());
                System.out.println("Progressio: " + progresio);
                progresio += 1;
                player.setScore(player.getScore() + 250);

                //Mentres la progressio sigui menor a 11 anirem llevant cada vagada 100 al controlador del objectes
                if (progresio < 11) {
                    if (progresio % 2 != 0) {
                        world.setTemps(world.getTemps() - 100);
                    }
                } else {
                    world.setTemps(world.getTemps() - 50);
                }
                //System.out.println(world.augmentaVelocitat);
            }

            //cada passada augmentam la velocitat en 3 als obstacles gràcies a la variable "getAugmentaVelocitat".
            if (player.getPasades() == progressioVelocitat) {
                System.out.println(world.getAugmentaVelocitat());
                world.setAugmentaVelocitat(world.getAugmentaVelocitat() + 3);
                progressioVelocitat += 1;
            }
        }
    }
}
