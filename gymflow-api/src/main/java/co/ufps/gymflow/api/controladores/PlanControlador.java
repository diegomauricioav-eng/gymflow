package co.ufps.gymflow.api.controladores;

import co.ufps.gymflow.application.dto.CrearPlanDto;
import co.ufps.gymflow.application.usecases.CrearPlanUseCase;
import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/planes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanControlador {

    @Inject
    private CrearPlanUseCase crearPlanUseCase;

    @POST
    public Response crearPlan(CrearPlanDto dto) {
        try {
            crearPlanUseCase.ejecutar(dto);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\": \"Plan de membresía creado exitosamente.\"}")
                    .build();
        } catch (ExcepcionDominio e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al crear el plan: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}