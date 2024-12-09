package appModels;

public abstract class Transaccion {
    private String fecha;

    public Transaccion(String fecha) {
        this.fecha = fecha;
    }

    // Getter y Setter para fecha
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public abstract String toString();
}