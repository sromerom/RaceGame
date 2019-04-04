//Classe Coordenada que ens permetra assignar una coordenada x, y per cada objecte d'una manera senzilla i ordenada

public class Coordenada {
    private float x;
    private float y;

    public Coordenada(float x, float y) {
        this.x = x;
        this.y = y;
    }

    //Getters and Setters
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
