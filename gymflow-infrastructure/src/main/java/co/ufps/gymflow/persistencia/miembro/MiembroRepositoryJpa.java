package co.ufps.gymflow.persistencia.miembro;

import co.ufps.gymflow.domain.miembro.*;
import co.ufps.gymflow.domain.plan.PlanId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@ApplicationScoped
public class MiembroRepositoryJpa implements RepositorioMiembro {

    @PersistenceContext(unitName = "gymflowPU")
    private EntityManager entityManager;

    @Override
    public void guardar(Miembro miembro) {
        entityManager.merge(toEntity(miembro));
    }

    @Override
    public Optional<Miembro> buscarPorId(MiembroId id) {
        MiembroJpa jpa = entityManager.find(MiembroJpa.class, id.getValor());
        return Optional.ofNullable(jpa).map(this::toDomain);
    }

    @Override
    public Optional<Miembro> buscarPorCedula(Cedula cedula) {
        return entityManager.createQuery(
                        "SELECT m FROM MiembroJpa m WHERE m.cedula = :cedula", MiembroJpa.class)
                .setParameter("cedula", cedula.getValor())
                .getResultStream()
                .findFirst()
                .map(this::toDomain);
    }

    // dominio → JPA
    private MiembroJpa toEntity(Miembro miembro) {
        MiembroJpa jpa = new MiembroJpa();
        jpa.setMiembroId(miembro.getMiembroID().getValor());
        jpa.setNombre(miembro.getNombre());
        jpa.setTelefono(miembro.getTelefono());
        jpa.setCedula(miembro.getCedula().getValor());
        jpa.setCorreo(miembro.getCorreo().getValor());
        jpa.setEstado(miembro.getEstado().toString());
        jpa.setRutaFoto(miembro.getRutaFoto());
        jpa.setPlanId(miembro.getPlanID() != null ? miembro.getPlanID().getValor() : null);
        return jpa;
    }

    // JPA → dominio
    private Miembro toDomain(MiembroJpa jpa) {
        return Miembro.of(
                MiembroId.de(jpa.getMiembroId()),
                jpa.getNombre(),
                jpa.getTelefono(),
                new Cedula(jpa.getCedula()),
                new Correo(jpa.getCorreo()),
                EstadoMiembro.valueOf(jpa.getEstado()),
                jpa.getRutaFoto(),
                jpa.getPlanId() != null ? PlanId.de(jpa.getPlanId()) : null
        );
    }
}