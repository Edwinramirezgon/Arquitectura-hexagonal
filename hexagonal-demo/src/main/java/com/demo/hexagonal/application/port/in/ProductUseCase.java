package com.demo.hexagonal.application.port.in;

import com.demo.hexagonal.domain.model.Product;
import java.util.List;

/**
 * PUERTO DE ENTRADA — Capa de Aplicación.
 *
 * Define el contrato de lo que la aplicación puede hacer.
 * Vive en 'application' porque es la frontera de los casos de uso,
 * no una regla de negocio del dominio.
 *
 * El dominio puro (domain/) solo contiene entidades y reglas de negocio.
 * Los puertos son la API de la aplicación hacia el exterior.
 */
public interface ProductUseCase {
    Product save(Product product);
    List<Product> findAll();
    void deleteById(Long id);
}
