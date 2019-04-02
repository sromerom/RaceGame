import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Player {
    private Circle sc;
    Punto posicioPlayer;
    private int score;
    private int maxScore;
    private int pasades;
    private Velocitat velocitat;


    public Player(Punto posicioPlayer, Velocitat velocitat) {
        this.posicioPlayer = posicioPlayer;
        this.velocitat = velocitat;
    }

    public void init(GameContainer gameContainer) throws SlickException {
        this.setScore(0);
    }

    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input mou = gameContainer.getInput();
        if (mou.isKeyDown(Input.KEY_RIGHT)) {
            posicioPlayer.setX(posicioPlayer.getX() + 5);
        } else if (mou.isKeyDown(Input.KEY_LEFT)) {
            posicioPlayer.setX(posicioPlayer.getX() - 5);
        } else {
            posicioPlayer.setX(posicioPlayer.getX());
        }

        if (posicioPlayer.getX() < 0) {
            posicioPlayer.setX(0);
        } else if (posicioPlayer.getX() > 610) {
            posicioPlayer.setX(640 - 30);
        }
        this.score += 1;
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        sc = new Circle(0, 0, 15, 15);
        sc.setX(posicioPlayer.getX());
        sc.setY(posicioPlayer.getY());
        graphics.drawString("Score: " + this.score, 10, 10);
        graphics.draw(sc);


    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPasades() {
        return pasades;
    }

    public void setPasades(int pasades) {
        this.pasades = pasades;
    }

    public Velocitat getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(Velocitat velocitat) {
        this.velocitat = velocitat;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
