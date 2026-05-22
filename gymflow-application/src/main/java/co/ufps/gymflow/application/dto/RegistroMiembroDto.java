package co.ufps.gymflow.application.dto;

public record RegistroMiembroDto(
        String cedula,
        String nombre,
        String correo,
        String telefono,
        String urlFoto) {
}
