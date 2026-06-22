package co.ufps.gymflow.persistencia.membresia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "membresia")
public class MembresiaJpa {

    @Id
    @Column(name = "membresia_id", length = 36, nullable = false)
    private String membresiaId;

    @Column(name = "miembro_id", length = 36, nullable = false)
    private String miembroId;

    @Column(name = "plan_id", length = 36, nullable = false)
    private String planId;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    public MembresiaJpa() {}

    public String getMembresiaId() { return membresiaId; }
    public void setMembresiaId(String membresiaId) { this.membresiaId = membresiaId; }

    public String getMiembroId() { return miembroId; }
    public void setMiembroId(String miembroId) { this.miembroId = miembroId; }

    public String getPlanId() { return planId; }
    public void setPlanId(String planId) { this.planId = planId; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
}