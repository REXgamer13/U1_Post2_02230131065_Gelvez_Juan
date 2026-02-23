package strategy;

public class RegularPricing implements PricingStrategy {
    public double calculateFinalPrice(double price) {
        return price;// No se aplica ningún descuento

    }
    public String getDescription() { return "Precio regular"; }


}
