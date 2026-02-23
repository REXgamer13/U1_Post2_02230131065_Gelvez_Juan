package strategy;

public class MemberPricing implements PricingStrategy {

    public double calculateFinalPrice(double price) {
        // 10% de descuento para miembros
        double discountRate = 0.10;
        return price * (1 - discountRate); // Aplica el descuento
    }

    public String getDescription() { return "Precio para miembros (10% de descuento)"; }

}
