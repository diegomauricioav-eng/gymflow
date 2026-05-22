package co.ufps.gymflow.domain.plan;

import java.util.Optional;

public interface RepositorioPlan {

    // Guarda un nuevo plan de membresía en el sistema (Usado en GIM-002)
    void guardar(Plan plan);

    // Busca un plan por su ID para verificar existencia (Usado en GIM-005)
    Optional<Plan> buscarPorId(PlanId id);

    // Busca por nombre para evitar duplicados según el criterio de aceptación (Usado en GIM-002)
    Optional<Plan> buscarPorNombre(String nombre);
}