import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import java.util.List;


//Classe Player a on cream un player per jugar amb ell
public class Player {
    public Circle sc;
    private Coordenada posicioPlayer;
    private int score;
    private int maxScore;
    private int pasades;
    private Velocitat velocitat;
    boolean xoc = false;

    //Constructor Player a on l'hi assignarem una posicio i una velocitat al player
    public Player(Coordenada posicioPlayer, Velocitat velocitat) {
        this.posicioPlayer = posicioPlayer;
        this.velocitat = velocitat;
    }

    //Metode init on assignarem alguns parametres inicials al player
    public void init(GameContainer gameContainer) throws SlickException {
        this.setScore(0);
        sc = new Circle(0, 0, 15, 15);
    }

    //Metode update a on assignarem moviment al player cada vegada que pulsem la tecla esquerra i dreta. A més, crearem les colision quan el player es xoca amb un obstacle
    public void update(GameContainer gameContainer, int i, List<Obstacle> cv) throws SlickException {
        Input mou = gameContainer.getInput();

        //Si el jugador presiona la tecla dreta, haurem de sumar 5 a la seva posicio X
        if (mou.isKeyDown(Input.KEY_RIGHT)) {
            this.posicioPlayer.setX(this.posicioPlayer.getX() + 5);
            //Si el jugador presiona la tecla esquerra, haurem de restar 5 la seva posicio X
        } else if (mou.isKeyDown(Input.KEY_LEFT)) {
            this.posicioPlayer.setX(this.posicioPlayer.getX() - 5);
            //Si no es presiona res, simplement deixam la posicio del player actual
        } else {
            this.posicioPlayer.setX(this.posicioPlayer.getX());
        }

        //Limits de pantalla pel player.
        //Per tal de que el player no es sortis del marge de la pantalla i asi poder fet trampes, farem que quan la posicio X del jugador es menor a 0, li assignarem la posicio 0,
        //i si la posicio del player es mes gran al tamany de la pantalla - diametre del cercle player, o sigui, 610, doncs li assignarem un altre vegada al maxim de pantalla, 610
        if (posicioPlayer.getX() < 0) {
            getPosicioPlayer().setX(0);
        } else if (posicioPlayer.getX() > (640 - 30)) {
            getPosicioPlayer().setX(640 - 30);
        }

        //El score s'anira augmentant en un, cada ms s'augmentara 1
        this.setScore(this.score + 1);

        //Gestor de colisions
        //Amb un for recorrem cada objecte de la llista que ens passen i veurem si el dibuix del obstacle1 i si el dibuix del obstacle2 intersionen entre amb el dibuix del player.
        //Aixo es molt simple gràcies al metode de la llibreria "intersects". Si xoquen, doncs posarem la variable booleana xoc en true.
        for (int j = 0; j < cv.size(); j++) {
            if (sc.intersects(cv.get(j).dibuixObstacle1) || sc.intersects(cv.get(j).dibuixObstacle2)) {
                xoc = true;
            }
        }
    }

    //En el metode render dibuixarem el player per pantalla i les seves estadistiques
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        sc.setX(getPosicioPlayer().getX());
        sc.setY(getPosicioPlayer().getY());

        //Dibuixam el Score i el MaxScore del jugador. Nomes surt quan s'esta jugant.
        graphics.drawString("Score: " + this.getScore(), 10, 10);
        graphics.drawString("MaxScore: " + this.getMaxScore(), 10, 30);
        graphics.draw(sc);
    }

    //Getters and Setters
    public Coordenada getPosicioPlayer() {
        return posicioPlayer;
    }

    public void setPosicioPlayer(Coordenada posicioPlayer) {
        this.posicioPlayer = posicioPlayer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getPasades() {
        return pasades;
    }

    public void setPasades(int pasades) {
        this.pasades = pasades;
    }
}
