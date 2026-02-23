package model;

public class Food extends Product{

    private int expirationDate;

    // Constructor
    public Food(String name, double basePrice, int expirationDate) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = "Alimento";
        this.expirationDate = expirationDate;
    }


    // Shipping basado en peso/fragilidad
    public double calculateShipping() { return basePrice * 0.02; }

    public String getDescription() {
        return name + " [Alimento] - Fecha de Expiración: "
                + expirationDate;
    }


}
