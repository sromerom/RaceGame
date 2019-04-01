import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Player {
    Circle sc;
    //Rectangle sc = new Rectangle(0, 0, 30, 30);

    Punto posicioPlayer;

    private int score;
    private float x;
    private float y;

    public Player(Punto posicioPlayer) {
        this.posicioPlayer = posicioPlayer;
    }
    public void init(GameContainer gameContainer) throws SlickException {
        Player jugador1 = new Player(new Punto(300, 400));

    }

    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input mou = gameContainer.getInput();
        if (mou.isKeyDown(Input.KEY_RIGHT)) {

            posicioPlayer.setX(posicioPlayer.getX() + 5);
        }

        if (mou.isKeyDown(Input.KEY_LEFT)) {
            posicioPlayer.setX(posicioPlayer.getX() - 5);

        }
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        sc = new Circle(0, 0, 15, 15);
        //graphics.drawString("Això és una prova!!", this.x, this.y);

        sc.setX(posicioPlayer.getX());
        sc.setY(posicioPlayer.getY());

        graphics.draw(sc);


    }
}
