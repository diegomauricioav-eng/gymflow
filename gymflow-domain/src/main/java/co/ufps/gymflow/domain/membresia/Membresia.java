package co.ufps.gymflow.domain.membresia;

import co.ufps.gymflow.domain.miembro.MiembroId;
import co.ufps.gymflow.domain.plan.PlanId;
import java.time.LocalDate;
import java.util.Objects;

public class Membresia {

    private final MembresiaId membresiaId;
    private final MiembroId miembroId;
    private final PlanId planId;
    private final LocalDate fechaInicio;
    private final LocalDate fechaVencimiento;

    private Membresia(MembresiaId membresiaId, MiembroId miembroId, PlanId planId,
                      LocalDate fechaInicio, LocalDate fechaVencimiento) {
        this.membresiaId = Objects.requireNonNull(membresiaId);
        this.miembroId = Objects.requireNonNull(miembroId);
        this.planId = Objects.requireNonNull(planId);
        this.fechaInicio = Objects.requireNonNull(fechaInicio);
        this.fechaVencimiento = Objects.requireNonNull(fechaVencimiento);
    }

    public static Membresia crear(MiembroId miembroId, PlanId planId, int duracionDias) {
        LocalDate inicio = LocalDate.now();
        return new Membresia(
                MembresiaId.generar(),
                miembroId,
                planId,
                inicio,
                inicio.plusDays(duracionDias)
                /*plusdays es un metodo que agarra la fecha (variable inicio)
                 y calcula una nueva fecha (fecha de fin de membresia)
                  sumandole los dias de la duracion del plan, a la fecha actual
                  fecha inicio + duracion plan = fecha fin de membresia*/
        );
    }

    // para reconstruir desde la bd
    public static Membresia de(MembresiaId membresiaId, MiembroId miembroId, PlanId planId,
                               LocalDate fechaInicio, LocalDate fechaVencimiento) {
        return new Membresia(membresiaId, miembroId, planId, fechaInicio, fechaVencimiento);
    }

    public MembresiaId getMembresiaId() { return membresiaId; }
    public MiembroId getMiembroId() { return miembroId; }
    public PlanId getPlanId() { return planId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
}