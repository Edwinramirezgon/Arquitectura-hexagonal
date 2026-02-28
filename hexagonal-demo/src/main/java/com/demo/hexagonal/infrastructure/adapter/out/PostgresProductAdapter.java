package com.demo.hexagonal.infrastructure.adapter.out;

import com.demo.hexagonal.domain.model.Product;
import com.demo.hexagonal.domain.port.out.ProductRepository;
import java.util.List;

/**
 * ADAPTADOR POSTGRESQL - Infraestructura de salida.
 *
 * Implementa el mismo puerto ProductRepository pero usando PostgreSQL.
 *
 * OBSERVA: El código interno es idéntico al de SqlServerProductAdapter.
 * Eso demuestra que la lógica de mapeo no cambia entre bases de datos relacionales.
 * Lo único que cambia es la configuración de conexión en application-postgres.properties.
 *
 * En un caso real, aquí podrías aprovechar características específicas de Postgres:
 * - Tipos de datos nativos (JSONB, arrays, UUID)
 * - Funciones específicas de Postgres
 * - Optimizaciones de queries
 * Sin que el dominio se entere de nada de eso.
 *
 * Se activa con: spring.profiles.active=postgres
 * Conexión: localhost:5432 (nativo Mac)
 */
public class PostgresProductAdapter implements ProductRepository {

    private final JpaProductRepository jpa;

    public PostgresProductAdapter(JpaProductRepository jpa) {
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
