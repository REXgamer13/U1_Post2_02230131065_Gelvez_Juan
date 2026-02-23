package model;

public class Clothing extends Product{

    //atributos específicos de ropa
    private String size;

    // Constructor
    public Clothing(String name, double basePrice, String size) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = "Ropa";
        this.size = size;
    }

    // Shipping basado en peso/fragilidad
    public double calculateShipping() { return basePrice * 0.03; }
    public String getDescription() {
        return name + " [Ropa] - Talla: " + size;
    }

}
