package co.ufps.gymflow.domain.membresia;

import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import java.util.UUID;

public final class MembresiaId {

    private final String valor;

    private MembresiaId(String valor) {
        if (valor == null || valor.isBlank())
            throw new ExcepcionDominio("El ID de membresía no puede estar vacío.");
        this.valor = valor.strip();
    }

    public static MembresiaId generar() {
        return new MembresiaId(UUID.randomUUID().toString());
    }

    public static MembresiaId de(String valor) {
        return new MembresiaId(valor);
    }

    public String getValor() { return valor; }
}