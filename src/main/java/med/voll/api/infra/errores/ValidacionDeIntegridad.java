package med.voll.api.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException { //la clase throwable responde ante errores y excepciones, y RunTimeException solo responde ante excepciones.
    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
