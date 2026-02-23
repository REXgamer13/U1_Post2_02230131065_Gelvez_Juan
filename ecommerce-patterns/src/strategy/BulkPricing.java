package strategy;

public class BulkPricing implements PricingStrategy {

    private int quantity;

    public BulkPricing(int quantity) {
        this.quantity = quantity;
    }

    public double calculateFinalPrice(double originalPrice)
    {
    // Descuento del 20% para compras de 10 o más unidades
        double discountRate = (quantity >= 10) ? 0.2 : 0.0;
        return originalPrice * (1 - discountRate); // Aplica el descuento si corresponde
    }

    public String getDescription(){
        return "Descuento por compra al por mayor (20% de descuento para 10 o más unidades)";

    }
}
