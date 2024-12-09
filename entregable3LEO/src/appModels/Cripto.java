package appModels;
/**
 * Esta clase representa un tipo de Criptomoneda.
 */
public class Cripto extends Moneda {
	private double volatililidad;
	private double stock;
	/**
     * Convierte un monto de esta moneda a otra moneda.
     *
     * @param monto  El monto a convertir.
     * @param moneda La moneda de destino a la que se va a convertir.
     * @return El monto convertido en la moneda de destino.
     */
	public Cripto(int id){
		super(id);
	}
	public Cripto(String nombre_icono, String nombre, String nomenclatura){
		super(0,nombre_icono, nombre, nomenclatura, 0);
		this.volatililidad = 0;
		this.stock=0;
	}
	public Cripto(int id, String nombre_icono, String nombre, String nomenclatura, double valor_Dolar, double volatililidad, double stock){
		super(id,nombre_icono, nombre, nomenclatura, valor_Dolar);
		this.volatililidad = volatililidad;
		this.stock=stock;
	}
	/**
	 * @return the volatibilidad
	 */
	public double getvolatililidad() {
		return volatililidad;
	}
	/**
	 * @param volatibilidad the volatibilidad to set
	 */
	public void setVolatibilidad(double volatililidad) {
		this.volatililidad = volatililidad;
	}
	/**
	 * @return the stock
	 */
	public double getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(double stock) {
		this.stock = stock;
	}
	

}
