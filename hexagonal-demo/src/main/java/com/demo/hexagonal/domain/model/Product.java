package com.demo.hexagonal.domain.model;

/**
 * CAPA DE DOMINIO - El corazón de la arquitectura hexagonal.
 *
 * Esta clase representa la entidad de negocio "Product".
 * REGLA DE ORO: No tiene ninguna anotación de Spring, JPA, ni ningún framework.
 * Esto garantiza que el dominio sea completamente independiente de la tecnología.
 *
 * Si mañana cambiamos de Spring a Quarkus, o de JPA a JDBC puro,
 * esta clase NO se toca. Eso es el beneficio principal.
 */
public class Product {

    private Long id;
    private String name;
    private double price;

    public Product() {}

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
}
