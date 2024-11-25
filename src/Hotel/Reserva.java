package Hotel;

import java.util.Date;

public class Reserva {
    private String cliente;
    private Habitacion habitacion;
    private Date fechaInicio;
    private Date fechaFin;

    public Reserva(String cliente, Habitacion habitacion, Date fechaInicio, Date fechaFin) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }
}
