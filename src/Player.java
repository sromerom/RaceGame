import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Player {
    Circle sc;
    //Rectangle sc = new Rectangle(0, 0, 30, 30);
    private int score;
    private float x;
    private float y;

    public Player() {
    }
    public void init(GameContainer gameContainer) throws SlickException {
        this.x = 300;
        this.y = 400;
    }

    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input mou = gameContainer.getInput();
        if (mou.isKeyDown(Input.KEY_RIGHT)) {
            x += 5;
        }

        if (mou.isKeyDown(Input.KEY_LEFT)) {
            x -= 5;
        }
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        sc = new Circle(0, 0, 15, 15);
        //graphics.drawString("Això és una prova!!", this.x, this.y);
        sc.setX(this.x);
        sc.setY(this.y);
        graphics.draw(sc);


    }
}
