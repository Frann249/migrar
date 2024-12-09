package excepciones;

public class ExcepcionEmailInvalido extends Exception {
    public ExcepcionEmailInvalido(String mensaje) {
        super(mensaje);
    }
}