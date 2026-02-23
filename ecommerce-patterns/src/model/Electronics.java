package model;

public class Electronics extends Product{
    private int warrantyMonths;

    // Constructor
    public Electronics(String name, double basePrice, int warrantyMonths) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = "Electrónica";
        this.warrantyMonths = warrantyMonths;
    }

    // Shipping basado en peso/fragilidad

    public double calculateShipping() { return basePrice * 0.05; }

    public String getDescription() {
        return name + " [Electrónica] - Garantía: "
                + warrantyMonths + " meses";
    }



}
