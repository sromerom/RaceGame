import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class World {
    List<Obstacle> obstacles = new ArrayList<>();
    Rectangle rectangle;
    private float x, y;

    public void add() throws SlickException {
        Obstacle obstacle = new Obstacle();
        obstacles.add(obstacle);
    }

    public void delete() throws SlickException {
        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).getX() < 0) {
                obstacles.remove(i);
            }
        }
    }
    
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        add();

        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).render(gameContainer, graphics);
        }

    }

    public void update(GameContainer gameContainer, int i) throws SlickException {

    }
}
