package strategy;

public class BlackFridayPricing implements PricingStrategy{

    public double calculateFinalPrice(double price) {
        // 10% de descuento para miembros
        double discountRate = 0.3; // Descuento del 30% para Black Friday
        return price * (1 - discountRate); // Aplica el descuento
    }

    public String getDescription() { return "Precio de Black Friday (30% de descuento)"; }

}
