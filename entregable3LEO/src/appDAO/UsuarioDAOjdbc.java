package appDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.MyConnection;
import appModels.Activo;
import appModels.Persona;
import appModels.Usuario;

public class UsuarioDAOjdbc implements UsuarioDAO{

	@Override
	public Usuario existeUsuario(int id) {
		String sql = "SELECT * FROM USUARIO WHERE ID=\""+id+"\"";
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
            
            int id_persona = resul.getInt("ID_PERSONA");
            String email = resul.getString("EMAIL");
          	String password = resul.getString("PASSWORD");
          	return new Usuario(id, new Persona(id_persona), email, password);
		}
		catch(SQLException e){
//			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
	    	return null;
		}
	}

	@Override
	public Usuario existeUsuario(String email) {
		String sql = "SELECT * FROM USUARIO WHERE EMAIL=\""+email+"\"";
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
            
            int id = resul.getInt("ID");
            int id_persona = resul.getInt("ID_PERSONA");
          	String password = resul.getString("PASSWORD");
          	return new Usuario(id, new Persona(id_persona), email, password);
		}
		catch(SQLException e){
//			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
	    	return null;
		}
	}

	@Override
	public boolean guardarUsuario(Usuario usuario) {
		Connection con = null;
		String sql = null;
		sql ="INSERT INTO USUARIO VALUES "
			+ "( NULL ,'"
			+ usuario.getPersona().getId() +"', '"
			+ usuario.getEmail() +"', '"
			+ usuario.getPassword()+"', 'TRUE')";
		try {
			con = MyConnection.getCon();
			Statement stmt = con.createStatement();
	    	stmt.executeUpdate(sql);
	    	ResultSet clavesGeneradas = stmt.getGeneratedKeys();
            if(clavesGeneradas.next()) {	
            	int idGenerado = clavesGeneradas.getInt(1);
            	usuario.setId(idGenerado);
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