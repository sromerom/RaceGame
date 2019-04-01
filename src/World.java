import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class World {
    //private Obstacle c = new Obstacle(new Punto(0, 320), new Punto(426, 320), new Velocitat(new Punto(0, 60)));
    //List<Obstacle> obstacles = new ArrayList<>();
    private List<Obstacle> obstacles = new ArrayList<>();
    int controlador = 0;

    public void add() throws SlickException {
        Obstacle obstacle = new Obstacle(new Punto(0, 0), new Punto(426, 0), new Velocitat(new Punto(0, 60)));
        obstacles.add(obstacle);
    }

    public void delete() throws SlickException {
        for (int i = 0; i < obstacles.size(); i++) {
        }
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).render(gameContainer, graphics);
        }
    }

    public void update(GameContainer gameContainer, int i) throws SlickException {
        for (int j = 0; j < obstacles.size(); j++) {
            obstacles.get(j).updateN(i);
        }
        this.controlador += i;
        if (controlador > 2000) {
            add();
            controlador = 0;
        }
    }
}
