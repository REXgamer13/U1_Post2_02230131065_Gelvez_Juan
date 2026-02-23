package service;

import factory.ProductFactory;
import model.Product;
import observer.OrderObserver;
import observer.OrderSubject;
import strategy.PricingStrategy;
import strategy.RegularPricing;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements OrderSubject {

    // Lista de productos del pedido
    private final List<Product> products = new ArrayList<>();

    // Estrategia de precios (Strategy Pattern)
    private PricingStrategy pricingStrategy;

    // Observadores (Observer Pattern)
    private final List<OrderObserver> observers = new ArrayList<>();

    // Estado del pedido
    private String orderId;
    private String status;

    public OrderService(String orderId) {
        this.orderId = orderId;
        this.status = "CREADO";
        this.pricingStrategy = new RegularPricing(); // Estrategia por defecto
    }

    // ===================== FACTORY PATTERN =====================

    /**
     * Usa ProductFactory para crear un producto y agregarlo al pedido.
     */
    public Product addProduct(String type, String name, double price) {
        Product product = ProductFactory.createProduct(type, name, price);
        products.add(product);
        System.out.println("  Producto agregado: " + product.getDescription());
        return product;
    }

    // ===================== STRATEGY PATTERN =====================

    /**
     * Establece la estrategia de precios a utilizar.
     */
    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
        System.out.println("  Estrategia de precios cambiada a: " + strategy.getDescription());
    }

    /**
     * Calcula el subtotal (suma de precios base de todos los productos).
     */
    public double getSubtotal() {
        double subtotal = 0;
        for (Product p : products) {
            subtotal += p.getBasePrice();
        }
        return subtotal;
    }

    /**
     * Calcula el costo total de envío.
     */
    public double getTotalShipping() {
        double shipping = 0;
        for (Product p : products) {
            shipping += p.calculateShipping();
        }
        return shipping;
    }

    /**
     * Calcula el precio final aplicando la estrategia de precios al subtotal + envío.
     */
    public double calculateTotal() {
        double subtotal = getSubtotal();
        double finalPrice = pricingStrategy.calculateFinalPrice(subtotal);
        return finalPrice + getTotalShipping();
    }

    // ===================== OBSERVER PATTERN =====================

    @Override
    public void subscribe(OrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(OrderObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String orderId, String oldStatus, String newStatus) {
        for (OrderObserver observer : observers) {
            observer.update(orderId, oldStatus, newStatus);
        }
    }

    /**
     * Cambia el estado del pedido y notifica a todos los observadores.
     */
    public void changeStatus(String newStatus) {
        String oldStatus = this.status;
        this.status = newStatus;
        System.out.println("\n  Estado del pedido " + orderId + " cambiado: " + oldStatus + " -> " + newStatus);
        notifyObservers(orderId, oldStatus, newStatus);
    }

    // ===================== RESUMEN =====================

    /**
     * Imprime un resumen completo del pedido.
     */
    public void printSummary() {
        IO.println("\n========================================");
        IO.println("       RESUMEN DEL PEDIDO: " + orderId);
        IO.println("========================================");
        IO.println("Estado: " + status);
        IO.println("Estrategia: " + pricingStrategy.getDescription());
        IO.println("----------------------------------------");
        IO.println("Productos:");
        for (Product p : products) {
            System.out.printf("  - %-40s $%.2f%n", p.getDescription(), p.getBasePrice());
        }
        IO.println("----------------------------------------");
        double subtotal = getSubtotal();
        double shipping = getTotalShipping();
        double finalPrice = pricingStrategy.calculateFinalPrice(subtotal);
        double total = finalPrice + shipping;
        System.out.printf("Subtotal:                          $%.2f%n", subtotal);
        System.out.printf("Descuento aplicado:                $%.2f%n", subtotal - finalPrice);
        System.out.printf("Precio con descuento:              $%.2f%n", finalPrice);
        System.out.printf("Envío:                             $%.2f%n", shipping);
        System.out.printf("TOTAL:                             $%.2f%n", total);
        System.out.println("========================================\n");
    }

    // Getters
    public String getOrderId() { return orderId; }
    public String getStatus() { return status; }
    public List<Product> getProducts() { return products; }
}
