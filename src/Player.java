import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Circle sc;
    private Punto posicioPlayer;
    private int score;
    private int maxScore;
    private int pasades;
    private Velocitat velocitat;
    boolean xoc = false;


    public Player(Punto posicioPlayer, Velocitat velocitat) {
        this.setPosicioPlayer(posicioPlayer);
        this.velocitat = velocitat;
    }

    public void init(GameContainer gameContainer) throws SlickException {
        this.setScore(0);
    }

    public void update(GameContainer gameContainer, int i, List<Obstacle> obst) throws SlickException {
        obst = new ArrayList<>();
        Input mou = gameContainer.getInput();
        if (mou.isKeyDown(Input.KEY_RIGHT)) {
            getPosicioPlayer().setX(getPosicioPlayer().getX() + 5);
        } else if (mou.isKeyDown(Input.KEY_LEFT)) {
            getPosicioPlayer().setX(getPosicioPlayer().getX() - 5);
        } else {
            getPosicioPlayer().setX(getPosicioPlayer().getX());
        }

        if (getPosicioPlayer().getX() < 0) {
            getPosicioPlayer().setX(0);
        } else if (getPosicioPlayer().getX() > 610) {
            getPosicioPlayer().setX(640 - 30);
        }
        this.score += 1;
        //colisions
        for (int j = 0; j < obst.size(); j++) {
            if (sc.intersects(obst.get(j).a2)) {
                System.out.println("Has chocado");
                this.xoc = true;
            }
        }
        System.out.println(xoc);
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        sc = new Circle(0, 0, 15, 15);
        sc.setX(getPosicioPlayer().getX());
        sc.setY(getPosicioPlayer().getY());
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

    public Punto getPosicioPlayer() {
        return posicioPlayer;
    }

    public void setPosicioPlayer(Punto posicioPlayer) {
        this.posicioPlayer = posicioPlayer;
    }
}
