package strategy;

public interface PricingStrategy {
    double calculateFinalPrice(double originalPrice);

    String getDescription();
}
