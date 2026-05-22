package co.ufps.gymflow.domain.miembro;

import java.util.Optional;

public interface RepositorioMiembro {

    // Guarda o actualiza un miembro en el sistema
    void guardar(Miembro miembro);

    // Busca un miembro por su ID único (Usado en GIM-005)
    Optional<Miembro> buscarPorId(MiembroId id);

    // Busca si ya existe la cédula antes de registrar (Usado en GIM-001)
    Optional<Miembro> buscarPorCedula(Cedula cedula);
}