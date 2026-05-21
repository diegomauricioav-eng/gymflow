package co.ufps.gymflow.domain.plan;

import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import java.util.UUID;

public final class PlanId {

    private final String valor;

    private PlanId(String valor) {
        if (valor == null || valor.isBlank())
            throw new ExcepcionDominio("El ID del plan no puede estar vacío.");
        this.valor = valor.strip();
    }

    public static PlanId generar() {
        return new PlanId(UUID.randomUUID().toString());
    }

    //para reconstruir desde la base de datos
    public static PlanId de(String valor) {
        return new PlanId(valor);
    }

    public String getValor() { return valor; }
}