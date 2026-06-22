package co.ufps.gymflow.persistencia.plan;

import co.ufps.gymflow.domain.plan.Plan;
import co.ufps.gymflow.domain.plan.PlanId;
import co.ufps.gymflow.domain.plan.RepositorioPlan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@ApplicationScoped
public class PlanRepositoryJpa implements RepositorioPlan {

    @PersistenceContext(unitName = "gymflowPU")
    private EntityManager entityManager;

    @Override
    public void guardar(Plan plan) {
        entityManager.merge(toEntity(plan));
    }

    @Override
    public Optional<Plan> buscarPorId(PlanId id) {
        PlanJpa jpa = entityManager.find(PlanJpa.class, id.getValor());
        return Optional.ofNullable(jpa).map(this::toDomain);
    }

    @Override
    public Optional<Plan> buscarPorNombre(String nombre) {
        return entityManager.createQuery(
                        "SELECT p FROM PlanJpa p WHERE p.nombre = :nombre", PlanJpa.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findFirst()
                .map(this::toDomain);
    }

    // dominio → JPA
    private PlanJpa toEntity(Plan plan) {
        PlanJpa jpa = new PlanJpa();
        jpa.setPlanId(plan.getPlanId().getValor());
        jpa.setNombre(plan.getNombre());
        jpa.setDuracionDias(plan.getDuracionDias());
        jpa.setPrecio(plan.getPrecio());
        return jpa;
    }

    // JPA → dominio
    private Plan toDomain(PlanJpa jpa) {
        return Plan.de(
                PlanId.de(jpa.getPlanId()),
                jpa.getNombre(),
                jpa.getDuracionDias(),
                jpa.getPrecio()
        );
    }
}