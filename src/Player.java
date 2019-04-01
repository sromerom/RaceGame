import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Player {
    private Circle sc;
    Punto posicioPlayer;
    private int score;


    public Player(Punto posicioPlayer) {
        sc = new Circle(0, 0, 15, 15);
        this.posicioPlayer = posicioPlayer;
    }

    public void init(GameContainer gameContainer) throws SlickException {
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

        sc.setX(posicioPlayer.getX());
        sc.setY(posicioPlayer.getY());

        graphics.draw(sc);


    }
}
