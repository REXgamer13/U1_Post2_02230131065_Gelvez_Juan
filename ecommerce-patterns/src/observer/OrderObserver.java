package observer;

public interface OrderObserver {

    void update(String orderId, String oldStatus, String newStatus);

}
