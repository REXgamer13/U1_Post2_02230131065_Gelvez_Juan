package model;

public abstract class Product {
    // Atributos de la clase Product
    protected String name;
    protected double basePrice;
    protected String category;

    public abstract double calculateShipping();
    public abstract String getDescription();

    //getters
    public String getName() {return name;}
    public double getBasePrice() {return basePrice;}


}


