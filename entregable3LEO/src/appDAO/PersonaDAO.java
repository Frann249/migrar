package appDAO;

import appModels.Persona;

public interface PersonaDAO {
	public boolean existePersona(Persona persona);
	public boolean guardarPersona(Persona persona);
}
