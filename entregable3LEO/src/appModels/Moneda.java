package appModels;
/**
 * Esta clase representa la mayor abstraccion de los tipos de monedas.
 */

public abstract class Moneda {
	private String nombre;
	private int id;
	private String nomenclatura;
	private double valor_Dolar;
	private String nombre_icono;
	
	/**
     * Convierte un monto de esta moneda a otra moneda.
     *
     * @param monto  El monto a convertir.
     * @param moneda La moneda de destino a la que se va a convertir.
     * @return El monto convertido en la moneda de destino.
     */
	Moneda(int id){
		this.id = id;
	}
	Moneda(int id, String nombre_icono,String nombre, String nomenclatura, double valor_Dolar){
		this.setId(id);
		this.nombre = nombre;
		this.nomenclatura= nomenclatura;
		this.valor_Dolar= valor_Dolar;
		this.nombre_icono = nombre_icono;
	}
	public double convertir(double montoOrigen, Moneda monedaDestino) {
		return montoOrigen * (this.getValor_Dolar() / monedaDestino.getValor_Dolar());
	}

	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the nomenclatura
	 */
	public String getNomenclatura() {
		return nomenclatura;
	}
	/**
	 * @param nomenclatura the nomenclatura to set
	 */
	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}
	public boolean equals(Moneda moneda) {
		return (this.nomenclatura.equals(moneda.getNomenclatura()));
	}
	public boolean equals(String nomenclatura) {
		return (this.nomenclatura.equals(nomenclatura));
	}
	/**
	 * @return the valor_Dolar
	 */
	public double getValor_Dolar() {
		return valor_Dolar;
	}
	/**
	 * @param valor_Dolar the valor_Dolar to set
	 */
	public void setValor_Dolar(double valor_Dolar) {
		this.valor_Dolar = valor_Dolar;
	}
	/**
	 * @return the nombre_icono
	 */
	public String getNombre_icono() {
		return nombre_icono;
	}
	/**
	 * @param nombre_icono the nombre_icono to set
	 */
	public void setNombre_icono(String nombre_icono) {
		this.nombre_icono = nombre_icono;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
