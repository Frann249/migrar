package appDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import app.MyConnection;
import appModels.Activo;
import appModels.Cripto;
import appModels.Fiat;
import appModels.Moneda;



public class ActivoDAOjdbc implements ActivoDAO{
	
	public List<Activo> listarActivosCripto(int id_usuario){
		String sql = "SELECT * FROM ACTIVO_CRIPTO WHERE ID_USUARIO=\""+id_usuario+"\"";
		Connection con = null;
      	List<Activo> lista = new LinkedList<Activo>();
      	try {
        	con = MyConnection.getCon();
          	Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);      
            while(resul.next()) {
            	int id_moneda= resul.getInt("ID_MONEDA");
              	double cantidad = resul.getDouble("CANTIDAD");
              	Activo activo = new Activo(new Cripto(id_moneda), cantidad);
              	lista.add(activo);
              }
            resul.close();
           	sent.close();
            return lista; // La función finalizó con éxito          	
        } catch (SQLException e) {
//      		System.out.println(e.getMessage());
      		return null; // error 2: SQLException
      	}
	}
	public List<Activo> listarActivosFiat(int id_usuario){
		String sql = "SELECT * FROM ACTIVO_FIAT WHERE ID_USUARIO=\""+id_usuario+"\"";
		Connection con = null;
      	List<Activo> lista = new LinkedList<Activo>();
      	try {
        	con = MyConnection.getCon();
          	Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);      
            while(resul.next()) {
            	
            	int id_moneda= resul.getInt("ID_MONEDA");
              	double cantidad = resul.getDouble("CANTIDAD");
              	Activo activo = new Activo(new Fiat(id_moneda), cantidad);
              	lista.add(activo);
              }
            resul.close();
           	sent.close();
            return lista; // La función finalizó con éxito          	
        } catch (SQLException e) {
//      		System.out.println(e.getMessage());
      		return null; // error 2: SQLException
      	}
	}
	public List<Activo> listarActivos(int id_usuario){
      	List<Activo> lista = this.listarActivosCripto(id_usuario);
        lista.addAll(this.listarActivosFiat(id_usuario));
        return lista;
	}
	
	public boolean actualizarActivo(Activo activo) {
		String sql = null;
		if(activo.getMoneda() instanceof Cripto) {
			sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD='"+ activo.getCantidad()+"' WHERE ID=\""+activo.getId()+"\"";
		}
		else if(activo.getMoneda() instanceof Fiat) {
			sql = "UPDATE ACTIVO_FIAT SET CANTIDAD='"+ activo.getCantidad()+"' WHERE ID=\""+activo.getId()+"\"";
		} else {
			return false;
		}
		Connection con = null;
		try {
			con = MyConnection.getCon();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
	    	stmt.close();
	    	return true;
	    	} 
		catch (SQLException e){
	    	return false;
		}
	}
	public boolean crearActivo(int id_usuario, Moneda moneda) {
		String sql = null;
		if(moneda instanceof Cripto) {
			sql = "INSERT INTO ACTIVO_CRIPTO VALUES "
				+ "(NULL,'"
				+ id_usuario +"', '"
				+ moneda.getId() + "', '0')";
		}
		if(moneda instanceof Fiat) {
			sql = "INSERT INTO ACTIVO_FIAT VALUES "
				+ "(NULL, '"
				+ id_usuario +"', '"
				+ moneda.getId() + "', '0')";
		}
		Connection con = null;
		try {
			
			con = MyConnection.getCon();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
	    	stmt.close();
	    	return true;
	    	} 
		catch (SQLException e){
	    	return false;
		}
	}
	
	public Activo existeActivo(int id_usuario,Moneda moneda) {
		String table= null;
		if(moneda instanceof Cripto)
			table = "ACTIVO_CRIPTO";
		else if (moneda instanceof Fiat)
			table = "ACTIVO_FIAT";
		
		String sql = "SELECT * FROM " + table +" WHERE ID_MONEDA=\""+moneda.getId()+"\"";
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
          	double cantidad = resul.getDouble("CANTIDAD");
          	Activo activo = new Activo(moneda, cantidad);
          	return activo;
		}
		catch(SQLException e){
//			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
	    	return null;
		}
	}
}
