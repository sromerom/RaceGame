import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws SlickException {
        try {
            AppGameContainer gc;
            gc = new AppGameContainer(new RaceGame("MyGame"));
            gc.setDisplayMode(640, 480, false);
            gc.setTargetFrameRate(60);
            //gc.setShowFPS(false);
            gc.start();
        } catch (SlickException ex) {
            Logger.getLogger(RaceGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
