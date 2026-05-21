package co.ufps.gymflow.domain.miembro;

import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import java.util.Objects;

public final class Cedula {

    private final String valor;

    public Cedula(String valor) {
        if (valor == null || valor.isBlank())
            throw new ExcepcionDominio("La cédula no puede estar vacía.");
        if (!valor.strip().matches("\\d{6,12}"))
            throw new ExcepcionDominio("La cédula debe contener entre 6 y 12 dígitos.");
        this.valor = valor.strip();
    }

    public String getValor() { return valor; }

    @Override
    public boolean equals(Object o) {
        // Si el objeto o es una cedula, se guarda en c, si no, retorna false
        if (!(o instanceof Cedula c)) return false;
        // Despues de validar que ambas son cedulas, se retorna si son la misma o no
        return Objects.equals(valor, c.valor);
    }

    @Override
    // Objetos con misma cedula tienen el mismo hash
    public int hashCode() { return Objects.hash(valor); }
}