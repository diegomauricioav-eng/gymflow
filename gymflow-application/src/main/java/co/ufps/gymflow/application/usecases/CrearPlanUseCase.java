package co.ufps.gymflow.application.usecases;

import co.ufps.gymflow.application.dto.CrearPlanDto;
import co.ufps.gymflow.domain.plan.*;
import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class CrearPlanUseCase {

    private final RepositorioPlan repositorioPlan;

    protected CrearPlanUseCase() {
        this.repositorioPlan = null;
    }

    @Inject
    public CrearPlanUseCase(RepositorioPlan repositorioPlan) {
        this.repositorioPlan = repositorioPlan;
    }

    public void ejecutar(CrearPlanDto dto) {
        // Validar no nombres duplicados
        if (repositorioPlan.buscarPorNombre(dto.nombre()).isPresent()) {
            throw new ExcepcionDominio("Ya existe un plan de membresía con el nombre: " + dto.nombre());
        }

        PlanId nuevoId = PlanId.generar();
        Plan nuevoPlan = Plan.crear(dto.nombre(), dto.duracionDias(), dto.precio());

        repositorioPlan.guardar(nuevoPlan);
    }
}