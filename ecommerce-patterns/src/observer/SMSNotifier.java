package observer;

public class SMSNotifier implements OrderObserver {
    @Override
    public void update(String orderId, String oldStatus, String newStatus) {
        System.out.println("[SMS] Enviando SMS para el pedido " + orderId +
                ": El estado ha cambiado de " + oldStatus + " a " + newStatus + ".");
    }
}
