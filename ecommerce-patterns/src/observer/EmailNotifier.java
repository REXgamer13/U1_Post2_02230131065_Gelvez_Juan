package observer;

public class EmailNotifier implements OrderObserver{
    @Override
    public void update(String orderId, String oldStatus, String newStatus) {
        System.out.println("[EMAIL] Enviando correo para el pedido " + orderId +
                ": El estado ha cambiado de " + oldStatus + " a " + newStatus + ".");
    }
}
