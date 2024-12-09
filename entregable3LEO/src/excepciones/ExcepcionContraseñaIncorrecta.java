package excepciones;

public class ExcepcionContraseñaIncorrecta extends Exception {
    public ExcepcionContraseñaIncorrecta(String mensaje) {
        super(mensaje);
    }
}