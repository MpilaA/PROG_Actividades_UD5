public class Gato {
    // Atributos de la clase
    String nombre;
    int edad;

    // Constructor de los objetos de la clase. Con funciones que 
    // lanzan excepciones cuando los datos son incorrectos.
    public Gato(String nombre, int edad) throws NombreException, EdadException {
        comprobarNombre(nombre);
        comprobarEdad(edad);

        this.nombre = nombre;
        this.edad = edad;
    }

    // Gettres
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    // Setters con funciones que lanzan excepciones cuando hay algo mal.
    public void setNombre(String nombre) throws NombreException{
        comprobarNombre(nombre);
        this.nombre = nombre;
    }

    public void setEdad(int edad) throws EdadException{
        comprobarEdad(edad);
        this.edad = edad;
    }

    // Funcion para comprobar si el nombre es admitido.
    public static void comprobarNombre(String nombre) throws NombreException{
        if (nombre.length() < 3) {
            throw new NombreException();
        }
    }

    // Funcion para comprobar si la edad es admitida.
    public static void comprobarEdad(int edad) throws EdadException{
        if (edad < 0) {
            throw new EdadException();
        }
    }
}
