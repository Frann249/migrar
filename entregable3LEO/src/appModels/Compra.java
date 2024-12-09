package appModels;

public class Compra extends Transaccion {
    private String fiatOrigen;
    private String criptoDestino;
    private double cantidadOrigen;
    private double cantidadDestino;

    public Compra(String fecha, String fiatOrigen, String criptoDestino, double cantidadOrigen, double cantidadDestino) {
        super(fecha);
        this.fiatOrigen = fiatOrigen;
        this.criptoDestino = criptoDestino;
        this.cantidadOrigen = cantidadOrigen;
        this.cantidadDestino = cantidadDestino;
    }

    // Getters y Setters
    public String getFiatOrigen() {
        return fiatOrigen;
    }

    public void setFiatOrigen(String fiatOrigen) {
        this.fiatOrigen = fiatOrigen;
    }

    public String getCriptoDestino() {
        return criptoDestino;
    }

    public void setCriptoDestino(String criptoDestino) {
        this.criptoDestino = criptoDestino;
    }

    public double getCantidadOrigen() {
        return cantidadOrigen;
    }

    public void setCantidadOrigen(double cantidadOrigen) {
        this.cantidadOrigen = cantidadOrigen;
    }

    public double getCantidadDestino() {
        return cantidadDestino;
    }

    public void setCantidadDestino(double cantidadDestino) {
        this.cantidadDestino = cantidadDestino;
    }

    @Override
    public String toString() {
        return "Tipo: Compra Cripto" +
               "\nMoneda fiat usada: " + fiatOrigen +
               "\nMoneda cripto comprada: " + criptoDestino +
               "\nMonto fiat usado: " + cantidadOrigen +
               "\nMonto cripto comprado: " + cantidadDestino;
    }
}
