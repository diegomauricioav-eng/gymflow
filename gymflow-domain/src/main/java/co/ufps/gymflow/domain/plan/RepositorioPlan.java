package co.ufps.gymflow.domain.plan;

import java.util.Optional;

public interface RepositorioPlan {

    // Guarda un nuevo plan de membresía
    void guardar(Plan plan);

    // Busca un plan por su ID para
    Optional<Plan> buscarPorId(PlanId id);

    // Busca por nombre para evitar duplicados
    Optional<Plan> buscarPorNombre(String nombre);
}