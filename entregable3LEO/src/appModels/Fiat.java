package appModels;

/**
 * Esta clase representa un tipo de moneda fiduciaria.
 */
public class Fiat extends Moneda {
	
	public Fiat(int id){
		super(id);
	}
    public Fiat(int id,String nombre_Icono, String nombre, String nomenclatura, double valor_Dolar){
		super(id, nombre_Icono, nombre, nomenclatura, valor_Dolar);
	}
    public Fiat(String nombre_Icono, String nombre, String nomenclatura, double valor_usd){
		super(0,nombre_Icono, nombre, nomenclatura,valor_usd);
	}
}
