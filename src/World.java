import org.newdawn.slick.*;
import java.util.ArrayList;
import java.util.List;


//Classe world on crearem molts d'objectes gràcies a una llista i que despres dibuixarem en el world
public class World {

    List<Obstacle> obstacles = new ArrayList<>();
    private int controlador = 0;
    private int augmentaVelocitat = 60;
    private int temps = 2000;


    //metode render on dibuixarem cada un dels elements de la llista (els obstacles)
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).render(gameContainer, graphics);
        }
    }

    //Metode update on actualitzarem i assignarem una velocitat a cada un dels elements de la llista (els obstacles)
    public void update(GameContainer gameContainer, int i) throws SlickException {
        for (int j = 0; j < obstacles.size(); j++) {
            obstacles.get(j).updateN(i);
        }
        //Cridam a la funcio delete per esborrar els obstacles innecesaris
        delete();

        //Controlador d'objectes.
        //Amb aquest controlador controlarem que el objectes sortin nomes cada certs temps, per defecte cada 2000 ms sortira un obstacle. En la classe dificultat s'anira modificant
        //per tal de que el joc sigui mes dificil.
        //Nomes s'afegira un obstacle quan el delta del update sigui major a 2000ms. Els afegirem amb el metode add(). Important tornar a posar el controlador a 0.
        this.controlador += i;
        if (this.controlador > this.temps) {
            System.out.println(this.temps);
            add();
            setControlador(0);
        }
    }

    //Metode add on ficam els objectes obstacles en la llista
    //La variable getAugmentaVelocitat s'anira modificant segons la dificultat del joc.
    public void add() throws SlickException {
        Obstacle obstacle = new Obstacle(new Coordenada(0, 0), new Coordenada(426, 0), new Velocitat(new Coordenada(0, getAugmentaVelocitat())));
        float desPosicio = obstacle.random + 140;
        obstacle.setPosicioObstacle2(new Coordenada(desPosicio, 0));

        obstacles.add(obstacle);
    }

    //Metode delete que ens permet esborrar un obstacle de la llista. Quan ja no surt per pantalla l'eliminam
    public void delete() throws SlickException {
        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).getPosicioObstacle1().getY() >= 460) {
                obstacles.remove(i);
            }
        }
    }

    //Getters and Setters
    public int getControlador() {
        return controlador;
    }

    public void setControlador(int controlador) {
        this.controlador = controlador;
    }

    public int getAugmentaVelocitat() {
        return augmentaVelocitat;
    }

    public void setAugmentaVelocitat(int augmentaVelocitat) {
        this.augmentaVelocitat = augmentaVelocitat;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
}

