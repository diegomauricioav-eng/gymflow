package co.ufps.gymflow.api.controladores;

import co.ufps.gymflow.application.dto.RegistroMiembroDto;
import co.ufps.gymflow.application.usecases.RegistrarMiembroUseCase;
import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/miembros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MiembroControlador {

    @Inject
    private RegistrarMiembroUseCase registrarMiembroUseCase;

    @POST
    public Response registrarMiembro(RegistroMiembroDto dto) {
        try {
            registrarMiembroUseCase.ejecutar(dto);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\": \"Miembro registrado exitosamente en estado PENDIENTE.\"}")
                    .build();
        } catch (ExcepcionDominio e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno en el servidor: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}