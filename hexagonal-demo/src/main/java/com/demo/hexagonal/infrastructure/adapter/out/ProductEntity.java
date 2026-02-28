package com.demo.hexagonal.infrastructure.adapter.out;

import jakarta.persistence.*;

/**
 * ENTIDAD JPA - Pertenece a la infraestructura, NO al dominio.
 *
 * Esta clase es el "mapeo técnico" entre el objeto de dominio (Product)
 * y la tabla en la base de datos.
 *
 * ¿Por qué no usamos Product directamente con anotaciones JPA?
 * Porque eso contaminaría el dominio con detalles técnicos de persistencia.
 * Si cambiamos de JPA a MongoDB, solo cambia esta clase, no el dominio.
 *
 * Flujo de datos:
 *   HTTP Request → Product (dominio) → ProductEntity (JPA) → Base de datos
 *   Base de datos → ProductEntity (JPA) → Product (dominio) → HTTP Response
 */
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La BD genera el ID automáticamente
    private Long id;

    private String name;
    private double price;

    public ProductEntity() {}

    public ProductEntity(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
