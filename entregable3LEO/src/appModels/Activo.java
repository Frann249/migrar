package appModels;

public class Activo {
	private int id;
	private Moneda moneda;
	private double cantidad;
	/**
	 * @return the moneda
	 */

	public Activo(Moneda moneda, double cantidad){
		this.moneda = moneda;
		this.cantidad = cantidad;
	}

	/**
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the nomenclatura
	 */
	

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
