package co.ufps.gymflow.domain.miembro;

import java.util.Optional;

public interface RepositorioMiembro {
    void guardar(Miembro miembro);
    Optional<Miembro> buscarPorId(MiembroId id);
    boolean existePorCedula(Cedula cedula);
}