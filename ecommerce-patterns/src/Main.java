import factory.ProductFactory;
import model.Product;
import observer.EmailNotifier;
import observer.LogNotifier;
import observer.SMSNotifier;
import service.OrderService;
import strategy.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║     DEMOSTRACIÓN E-COMMERCE DESIGN PATTERNS      ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // ============================================================
        // a) Creación de productos usando Factory (uno de cada tipo)
        // ============================================================
        System.out.println("\n--- FACTORY PATTERN: Creación de productos ---");

        Product laptop = ProductFactory.createProduct("ELECTRONICS", "Laptop Gaming", 1500.00);
        System.out.println("  Creado: " + laptop.getDescription() + " | Precio: $" + laptop.getBasePrice());

        Product camiseta = ProductFactory.createProduct("CLOTHING", "Camiseta Polo", 45.00);
        System.out.println("  Creado: " + camiseta.getDescription() + " | Precio: $" + camiseta.getBasePrice());

        Product pizza = ProductFactory.createProduct("FOOD", "Pizza Congelada", 12.50);
        System.out.println("  Creado: " + pizza.getDescription() + " | Precio: $" + pizza.getBasePrice());

        // ============================================================
        // b) Cálculo de precios con diferentes estrategias
        // ============================================================
        System.out.println("\n--- STRATEGY PATTERN: Cálculos de precios ---");

        double precioBase = laptop.getBasePrice();
        System.out.println("\n  Precio base del Laptop: $" + precioBase);

        // Estrategia Regular
        PricingStrategy regular = new RegularPricing();
        System.out.printf("  > %s: $%.2f%n", regular.getDescription(),
                regular.calculateFinalPrice(precioBase));

        // Estrategia Member
        PricingStrategy member = new MemberPricing();
        System.out.printf("  > %s: $%.2f%n", member.getDescription(),
                member.calculateFinalPrice(precioBase));

        // Estrategia Black Friday
        PricingStrategy blackFriday = new BlackFridayPricing();
        System.out.printf("  > %s: $%.2f%n", blackFriday.getDescription(),
                blackFriday.calculateFinalPrice(precioBase));

        // Estrategia Bulk (con 15 unidades)
        PricingStrategy bulk = new BulkPricing(15);
        System.out.printf("  > %s: $%.2f%n", bulk.getDescription(),
                bulk.calculateFinalPrice(precioBase));

        // ============================================================
        // c) y d) OrderService integrando los 3 patrones
        // ============================================================
        System.out.println("\n--- INTEGRACIÓN: OrderService con los 3 patrones ---");

        // Crear el servicio de pedido
        OrderService order = new OrderService("ORD-2026-001");

        // Suscribir observadores (Observer Pattern)
        System.out.println("\n  Suscribiendo observadores...");
        order.subscribe(new EmailNotifier());
        order.subscribe(new SMSNotifier());
        order.subscribe(new LogNotifier());
        System.out.println("  ✓ EmailNotifier suscrito");
        System.out.println("  ✓ SMSNotifier suscrito");
        System.out.println("  ✓ LogNotifier suscrito");

        // Agregar productos usando Factory a través del OrderService
        System.out.println("\n  Agregando productos al pedido:");
        order.addProduct("ELECTRONICS", "Smartphone Samsung", 899.99);
        order.addProduct("CLOTHING", "Chaqueta de Cuero", 120.00);
        order.addProduct("FOOD", "Café Premium 1kg", 25.00);

        // Calcular con estrategia regular
        order.setPricingStrategy(new RegularPricing());
        System.out.printf("  Total (precio regular): $%.2f%n", order.calculateTotal());

        // Cambiar estrategia a Black Friday
        order.setPricingStrategy(new BlackFridayPricing());
        System.out.printf("  Total (Black Friday): $%.2f%n", order.calculateTotal());

        // Notificaciones de cambio de estado del pedido
        System.out.println("\n--- OBSERVER PATTERN: Cambios de estado ---");
        order.changeStatus("PROCESANDO");
        order.changeStatus("ENVIADO");
        order.changeStatus("ENTREGADO");

        // d) Resumen final del pedido
        System.out.println("\n--- RESUMEN FINAL DEL PEDIDO ---");
        order.printSummary();

        System.out.println("✓ Demostración completada exitosamente.");
    }
}

