package co.ufps.gymflow.persistencia.miembro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "miembro")
public class MiembroJpa {

    @Id
    @Column(name = "miembro_id", length = 36, nullable = false)
    private String miembroId;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "telefono", length = 20, nullable = false)
    private String telefono;

    @Column(name = "cedula", length = 12, nullable = false, unique = true)
    private String cedula;

    @Column(name = "correo", length = 255, nullable = false, unique = true)
    private String correo;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "ruta_foto", length = 500)
    private String rutaFoto;

    // FK al plan — solo guardamos el ID, sin relación JPA entre entidades
    @Column(name = "plan_id", length = 36)
    private String planId;

    public MiembroJpa() {}

    public String getMiembroId() { return miembroId; }
    public void setMiembroId(String miembroId) { this.miembroId = miembroId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getRutaFoto() { return rutaFoto; }
    public void setRutaFoto(String rutaFoto) { this.rutaFoto = rutaFoto; }

    public String getPlanId() { return planId; }
    public void setPlanId(String planId) { this.planId = planId; }
}