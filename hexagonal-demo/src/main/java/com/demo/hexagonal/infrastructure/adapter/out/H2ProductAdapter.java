package com.demo.hexagonal.infrastructure.adapter.out;

import com.demo.hexagonal.domain.model.Product;
import com.demo.hexagonal.application.port.out.ProductRepository;
import java.util.List;

/**
 * ADAPTADOR H2 - Infraestructura de salida.
 *
 * Implementa ProductRepository usando H2, una base de datos en memoria.
 *
 * ¿Para qué sirve H2 en arquitectura hexagonal?
 * - Tests unitarios: levanta y destruye la BD en milisegundos
 * - Desarrollo offline: no necesitas Docker ni servicios externos
 * - CI/CD pipelines: corre tests sin infraestructura
 *
 * DATO IMPORTANTE: Los datos se pierden al apagar la aplicación.
 * Eso es intencional para este perfil de desarrollo/testing.
 *
 * Se activa con: spring.profiles.active=h2
 * Consola web disponible en: http://localhost:8080/h2-console
 */
public class H2ProductAdapter implements ProductRepository {

    private final JpaProductRepository jpa;

    public H2ProductAdapter(JpaProductRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = new ProductEntity(product.getName(), product.getPrice());
        ProductEntity saved = jpa.save(entity);
        return new Product(saved.getId(), saved.getName(), saved.getPrice());
    }

    @Override
    public List<Product> findAll() {
        return jpa.findAll().stream()
                .map(e -> new Product(e.getId(), e.getName(), e.getPrice()))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }
}
