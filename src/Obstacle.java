import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;


//Classe obstacle a on crearem el nostre obstacle on despres crearem molts en world
public class Obstacle {

    //El nostre objecte consta de dos dibuixos. Un per el costat dret de la pantalla i un altre per l'esquerra
    Rectangle dibuixObstacle1;
    Rectangle dibuixObstacle2;
    private Coordenada posicioObstacle1;
    private Coordenada posicioObstacle2;
    private Velocitat velocitat;

    public float random = getRandom(0, 500);
    private int randomColors = (int) getRandom(1, 9);

    //Contructor a on un obejcte li passarem la posicio del obstacle1, la posicio del obstacle2, i la velocitat d'aquests
    public Obstacle(Coordenada posicioObstacle1, Coordenada posicioObstacle2, Velocitat velocitat) {
        this.setPosicioObstacle1(posicioObstacle1);
        this.setPosicioObstacle2(posicioObstacle2);
        this.setVelocitat(velocitat);
    }

    //metode render on dibuixam el nostre obstacle
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        Graphics gObstacle = new Graphics();
        //switch on assignara un color random al obstacles
        switch (randomColors) {
            case 1:
                gObstacle.setColor(Color.red);
                break;

            case 2:
                gObstacle.setColor(Color.yellow);
                break;

            case 3:
                gObstacle.setColor(Color.blue);
                break;
            case 4:
                gObstacle.setColor(Color.green);
                break;

            case 5:
                gObstacle.setColor(Color.orange);
                break;
            case 6:
                gObstacle.setColor(Color.cyan);
                break;
            case 7:
                gObstacle.setColor(Color.gray);
                break;
            case 8:
                gObstacle.setColor(Color.magenta);
                break;
            case 9:
                gObstacle.setColor(Color.pink);
                break;
            default:
                break;
        }

        //Assignam una posicio al obstacle1 i al obstacle2 i els dibuixam
        this.dibuixObstacle1 = new Rectangle(getPosicioObstacle1().getX(), getPosicioObstacle1().getY(), this.random, 10);
        this.dibuixObstacle2 = new Rectangle(getPosicioObstacle2().getX(), getPosicioObstacle2().getY(), 640 - (this.random + 140), 10);
        gObstacle.draw(dibuixObstacle1);
        gObstacle.draw(dibuixObstacle2);

    }

    //Metode UpdateN on assignam un moviment cap avall al obstacle. Gràcies a la formula velocitat = espai / temps podem deduïr aquesta velocitat.
    //Amb aquesta operació podrem actualitzar el obstacle cada vegada que el metode render el cridi. La posicio anira augmentant
    public void updateN(int delta) {
        //Formula per crear moviment constant als obstacles
        //posicionFinal = posicioInicial + velocitat * temps

        float temps = (float) delta / 1000;

        //Posicio final pel l'obstacle1
        float x = getPosicioObstacle1().getX() + getVelocitat().getXVelocitat() * temps;
        float y = getPosicioObstacle1().getY() + getVelocitat().getYVelocitat() * temps;

        //Posicio final pel l'obstacle2
        float x2 = getPosicioObstacle2().getX() + getVelocitat().getXVelocitat() * temps;
        float y2 = getPosicioObstacle2().getY() + getVelocitat().getYVelocitat() * temps;

        //assignam les noves coordenades que han sortit a la posicio actual del obstacle1 i del obstacle2
        this.setPosicioObstacle1(new Coordenada(x, y));
        this.setPosicioObstacle2(new Coordenada(x2, y2));
    }

    //Metode que ens torna un numero random entre un rang especificat
    public float getRandom(int min, int max) {
        return (float) Math.random() * (max - min) + min;
    }

    //Getters and Setters
    public Coordenada getPosicioObstacle1() {
        return posicioObstacle1;
    }

    public void setPosicioObstacle1(Coordenada posicioObstacle1) {
        this.posicioObstacle1 = posicioObstacle1;
    }

    public Coordenada getPosicioObstacle2() {
        return posicioObstacle2;
    }

    public void setPosicioObstacle2(Coordenada posicioObstacle2) {
        this.posicioObstacle2 = posicioObstacle2;
    }

    public Velocitat getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(Velocitat velocitat) {
        this.velocitat = velocitat;
    }
}

