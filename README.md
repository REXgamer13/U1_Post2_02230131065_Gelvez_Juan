# U1_Post2_02230131065_Gelvez_Juan
Fundamentos de Patrones de Diseño y Buenas Prácticas  Post-Contenido 2 
Proyecto Sistema de E-Commerce con Patrones Observer, Factory y Strategy


## Objetivo
Implementar un sistema de e-commerce simplificado en Java que integre tres
patrones de diseño fundamentales (Observer, Factory Method y Strategy) junto con
principios SOLID y Clean Code, demostrando la capacidad de tomar decisiones de
diseño justificadas.

## Proyecto: Sistema de E-Commerce con Patrones de Diseño
Usted va a construir un sistema de e-commerce simplificado que gestione productos,
aplique descuentos y notifique a los usuarios sobre cambios en sus pedidos. El sistema
utilizará tres patrones de diseño del GoF.

## Diagrama UML textual (resumen)
```
Product (abstract)
  + getName()
  + getBasePrice()
  + calculateShipping()
  + getDescription()
    ^
    |-- Electronics
    |-- Clothing
    |-- Food

ProductFactory
  + createProduct(type, name, price): Product
  --> crea Electronics | Clothing | Food

PricingStrategy (interface)
  + calculateFinalPrice(originalPrice)
  + getDescription()
    ^
    |-- RegularPricing
    |-- MemberPricing
    |-- BlackFridayPricing
    |-- BulkPricing

OrderObserver (interface)
  + update(orderId, oldStatus, newStatus)
    ^
    |-- EmailNotifier
    |-- SMSNotifier
    |-- LogNotifier

OrderSubject (interface)
  + subscribe(observer)
  + unsubscribe(observer)
  + notifyObservers(orderId, oldStatus, newStatus)

OrderService implements OrderSubject
  - products: List<Product>
  - pricingStrategy: PricingStrategy
  - observers: List<OrderObserver>
  + addProduct(type, name, price): Product  --> usa ProductFactory
  + setPricingStrategy(strategy)
  + changeStatus(newStatus) --> notifica observers
  + calculateTotal() --> usa PricingStrategy
```

## Justificacion de patrones elegidos
- Factory Method: centraliza la creacion de productos en `ProductFactory`, evitando `new` disperso y permitiendo agregar nuevos tipos sin cambiar el flujo principal.
- Strategy: `PricingStrategy` encapsula reglas de descuento y permite cambiar el calculo de precios en tiempo de ejecucion sin modificar `OrderService`.
- Observer: `OrderSubject` y `OrderObserver` desacoplan los cambios de estado del pedido de las notificaciones, permitiendo agregar o quitar canales (email, SMS, log) sin afectar el servicio principal.
