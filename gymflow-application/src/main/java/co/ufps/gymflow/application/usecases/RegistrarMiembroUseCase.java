package co.ufps.gymflow.application.usecases;

import co.ufps.gymflow.application.dto.RegistroMiembroDto;
import co.ufps.gymflow.domain.miembro.Miembro;
import co.ufps.gymflow.domain.miembro.Cedula;
import co.ufps.gymflow.domain.miembro.Correo;
import co.ufps.gymflow.domain.miembro.RepositorioMiembro;
import co.ufps.gymflow.domain.excepcion.ExcepcionDominio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped // Bean gestionado por GlassFish 7
public class RegistrarMiembroUseCase {

    private final RepositorioMiembro repositorioMiembro;

    // Requerido por CDI para GlassFish
    protected RegistrarMiembroUseCase() {
        this.repositorioMiembro = null;
    }

    @Inject
    public RegistrarMiembroUseCase(RepositorioMiembro repositorioMiembro) {
        this.repositorioMiembro = repositorioMiembro;
    }

    public void ejecutar(RegistroMiembroDto dto) {

        Cedula cedulaVo = new Cedula(dto.cedula());
        Correo correoVo = new Correo(dto.correo());

        if (repositorioMiembro.buscarPorCedula(cedulaVo).isPresent()) {
            throw new ExcepcionDominio("Ya existe un miembro registrado con esta cédula.");
        }
        Miembro nuevoMiembro = Miembro.crear(
                dto.nombre(),
                dto.telefono(),
                cedulaVo,
                correoVo,
                dto.urlFoto()
        );

        repositorioMiembro.guardar(nuevoMiembro);
    }
}