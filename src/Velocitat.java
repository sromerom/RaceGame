//Classe Velocitat que ens permetra assignar una velocitat a un objecte del nostre joc

public class Velocitat {
    private Coordenada origen;
    private Coordenada desti;

    //Constructor de Velocitat a on l'hi passam com a parametre l'origen i el destí ()
    public Velocitat(Coordenada origen, Coordenada desti) {
        super();
        this.setOrigen(origen);
        this.setDesti(desti);
    }

    //Constructor de Velocitat a on només l'hi passem per parametre el destí
    public Velocitat(Coordenada desti) {
        this(new Coordenada(0, 0), desti);
    }

    public float getXVelocitat() {
        //Coordenada = desti - origin;
        return getDesti().getX() - getOrigen().getX();
    }

    public float getYVelocitat() {
        //Coordenada = desti - origin;
        return getDesti().getY() - getOrigen().getY();
    }

    //Getters and Setters
    public Coordenada getOrigen() {
        return origen;
    }

    public void setOrigen(Coordenada origen) {
        this.origen = origen;
    }

    public Coordenada getDesti() {
        return desti;
    }

    public void setDesti(Coordenada desti) {
        this.desti = desti;
    }
}
