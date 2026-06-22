package co.ufps.gymflow.persistencia.membresia;

import co.ufps.gymflow.domain.membresia.Membresia;
import co.ufps.gymflow.domain.membresia.MembresiaId;
import co.ufps.gymflow.domain.membresia.RepositorioMembresia;
import co.ufps.gymflow.domain.miembro.MiembroId;
import co.ufps.gymflow.domain.plan.PlanId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class MembresiaRepositoryJpa implements RepositorioMembresia {

    @PersistenceContext(unitName = "gymflowPU")
    private EntityManager entityManager;

    @Override
    public void guardar(Membresia membresia) {
        entityManager.merge(toEntity(membresia));
    }

    // dominio → JPA
    private MembresiaJpa toEntity(Membresia membresia) {
        MembresiaJpa jpa = new MembresiaJpa();
        jpa.setMembresiaId(membresia.getMembresiaId().getValor());
        jpa.setMiembroId(membresia.getMiembroId().getValor());
        jpa.setPlanId(membresia.getPlanId().getValor());
        jpa.setFechaInicio(membresia.getFechaInicio());
        jpa.setFechaVencimiento(membresia.getFechaVencimiento());
        return jpa;
    }

    // JPA → dominio
    private Membresia toDomain(MembresiaJpa jpa) {
        return Membresia.de(
                MembresiaId.de(jpa.getMembresiaId()),
                MiembroId.de(jpa.getMiembroId()),
                PlanId.de(jpa.getPlanId()),
                jpa.getFechaInicio(),
                jpa.getFechaVencimiento()
        );
    }
}