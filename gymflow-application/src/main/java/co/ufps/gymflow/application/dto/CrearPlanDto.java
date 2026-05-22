package co.ufps.gymflow.application.dto;

import java.math.BigDecimal;

public record CrearPlanDto(
        String nombre,
        int duracionDias,
        BigDecimal precio) {}