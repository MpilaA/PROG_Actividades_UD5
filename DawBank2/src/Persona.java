import java.util.Date;

public class Persona {
    // Atributos del objeto
    protected String nombre;
    protected String dni;
    protected Date fechaNacimiento;

    // Constructor del objeto
    public Persona(String nombre, String dni, Date fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Metodos para obtener los atributos del objeto.
    public String getNombre() {
        return this.nombre;
    }

    public String getDni() {
        return this.dni;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
}
