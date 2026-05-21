package co.ufps.gymflow.domain.plan;

import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import java.math.BigDecimal;
import java.util.Objects;

public class Plan {

    private final PlanId planId;
    private String nombre;
    private int duracionDias;
    private BigDecimal precio;

    private Plan(PlanId planId, String nombre, int duracionDias, BigDecimal precio) {
        this.planId = Objects.requireNonNull(planId, "El ID del plan no puede ser nulo.");
        validarNombre(nombre);
        validarDuracion(duracionDias);
        validarPrecio(precio);
        this.nombre = nombre.strip();
        this.duracionDias = duracionDias;
        this.precio = precio;
    }

    // para crear planes
    public static Plan crear(String nombre, int duracionDias, BigDecimal precio) {
        return new Plan(PlanId.generar(), nombre, duracionDias, precio);
    }

    // reconstruir desde la BD
    public static Plan de(PlanId planId, String nombre, int duracionDias, BigDecimal precio) {
        return new Plan(planId, nombre, duracionDias, precio);
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new ExcepcionDominio("El nombre del plan no puede estar vacío.");
    }

    private static void validarDuracion(int duracion) {
        if (duracion <= 0)
            throw new ExcepcionDominio("La duración debe ser mayor a cero días.");
    }

    private static void validarPrecio(BigDecimal precio) {
        if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0)
            throw new ExcepcionDominio("El precio debe ser mayor a cero.");
    }

    public PlanId getPlanId() { return planId; }
    public String getNombre() { return nombre; }
    public int getDuracionDias() { return duracionDias; }
    public BigDecimal getPrecio() { return precio; }
}