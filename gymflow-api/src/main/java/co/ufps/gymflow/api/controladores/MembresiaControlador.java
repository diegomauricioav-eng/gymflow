package co.ufps.gymflow.api.controladores;

import co.ufps.gymflow.application.dto.AsignarPlanDto;
import co.ufps.gymflow.application.usecases.AsignarPlanUseCase;
import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/membresias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MembresiaControlador {

    @Inject
    private AsignarPlanUseCase asignarPlanUseCase;

    @POST
    @Path("/asignar")
    public Response asignarPlan(AsignarPlanDto dto) {
        try {
            asignarPlanUseCase.ejecutar(dto);
            return Response.ok("{\"mensaje\": \"Plan asignado correctamente. El miembro ahora está ACTIVO.\"}")
                    .build();
        } catch (ExcepcionDominio e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error en la asignación: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}