package appDAO;

import appModels.Usuario;

public interface UsuarioDAO {
	public Usuario existeUsuario(int id);
	public Usuario existeUsuario(String email);
	public boolean guardarUsuario(Usuario usuario);
}
