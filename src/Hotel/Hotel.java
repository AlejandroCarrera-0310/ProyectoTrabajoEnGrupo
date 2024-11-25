package Hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private List<Habitacion> habitaciones;
    private List<Reserva> reservas;

    public Hotel() {
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();
        inicializarHabitaciones();
    }

    private void inicializarHabitaciones() {
        // Crear 20 habitaciones individuales
        for (int i = 1; i <= 20; i++) {
            habitaciones.add(new Habitacion(i, "Individual"));
        }
        // Crear 50 habitaciones dobles
        for (int i = 21; i <= 70; i++) {
            habitaciones.add(new Habitacion(i, "Doble"));
        }
        // Crear 30 habitaciones suite
        for (int i = 71; i <= 100; i++) {
            habitaciones.add(new Habitacion(i, "Suite"));
        }
    }

    public boolean verificarDisponibilidad(int numeroHabitacion) {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        return habitacion != null && habitacion.isDisponible();
    }

    public boolean crearReserva(String cliente, int numeroHabitacion, Date fechaInicio, Date fechaFin) {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        if (habitacion != null && habitacion.isDisponible()) {
            Reserva nuevaReserva = new Reserva(cliente, habitacion, fechaInicio, fechaFin);
            reservas.add(nuevaReserva);
            habitacion.setDisponible(false);
            return true;
        }
        return false; // Habitación no disponible o inexistente
    }

    private Habitacion buscarHabitacion(int numeroHabitacion) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numeroHabitacion) {
                return habitacion;
            }
        }
        return null; // Habitación no encontrada
    }

    public List<Habitacion> getHabitacionesPorTipo(String tipo) {
        List<Habitacion> resultado = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(habitacion);
            }
        }
        return resultado;
    }
}
