package com.demo.hexagonal.infrastructure.adapter.out;

import com.demo.hexagonal.domain.model.Product;
import com.demo.hexagonal.domain.port.out.ProductRepository;
import java.util.List;

/**
 * ADAPTADOR SQL SERVER - Infraestructura de salida.
 *
 * Implementa el puerto ProductRepository usando SQL Server como tecnología.
 * Este es uno de los 3 adaptadores intercambiables de esta demo.
 *
 * PATRÓN APLICADO: Adapter Pattern
 * Convierte la interfaz del dominio (ProductRepository) en llamadas
 * concretas a JPA con SQL Server.
 *
 * Se activa con: spring.profiles.active=sqlserver
 * Conexión: localhost:1433 (Docker)
 *
 * El dominio (ProductService) nunca sabe que existe esta clase.
 * Solo conoce la interfaz ProductRepository.
 */
public class SqlServerProductAdapter implements ProductRepository {

    private final JpaProductRepository jpa;

    public SqlServerProductAdapter(JpaProductRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Product save(Product product) {
        // 1. Convertimos el objeto de dominio a entidad JPA (mapeo de entrada)
        ProductEntity entity = new ProductEntity(product.getName(), product.getPrice());

        // 2. Persistimos usando JPA (tecnología específica de este adaptador)
        ProductEntity saved = jpa.save(entity);

        // 3. Convertimos la entidad JPA de vuelta a objeto de dominio (mapeo de salida)
        // El dominio NUNCA recibe un ProductEntity, siempre recibe un Product limpio
        return new Product(saved.getId(), saved.getName(), saved.getPrice());
    }

    @Override
    public List<Product> findAll() {
        // Mapeamos cada entidad JPA a objeto de dominio antes de retornar
        return jpa.findAll().stream()
                .map(e -> new Product(e.getId(), e.getName(), e.getPrice()))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }
}
