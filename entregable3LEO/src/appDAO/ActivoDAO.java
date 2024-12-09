package appDAO;

import java.util.List;

import appModels.Activo;
import appModels.Moneda;

public interface ActivoDAO {
	public List<Activo> listarActivos(int id_usuario);
	public List<Activo> listarActivosCripto(int id_usuario);
	public List<Activo> listarActivosFiat(int id_usuario);
	public boolean actualizarActivo(Activo activo);
	public Activo existeActivo(int id_usuario,Moneda moneda);
	public boolean crearActivo(int id_usuario, Moneda moneda);
}
