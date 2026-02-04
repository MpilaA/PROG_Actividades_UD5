import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class CuentaBancaria {

    // Cosas de los objetos
    private final String IBAN;
    private Cliente cliente;
    private double saldo;
    // Coleccion de movimientos
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    // Constructor del objeto.

    public CuentaBancaria(String IBAN, Cliente cliente) {
        this.IBAN = IBAN;
        this.cliente = cliente;
        this.saldo = 0;
    }

    // Metodos del objeto
    // Gets para la obtencion del IBAN el titular y el saldo
    public String getIBAN(){
        return this.IBAN;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return this.saldo;
    }
    // Metodo para el ingreso
    public boolean ingresar(double ingreso) throws CuentaException{
        boolean estado = false;
        // If para revisar si no ponen una cantidad con el proceso de ingreso.
        if (!(ingreso < 1)) {
            this.saldo += ingreso;

            this.movimientos.add(new Movimiento("Ingreso", ingreso));

            estado = true;
        } else {
            throw new CuentaException("Por favor introduzca otra cantidad que sea admitida.");
        }
        return estado;
    }
    // Metodo para el retiro
    public boolean retirar(double retiro) throws CuentaException {
        boolean estado = false;
        retiro = Math.abs(retiro); // Esto esta para restar sean negativos o positivos.
        // If para revisar si no ponen una cantidad o si el saldo vale menos de -50
        if ( !(retiro < 1) && !((saldo - retiro) < -50) ) {
            this.saldo -= retiro;

            this.movimientos.add(new Movimiento("Retiro", (retiro * -1)));


            estado = true;
        } else {
            throw new CuentaException("Por favor introduzca otra cantidad que sea admitida.");
        }
        return estado;
    }
    // Metodo para el movimiento
    public String verMovimientos() {
        StringBuilder texto = new StringBuilder();
        // IF para imprimir que no hay movimientos si no los hay y si si hay imprimirlos.
        if (movimientos.isEmpty()) {
            texto.append("No hay movimientos");
        } else {
            texto.append("Historial de movimientos: \n");
            for (Movimiento i : movimientos) { // Bucle para imprimir todos los movimientos
                if (i != null) {
                    texto.append(i.toString() + " \n");
                }
            }
        }
        return texto.toString();
    }
    // Metodo para los datos de la cuenta
    public String datosCuenta() {
        String out = String.format("Info cuenta: \n" +
                        "IBAN: %s \n" +
                        "Titular: %s \n" +
                        "Saldo: %s",
                this.getIBAN(), this.getCliente(), this.getSaldo());
        return out;
    }
    // Metodo para las excepciones // Sin hacer
    public void avisos() throws CuentaException {

        if (this.saldo < 0) {
            throw new CuentaException("AVISO: Saldo negativo ");
        }
    }

    public void avisos(double cantidad, String tipo) throws AvisarHaciendaException{

        // Verificacion de que la cantidad es mayor al maximo para lanzar la excepcion
        if (cantidad > 3000) {
            throw new AvisarHaciendaException("AVISO: Notificar a hacienda ",
                this.cliente, this.IBAN, cantidad, tipo);
        }
    }


}
