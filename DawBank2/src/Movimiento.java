import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Movimiento {

    // Cosas de la clase de movimiento
    private static int generadorID = 0;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // Cosas del objeto de movimiento
    private final int ID;
    private LocalDateTime fecha;
    private String tipo; // Tambien puede ser booleano pero es lioso
    private double cantidad;

    // Constructor del objeto.
    public Movimiento(String tipo, double cantidad) {
        this.ID = generadorID;
        generadorID++;

        this.fecha = LocalDateTime.now();
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Movimiento{" +
                ", ID=" + ID +
                ", fecha='" + fecha + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
