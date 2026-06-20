package co.ufps.gymflow.domain.miembro;

import java.util.Optional;

public interface RepositorioMiembro {

    void guardar(Miembro miembro);

    Optional<Miembro> buscarPorId(MiembroId id);

    // Busca si ya existe la cédula antes de registrar
    Optional<Miembro> buscarPorCedula(Cedula cedula);
}