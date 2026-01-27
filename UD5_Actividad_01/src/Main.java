import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ejercicio1();
        ejercicio2();
        ejercicio3();
        ejercicio4();
        ejercicio5();
        ejercicio6();
    }

    public static void ejercicio1(){
        Scanner sc = new Scanner(System.in);

        try { 
            System.out.print("1> ");
            int a = sc.nextInt();

            System.out.println("Valor introducido: " + a);
        } catch (InputMismatchException e) {
            System.out.println("Valor introducido incorrecto");
        }
    }

    public static void ejercicio2(){
        Scanner sc = new Scanner(System.in);

        Integer r = null;

        try {
            System.out.print("2> ");
            int a = sc.nextInt();

            System.out.print("2> ");
            int b = sc.nextInt();

            r = a/b;
            
        } catch (InputMismatchException e) {
            System.out.println("Eso no es un numero");
        } catch (ArithmeticException e) {
            System.out.println("Error al dividir");
        }finally {
            System.out.println(r);
        }
    }

    public static void ejercicio3(){
        Scanner sc;

        double[] a = new double[5];

        for (int i = 0; i<a.length;i++){
            try{
                System.out.print("3> ");
                sc = new Scanner(System.in);
                a[i] = sc.nextDouble();
            } catch (InputMismatchException  e) {
                i--;

                System.out.println("Las letras no son admitidas");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Creo que ya esta lleno");
            }
        }

        // Añadi esto para poder ver el estado como es el array depues de 
        // rellenarlo
        for (int i = 0; i<a.length;i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void ejercicio4(){
        Scanner sc = new Scanner(System.in);

        boolean salir = true;
        System.out.println("Excepcion positivos");

        do {
            System.out.println("Mete algo que no sea un numero para salir");
            System.out.print("4>");
            // No meti los print de texto ya que no es necesario controlar
            // sus excepcioenes ya que no ocurren
            try {
                sc = new Scanner(System.in);
                int num = sc.nextInt();
                imprimePositivo(num);
            } catch (InputMismatchException e) {
                System.out.println("Saliendo");
                salir = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println();
            }
        } while (salir);

        salir = true;
        System.out.println("Excepcion negativos");
        do {
            System.out.println("Mete algo que no sea un numero para salir");
            System.out.print("4>");
            // No meti los print de texto ya que no es necesario controlar
            // sus excepcioenes ya que no ocurren.
            try {
                sc = new Scanner(System.in);
                int num = sc.nextInt();
                imprimeNegativo(num);
            } catch (InputMismatchException e) {
                System.out.println("Saliendo");
                salir = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (salir);
    }

    public static void ejercicio5(){

        System.out.println("Ejercicio 5");

        // Prueba de la excepcion en el constructor 
        // con un nombre no admitido
        try{
            Gato a = new Gato("a", 1);
            System.out.println(a);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // Prueba de la excepcion en el constructor 
        // con una edad no admitida
        try{
            Gato b = new Gato("Baba", -3);
            System.out.println(b);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // Creando un objeto de la clase Gato para pruebas
        Gato c = null;
        try {
            c = new Gato("ccc", 2);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // Prueba de la excepcion con el metodo setEdad
        try {
            c.setEdad(-1);
        } catch (EdadException e) {
            System.out.println(e.getMessage());
        }

        // Prueba de la excepcion con el metodo setNombre
        try {
            c.setNombre("n");
        } catch (NombreException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Fin del ejercicio 5");
    }

    public static void ejercicio6(){
        ArrayList<Gato> gatos = new ArrayList<>();
        do { // Bucle que revisa si hay no hay 5 objetos en el array
            // Para esto en el try esta la parte de añadir el objeto
            // a la coleccion ya que si algo si salta una excepcion 
            // esta no se ejecutara
            try {
                System.out.print("Pon nombre \n6>");
                Scanner sc = new Scanner(System.in);
                String nombre = sc.nextLine();

                System.out.print("Pon edad \n6>");
                sc = new Scanner(System.in);
                int edad = sc.nextInt();

                Gato gato = new Gato(nombre, edad);
                gatos.add(gato);
            } catch (InputMismatchException e) {
                System.out.println("En la edad no se aceptan letras");
            } catch (NombreException | EdadException e) {
                System.out.println(e.getMessage());
            }
        } while (5 > gatos.size());

        System.out.println("Gatos: ");
        for (Gato cat : gatos) {
            System.out.println(cat.getNombre() + " " + cat.getEdad());
        }

    }

    // Metodos necesarios para el ejercicio 4
    public static void imprimePositivo(int p) throws Exception{
        System.out.println(p);
        if (p < 0) {
            throw new Exception("Es negativo");
        }
    }

    public static void imprimeNegativo(int n) throws Exception{
        if (n > 0) {
            throw new Exception("Es positivo");
        }
    }
}