package appDAO;

public class FactoryDAO {
	static {
	}
	private FactoryDAO() {
	}
	
	/**
	 * @return the monedaDAO
	 */
	public static MonedaDAOjdbc getMonedaDAO() {
		return new MonedaDAOjdbc();
	}

	/**
	 * @param monedaDAO the monedaDAO to set
	 */

	/**
	 * @return the activoDAO
	 */
	public static ActivoDAOjdbc getActivoDAO() {
		return new ActivoDAOjdbc();
	}

	/**
	 * @param activoDAO the activoDAO to set
	 */

	public static TransaccionDAOjdbc getTransaccionDAO() {
		return new TransaccionDAOjdbc();
	}
	public static PersonaDAOjdbc getPersonaDAO() {
		return new PersonaDAOjdbc();
	}
	public static UsuarioDAOjdbc getUsuarioDAO() {
		return new UsuarioDAOjdbc();
	}
}