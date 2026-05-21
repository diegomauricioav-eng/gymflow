package co.ufps.gymflow.domain.miembro;

import java.util.Objects;

public class Miembro{

    private MiembroId miembroID;
    private String nombre;
    private String telefono;
    private Cedula cedula;
    private Correo correo;
    private EstadoMiembro estado;
    private String rutaFoto;

    private Miembro(
            MiembroId miembroID,
            String nombre,
            String telefono,
            Cedula cedula,
            Correo correo,
            String rutaFoto){
        this.miembroID = Objects.requireNonNull(miembroID, "El ID del miembro no puede ser null.");
        this.nombre = nombre;
        this.telefono = telefono;
        this.cedula = cedula;
        this.correo = correo;
        this.estado = EstadoMiembro.PENDIENTE;
        this.rutaFoto = rutaFoto;
    }

    //Para crear la instancia del objeto miembro
    public static Miembro crear(
            String nombre,
            String telefono,
            Cedula cedula,
            Correo correo,
            String rutaFoto
    ){
        return new Miembro(MiembroId.generar(), nombre, telefono, cedula, correo, rutaFoto);
    }

    //Para reconstruir el objeto desde la persistencia (base de datos)
    public static Miembro of(
            MiembroId miembroID,
            String nombre,
            String telefono,
            Cedula cedula,
            Correo correo,
            EstadoMiembro estado,
            String rutaFoto
    ){
        Miembro m = new Miembro(
                miembroID,
                nombre,
                telefono,
                cedula,
                correo,
                rutaFoto
        );
        m.estado = estado;
        return m;
    }

    public void activar() {
        this.estado = EstadoMiembro.ACTIVO;
    }

    public MiembroId getMiembroID() {
        return miembroID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public Cedula getCedula() {
        return cedula;
    }

    public Correo getCorreo() {
        return correo;
    }

    public EstadoMiembro getEstado() {
        return estado;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }
}