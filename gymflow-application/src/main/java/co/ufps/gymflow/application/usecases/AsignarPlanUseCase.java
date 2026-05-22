package co.ufps.gymflow.application.usecases;

import co.ufps.gymflow.application.dto.AsignarPlanDto;
import co.ufps.gymflow.domain.miembro.*;
import co.ufps.gymflow.domain.plan.*;
import co.ufps.gymflow.domain.membresia.*;
import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AsignarPlanUseCase {

    private final RepositorioMiembro repositorioMiembro;
    private final RepositorioPlan repositorioPlan;
    private final RepositorioMembresia repositorioMembresia;

    protected AsignarPlanUseCase() {
        this.repositorioMiembro = null;
        this.repositorioPlan = null;
        this.repositorioMembresia = null;
    }

    @Inject
    public AsignarPlanUseCase(RepositorioMiembro repositorioMiembro,
                              RepositorioPlan repositorioPlan,
                              RepositorioMembresia repositorioMembresia) {
        this.repositorioMiembro = repositorioMiembro;
        this.repositorioPlan = repositorioPlan;
        this.repositorioMembresia = repositorioMembresia;
    }

    public void ejecutar(AsignarPlanDto dto) {
        MiembroId miembroId = MiembroId.de(dto.miembroId());
        Miembro miembro = repositorioMiembro.buscarPorId(miembroId)
                .orElseThrow(() -> new ExcepcionDominio("El miembro especificado no existe."));

        PlanId planId = PlanId.de(dto.planId());
        Plan plan = repositorioPlan.buscarPorId(planId)
                .orElseThrow(() -> new ExcepcionDominio("El plan especificado no existe."));

        Membresia nuevaMembresia = Membresia.crear(miembroId, planId, plan.getDuracionDias());
        repositorioMembresia.guardar(nuevaMembresia);

        miembro.asignarPlan(planId);

        repositorioMiembro.guardar(miembro);
    }
}