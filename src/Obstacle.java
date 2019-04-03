import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Obstacle {

    private Punto posicio;
    private Punto posicio2;
    private Velocitat velocitat;
    Rectangle a;
    Rectangle a2;
    float random = getRandom();
    public float tm = random + 140;


    public Obstacle(Punto posicio, Punto posicio2, Velocitat velocitat) {
        this.setPosicio(posicio);
        this.setPosicio2(posicio2);
        this.velocitat = velocitat;
    }

    public float getRandom() {
        int min = 0;
        int max = 500;
        return (float) Math.random() * (max - min) + min;
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        //posicio = new Punto(0, 320);
        //posicio2 = new Punto(426, 320);
        a = new Rectangle(getPosicio().getX(), getPosicio().getY(), random, 10);
        a2 = new Rectangle(getPosicio2().getX(), getPosicio2().getY(), 640 - (random + 140), 10);
        graphics.draw(a);
        graphics.draw(a2);

    }

    public Punto getPosicio() {
        return posicio;
    }

    public void setPosicio(Punto posicio) {
        this.posicio = posicio;
    }

    public Punto getPosicio2() {
        return posicio2;
    }

    public void setPosicio2(Punto posicio2) {
        this.posicio2 = posicio2;
    }

    public Velocitat getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(Velocitat velocitat) {
        this.velocitat = velocitat;
    }

    public void updateN(int delta) {
        float x = posicio.getX() + velocitat.getXVelocitat() * ((float) delta / 1000);
        float y = posicio.getY() + velocitat.getYVelocitat() * ((float) delta / 1000);
        float x2 = posicio2.getX() + velocitat.getXVelocitat() * ((float) delta / 1000);
        float y2 = posicio2.getY() + velocitat.getYVelocitat() * ((float) delta / 1000);
        this.setPosicio(new Punto(x, y));
        this.setPosicio2(new Punto(x2, y2));
    }
}


class Punto {
    private float x;
    private float y;

    public Punto(float x, float y) {

        this.setX(x);
        this.setY(y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}


