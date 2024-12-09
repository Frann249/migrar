package appDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import app.MyConnection;
import appModels.Cripto;
import appModels.Fiat;
import appModels.Moneda;

public class MonedaDAOjdbc implements MonedaDAO{
	
	MonedaDAOjdbc() {	
	}
	public boolean guardarMoneda(Moneda moneda) {
		Connection con = null;
		String sql = null;
		if(moneda instanceof Cripto) {
			Cripto cripto = (Cripto) moneda;
			sql ="INSERT INTO MONEDA VALUES "
					+ "( NULL , 'C', '"
					+ cripto.getNombre() +"', '"
					+ cripto.getNomenclatura() +"', '"
					+ cripto.getValor_Dolar() + "', '"
					+ cripto.getvolatililidad()+ "', '"
					+ cripto.getStock() + "', '"
					+ cripto.getNombre_icono()+"')";
		}
		else if(moneda instanceof Fiat) {
			sql ="INSERT INTO MONEDA VALUES "
					+ "( NULL , 'F', '"
					+ moneda.getNombre() +"', '"
					+ moneda.getNomenclatura() +"', '"
					+ moneda.getValor_Dolar() + "', NULL , NULL , '"
					+ moneda.getNombre_icono()+"')";
		}
		try {
			con = MyConnection.getCon();
			Statement stmt = con.createStatement();
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	return true;
	    	} 
		catch (SQLException e){
	    	//System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
	    	return false;
	    }
	}
	public Moneda existeMoneda(String nomenclatura) {
		String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA=\""+nomenclatura+"\"";
		Connection con = null;
		try {
        	con = MyConnection.getCon();

            Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);
            if (!resul.next()){
          		resul.close();
          		sent.close();
          		return null;
        	}
            String valor = resul.getString("TIPO");
          	char car = valor.charAt(0);
          	Moneda moneda;
          	if ( car == 'C') {
          		int id = resul.getInt("ID");
          		String nombre_icono = resul.getString("NOMBRE_ICONO");
  				String nombre = resul.getString("NOMBRE");
  	          	double valorDolar = resul.getDouble("VALOR_DOLAR");
  	          	double volatibilidad = resul.getDouble("VOLATILIDAD");
  	          	double stock = resul.getDouble("STOCK");
  				moneda = new Cripto(id,nombre_icono, nombre, nomenclatura,valorDolar,volatibilidad,stock);
  			}
  			else {
  				int id = resul.getInt("ID");
  				String nombre_icono = resul.getString("NOMBRE_ICONO");
  				String nombre = resul.getString("NOMBRE");
  	          	double valorDolar = resul.getDouble("VALOR_DOLAR");
  	          	moneda= new Fiat(id,nombre_icono, nombre,nomenclatura,valorDolar);
  			}
          	return moneda;
		}
		catch (SQLException e){
			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
			return null;
		}
	}
	public boolean obtenerMonedaID(Moneda moneda) {
		String sql = "SELECT * FROM MONEDA WHERE ID=\""+moneda.getId()+"\"";
		Connection con = null;
		try {
        	con = MyConnection.getCon();

            Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);
            if (!resul.next()){
          		resul.close();
          		sent.close();
          		return false;
        	}
            String valor = resul.getString("TIPO");
          	char car = valor.charAt(0);
          	if ( car == 'C') {
          		Cripto cripto = (Cripto) moneda;
          		cripto.setNombre_icono(resul.getString("NOMBRE_ICONO"));
  				cripto.setNombre(resul.getString("NOMBRE"));
  				cripto.setNomenclatura(resul.getString("NOMENCLATURA"));
  	          	cripto.setValor_Dolar(resul.getDouble("VALOR_DOLAR"));
  	          	cripto.setVolatibilidad(resul.getDouble("VOLATILIDAD"));
  	          	cripto.setStock(resul.getDouble("STOCK"));
  			}
  			else {
  				moneda.setNomenclatura(resul.getString("NOMENCLATURA"));
  				moneda.setNombre_icono(resul.getString("NOMBRE_ICONO"));
  				moneda.setNombre(resul.getString("NOMBRE"));
  	          	moneda.setValor_Dolar(resul.getDouble("VALOR_DOLAR"));
  			}
          	return true;
		}
		catch (SQLException e){
			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
			return false;
		}
	}
	public List<Moneda> listarMonedas(){
		String sql = "SELECT * FROM MONEDA ";
    	List<Moneda> lista;
      	Connection con = null;
      	try {
        	con = MyConnection.getCon();

            Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);
                
        	lista = new LinkedList<Moneda>();
            if (!resul.next()){
          		resul.close();
          		sent.close();
          		return lista; // error 1: no se encontraron resultados
        	}
          	else {
              	lista = new LinkedList<Moneda>();
          		do {
          			String valor = resul.getString("TIPO");
          			char car = valor.charAt(0);
          			
          			if ( car == 'C') {
          				int id = resul.getInt("ID");
          				String nombre_icono = resul.getString("NOMBRE_ICONO");
          				String nombre = resul.getString("NOMBRE");
          	          	String nomenclatura = resul.getString("NOMENCLATURA");
          	          	double valorDolar = resul.getDouble("VALOR_DOLAR");
          	          	double volatibilidad = resul.getDouble("VOLATILIDAD");
          	          	double stock = resul.getDouble("STOCK");
          	          	
          				Cripto c = new Cripto(id,nombre_icono, nombre, nomenclatura,valorDolar,volatibilidad,stock);
          				lista.add(c);
          			}
          			else {
          				int id = resul.getInt("ID");
          				String nombre_icono = resul.getString("NOMBRE_ICONO");
          				String nombre = resul.getString("NOMBRE");
          	          	String nomenclatura = resul.getString("NOMENCLATURA");
          	          	double valorDolar = resul.getDouble("VALOR_DOLAR");
          	          	
          	          	Fiat f = new Fiat(id, nombre_icono, nombre,nomenclatura,valorDolar);
          	          	lista.add(f);
          			}
          		} while ( resul.next());
            }
        	resul.close();
       		sent.close();
        	return lista; // La función finalizó con éxito          	
        } catch (SQLException e) {
      		System.out.println(e.getMessage());
      		return null; // error 2: SQLException
      	}
	}
	public List<Moneda> listarCripto(){
		String sql = "SELECT * FROM MONEDA WHERE TIPO='C'";
    	List<Moneda> lista;
      	Connection con = null;
      	try {
        	con = MyConnection.getCon();

            Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);
                
        	lista = new LinkedList<Moneda>();
            if (!resul.next()){
          		resul.close();
          		sent.close();
          		return lista; // error 1: no se encontraron resultados
        	}
          	else {
          		do {
          			int id = resul.getInt("ID");
          			String nombre_icono = resul.getString("NOMBRE_ICONO");
          			String nombre = resul.getString("NOMBRE");
          	        String nomenclatura = resul.getString("NOMENCLATURA");
          	        double valorDolar = resul.getDouble("VALOR_DOLAR");
          	        double volatibilidad = resul.getDouble("VOLATILIDAD");
          	        double stock = resul.getDouble("STOCK");
          			Cripto c = new Cripto(id,nombre_icono, nombre, nomenclatura,valorDolar,volatibilidad,stock);
          			lista.add(c);
          	} while ( resul.next());
          }
        	resul.close();
       		sent.close();
        	return lista; // La función finalizó con éxito          	
        } catch (SQLException e) {
      		System.out.println(e.getMessage());
      		return null; // error 2: SQLException
      	}
	}
	public List<Moneda> listarFiat(){
		String sql = "SELECT * FROM MONEDA WHERE TIPO='F'";
    	List<Moneda> lista;
      	Connection con = null;
      	try {
        	con = MyConnection.getCon();

            Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);
        	lista = new LinkedList<Moneda>();
            if (!resul.next()){
          		resul.close();
          		sent.close();
          		return lista; // error 1: no se encontraron resultados
        	}
          	else {
          		do {
          			int id = resul.getInt("ID");
          			String nombre_icono = resul.getString("NOMBRE_ICONO");
      				String nombre = resul.getString("NOMBRE");
      	          	String nomenclatura = resul.getString("NOMENCLATURA");
      	          	double valorDolar = resul.getDouble("VALOR_DOLAR");
      	          	
      	          	Fiat f = new Fiat(id,nombre_icono, nombre,nomenclatura,valorDolar);
      	          	lista.add(f);
          	} while ( resul.next());
          }
        	resul.close();
       		sent.close();
        	return lista; // La función finalizó con éxito          	
        } catch (SQLException e) {
      		System.out.println(e.getMessage());
      		return null; // error 2: SQLException
      	}
	}
	public boolean actualizarValor(Moneda moneda) {
		String sql = "UPDATE MONEDA SET VALOR_DOLAR=\""+ moneda.getValor_Dolar()+"\" WHERE ID=\""+moneda.getId()+"\"";
		Connection con= null;
		try {
			con = MyConnection.getCon();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
	    	stmt.close();
	   } 
		catch (SQLException e){
			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
			return false;
	   }
		return true;
	}
    public boolean actualizarStock(Cripto cripto) {
    	String sql ="UPDATE MONEDA set STOCK='"+ cripto.getStock() + "' WHERE NOMENCLATURA=\""+cripto.getNomenclatura()+"\"";
		Connection con= null;
		try {
			con = MyConnection.getCon();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
	    	stmt.close();
	   } 
		catch (SQLException e){
			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
			return false;
	   }
		return true;
	}
	@Override
	public boolean isEmpty() {
		String sql = "SELECT * FROM MONEDA ";
    	List<Moneda> lista;
      	Connection con = null;
      	try {
        	con = MyConnection.getCon();

            Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);
                
        	lista = new LinkedList<Moneda>();
            if (!resul.next()){
          		resul.close();
          		sent.close();
          		return true;
        	}
            resul.close();
      		sent.close();
            return false;
            	
      	}
      	catch (SQLException e){
			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
			return false;
	   }
	}
}
