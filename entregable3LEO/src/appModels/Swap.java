package appModels;

public class Swap extends Transaccion{
	
	private String monedaOrigen;
	private String monedaDestino;
	private double cantidadOrigen;
	private double cantidadDestino;
	public Swap(String fecha,String monedaOrigen, String monedaDestino, double cantidadOrigen, double cantidadDestino){
		super(fecha);
    this.cantidadDestino=cantidadDestino;
		this.cantidadOrigen=cantidadOrigen;
		this.monedaDestino=monedaDestino;
		this.monedaOrigen=monedaOrigen;
	}
	public String toString() {
		String  ret="Tipo: SWAP"
			  + "\nOrigen: " + monedaOrigen
			  +"\nDestino: " + monedaDestino 
			  +"\nMonto Origen: " + cantidadOrigen
			  +"\nMonto Destino: " + cantidadDestino;
		return ret;
	}
	
}
