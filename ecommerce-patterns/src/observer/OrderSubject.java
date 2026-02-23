package observer;

public interface OrderSubject {
    void subscribe(OrderObserver observer);
    void unsubscribe(OrderObserver observer);
    void notifyObservers(String orderId, String oldStatus, String newStatus);
}
