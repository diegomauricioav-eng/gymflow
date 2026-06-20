package co.ufps.gymflow.domain.membresia;

public interface RepositorioMembresia {

    // Unión entre el miembro y su plan
    void guardar(Membresia membresia);
}