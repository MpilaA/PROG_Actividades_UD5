public class AvisarHaciendaException extends Exception {
    public AvisarHaciendaException(String message, Cliente cliente, String IBAN, double cantidad, String tipo) {
        super(message + " \n" + cliente.getNombre() + "\n" + IBAN + "\n" + cantidad + " " + tipo);
    }
}
