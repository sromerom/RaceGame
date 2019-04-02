public class Velocitat {
    private Punto origen;
    private Punto desti;

    public Velocitat(Punto origen, Punto desti) {
        super();
        this.origen = origen;
        this.desti = desti;
    }

    public Velocitat(Punto desti) {
       this(new Punto(0,0), desti);
    }

    public Punto getOrigen() {
        return origen;
    }

    public void setOrigen(Punto origen) {
        this.origen = origen;
    }

    public Punto getDesti() {
        return desti;
    }

    public void setDesti(Punto desti) {
        this.desti = desti;
    }

    public float getXVelocitat() {
       return desti.getX() - origen.getX();
    }

    public float getYVelocitat() {
        return desti.getY() - origen.getY();
    }
}
