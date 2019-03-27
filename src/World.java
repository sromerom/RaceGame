import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class World {
    List<Obstacle> obstacles = new ArrayList<>();

    public void add (float y) throws SlickException {
        Obstacle obstacle = new Obstacle(new Punto(600, y));
        obstacles.add(obstacle);
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
    }

    public void update(GameContainer gameContainer, int i) throws SlickException {

    }
}
