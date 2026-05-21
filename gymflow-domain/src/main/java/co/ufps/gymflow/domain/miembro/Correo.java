package co.ufps.gymflow.domain.miembro;

import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import java.util.Objects;

public final class Correo {

    private final String valor;

    public Correo(String valor) {
        if (valor == null || valor.isBlank())
            throw new ExcepcionDominio("El correo no puede estar vacío.");
        if (!valor.strip().matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$"))
            throw new ExcepcionDominio("El correo no tiene un formato válido.");
        this.valor = valor.strip();
    }

    public String getValor() { return valor; }

    @Override
    public boolean equals(Object o) {
        // Si el objeto o es un correo, se guarda en c, si no, retorna false
        if (!(o instanceof Correo c)) return false;
        // Despues de validar que ambos son correos, se retorna si son el mismo o no
        return Objects.equals(valor, c.valor);
    }

    @Override
    public int hashCode() { return Objects.hash(valor); }
}