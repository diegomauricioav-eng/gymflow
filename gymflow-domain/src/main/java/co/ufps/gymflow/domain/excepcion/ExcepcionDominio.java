package co.ufps.gymflow.domain.excepcion;

public class ExcepcionDominio extends RuntimeException {
    public ExcepcionDominio(String mensaje) {
        super(mensaje);
    }
}