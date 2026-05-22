package co.ufps.gymflow.domain.miembro;

import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;

import java.util.UUID;

public final class MiembroId {

    private final String valor;

    private MiembroId(String valor){
        if (valor == null || valor.isBlank()){
            throw new ExcepcionDominio("El ID no puede estar vacío.");
        }
        this.valor = valor.strip(); // strip borra espacios en blanco
    }

    public static MiembroId generar(){
        // Generador de randomUUID utilizando la clase UUID incluida en java
        return new MiembroId(UUID.randomUUID().toString());
    }

    // Para reconstruir desde la BD
    public static MiembroId de(String valor) {
        return new MiembroId(valor);
    }

    public String getValor(){
        return valor;
    }
}