package factory;

import model.*;

public class ProductFactory {

    public static Product createProduct(String type, String name, double price) {
        return switch (type.toUpperCase()) {

            case "ELECTRONICS" -> new Electronics(name, price, 12);
            case "CLOTHING" -> new Clothing(name, price, "M");
            case "FOOD" -> new Food(name, price, 30);
            default -> throw new IllegalArgumentException(
                    "Tipo desconocido: " + type);
        };

    }

}
