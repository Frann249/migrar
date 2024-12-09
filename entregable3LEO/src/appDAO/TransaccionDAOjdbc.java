package appDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import app.MyConnection;
import appModels.Transaccion;

public class TransaccionDAOjdbc implements TransaccionDAO {
	public boolean guardarTransaccion(Transaccion transaccion) {
		String sql = "INSERT INTO TRANSACCION VALUES ('" + transaccion.toString() + "', '" + transaccion.getFecha() + "')";
		Connection con = null;
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
}
