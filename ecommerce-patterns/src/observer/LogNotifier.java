package observer;

public class LogNotifier implements OrderObserver {
    @Override
    public void update(String orderId, String oldStatus, String newStatus) {
        System.out.println("[LOG] [" + System.currentTimeMillis() + "] Pedido: " + orderId +
                " | Transición: " + oldStatus + " -> " + newStatus);
    }
}
