import java.time.LocalDateTime;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CuentaBancaria cuenta; // Creando cuenta
        String IBANp = "[A-Z]{2}[0-9]{22}"; // Creando patron para el IBAN

        // Creando variables con la informacion para la cuenta
        String IBAN, nombre, dni, telefono, email, direccion;
        Date fechaNacimiento = null;

        do { // Bucle para pedir el IBAN hasta que sea correcto
            System.out.print("Ingrese el IBAN: ");
            IBAN = sc.nextLine().toUpperCase();
        } while(!Pattern.matches(IBANp, IBAN));

        do{ // Bucle para pedir el nombre del cliente
            System.out.print("Ingrese el nombre del cliente: ");
            sc = new Scanner(System.in);
            nombre = sc.nextLine();
        }while(nombre.isEmpty());

        do{ // Bucle para pedir el DNI del cliente
            System.out.print("Ingrese el DNI: ");
            sc = new Scanner(System.in);
            dni = sc.nextLine().toUpperCase();
        }while(dni.length() == 9);

        do{ // Bucle para pedir la fecha de nacimiento
            try {
                // No en contre otra forma fucional de hacerlo
                System.out.println("Ingrese la fecha de nacimiento: ");

                System.out.print("Dame el dia: ");
                sc = new Scanner(System.in);
                int dia = sc.nextInt();

                System.out.print("Dame el mes: ");
                sc = new Scanner(System.in);
                int mes = sc.nextInt();

                System.out.print("Dame el año: ");
                sc = new Scanner(System.in);
                int ano = sc.nextInt();

                fechaNacimiento = new Date(ano, (mes-1), dia);
            } catch (InputMismatchException e) {
                System.out.println("La fecha es con numeros.");
            }
        }while(fechaNacimiento == null);

        do{ // Bucle para pedir el numero de telfono
            System.out.print("Ingrese el telefono: ");
            sc = new Scanner(System.in);
            telefono = sc.nextLine();
        }while(telefono.length() == 9);

        do{ // Bucle para pedir el email
            System.out.print("Ingrese la dierccion de email: ");
            sc = new Scanner(System.in);
            email = sc.nextLine();
        }while(email.isBlank());

        do{ // Bucle para pedir la direccion
            System.out.print("Ingrese la direccion: ");
            sc = new Scanner(System.in);
            direccion = sc.nextLine();
        }while(direccion.isBlank());

        cuenta = new CuentaBancaria(IBAN, new Cliente(nombre, dni, fechaNacimiento, telefono, email, direccion));
        System.out.println("La cuenta se ha creado correctamente.");

        // Variables y el bucle para el menu.
        boolean salida = false, estado = true;
        do {

            double cantidad = 0;

            System.out.println("Por favor escoja entre una de estas opciones: \n" +
            "1. Mostrar datos de la cuenta. \n" +
            "2. Mostrar el IBAN. \n" +
            "3. Mostrar el titular. \n" +
            "4. Mostrar el saldo. \n" +
            "5. Ingresar dinero. \n" +
            "6. Retirar dinero. \n" +
            "7. Ver movimientos. \n" +
            "8. Salir");

            System.out.print("Opcion: ");
            sc = new Scanner(System.in);
            int opcion = 0;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, solo numeros.");
                continue;
            }
            switch (opcion){
                case 1://Datos de la cuenta.
                    System.out.println(cuenta.datosCuenta());
                    break;
                case 2:// IBAN
                    System.out.println("IBAN: " + cuenta.getIBAN());
                    break;
                case 3:// Titular
                    System.out.println("Cliente: " + cuenta.getCliente());
                    break;
                case 4:// Saldo
                    System.out.println("Saldo: " + cuenta.getSaldo());
                    break;
                case 5: // Ingresar
                    estado = false;
                    do {
                        try { 
                            // Pedir la cantidad
                            System.out.print("Indique la cantidad a ingresar: ");
                            cantidad = sc.nextDouble();
                            
                            // Ejecutar el ingreso
                            estado = cuenta.ingresar(cantidad);

                        } catch (CuentaException e) {
                            System.out.println(e.getMessage());

                        } catch (InputMismatchException e){
                            System.out.println("Por favor introduca una cantidad en numeros.");
                        }
                    } while (!estado);
                    // Try con los avisos
                    try {
                        cuenta.avisos();

                    } catch (CuentaException e){
                        System.out.println(e.getMessage());
                    }
                    try {
                        cuenta.avisos(cantidad, "Ingreso");
                    } catch (AvisarHaciendaException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6: // Retirar
                    estado = false;
                    do {
                        try {
                            // Pedir la cantidad
                            System.out.print("Indique la cantidad a retirar: ");
                            cantidad = sc.nextDouble();

                            // Ejecutar el retiro
                            estado = cuenta.retirar(cantidad);
                        } catch (CuentaException e) {
                            System.out.println(e.getMessage());

                        } catch (InputMismatchException e){
                            System.out.println("Por favor introduca una cantidad en decimales.");
                        }

                    } while (!estado);
                    // Try con los avisos
                    try {
                        cuenta.avisos();

                    } catch (CuentaException e){
                        System.out.println(e.getMessage());
                    }
                    try {
                        cuenta.avisos(cantidad, "Retiro");

                    } catch (AvisarHaciendaException e){
                        System.out.println(e.getMessage());
                    }
                    
                    break;
                case 7: // Ver movimientos de la cuenta
                    System.out.println(cuenta.verMovimientos());
                    break;
                case 8: // Salir
                    System.out.println("Saliendo");
                    salida = true;
                    break;
                default: // Mensaje para cuando ponen una opcion incorrecta
                    System.out.println("Por favor, ponga una de las opciones " +
                    "permitidas que se te van a volver a mencionar. Gracias.");
            }
        } while(!salida && estado);
    }

}