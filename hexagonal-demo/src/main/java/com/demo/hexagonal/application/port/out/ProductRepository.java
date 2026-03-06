package com.demo.hexagonal.application.port.out;

import com.demo.hexagonal.domain.model.Product;
import java.util.List;

/**
 * PUERTO DE SALIDA — Capa de Aplicación.
 *
 * Define qué necesita la aplicación de la infraestructura para funcionar.
 * Vive en 'application' porque es un contrato que la aplicación impone
 * a la infraestructura, no una regla de negocio del dominio.
 *
 * Implementado por: SqlServerProductAdapter, PostgresProductAdapter, H2ProductAdapter
 */
public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll();
    void deleteById(Long id);
}
