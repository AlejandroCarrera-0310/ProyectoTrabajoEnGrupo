package Hotel;

public class Habitacion {
    private int numero;
    private String tipo; // Individual, Doble, Suite
    private boolean disponible;

    public Habitacion(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = true; // Por defecto, disponible
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
