package co.ufps.gymflow.domain.membresia;

public interface RepositorioMembresia {

    // Registra la unión histórica entre el miembro y su plan (Usado en GIM-005)
    void guardar(Membresia membresia);
}