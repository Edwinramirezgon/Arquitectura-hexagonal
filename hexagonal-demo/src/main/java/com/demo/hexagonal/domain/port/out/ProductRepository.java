package com.demo.hexagonal.domain.port.out;

import com.demo.hexagonal.domain.model.Product;
import java.util.List;

/**
 * PUERTO DE SALIDA (Driven Port / Secondary Port)
 *
 * Define QUÉ necesita el dominio del mundo exterior para funcionar.
 * En este caso, necesita persistir datos, pero NO le importa CÓMO ni DÓNDE.
 *
 * ANALOGÍA: Es como el enchufe de la pared. La lámpara (dominio) solo sabe
 * que necesita electricidad (datos). No sabe si viene de la red, un generador
 * o una batería (SQL Server, PostgreSQL, H2).
 *
 * Quien implementa esta interfaz: SqlServerProductAdapter, PostgresProductAdapter, H2ProductAdapter
 * Quien la usa: ProductService (capa de aplicación)
 *
 * ESTE ES EL PUNTO CLAVE DE LA DEMO:
 * Para cambiar de base de datos, solo se cambia el adaptador que implementa esta interfaz.
 * El dominio nunca se entera del cambio.
 */
public interface ProductRepository {

    Product save(Product product);
    List<Product> findAll();
    void deleteById(Long id);
}
