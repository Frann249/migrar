package appDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.MyConnection;
import appModels.Cripto;
import appModels.Fiat;
import appModels.Persona;
import appModels.Usuario;

public class PersonaDAOjdbc  implements PersonaDAO{

	@Override
	public boolean existePersona(Persona persona) {
		String sql = "SELECT * FROM PERSONA WHERE ID_PERSONA=\""+persona.getId()+"\"";
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
            
            String nombre = resul.getString("NOMBRES");
          	String apellido = resul.getString("APELLIDOS");
          	persona.setData(nombre, apellido);
          	return true;
		}
		catch(SQLException e){
//			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
	    	return false;
		}
		
	}

	@Override
	public boolean guardarPersona(Persona persona) {
		Connection con = null;
		String sql = null;
		sql ="INSERT INTO PERSONA VALUES "
			+ "( NULL ,'"
			+ persona.getNombre() +"', '"
			+ persona.getApellido() +"')";
		try {
			con = MyConnection.getCon();
			Statement stmt = con.createStatement();
	    	stmt.executeUpdate(sql);
	    	ResultSet clavesGeneradas = stmt.getGeneratedKeys();
            if(clavesGeneradas.next()) {	
            	int idGenerado = clavesGeneradas.getInt(1);
            	persona.setId(idGenerado);
            }
	    	stmt.close();
	    	return true;
	    	
	    	} 
		catch (SQLException e){
	    	//System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
	    	return false;
	    }
	}

}
