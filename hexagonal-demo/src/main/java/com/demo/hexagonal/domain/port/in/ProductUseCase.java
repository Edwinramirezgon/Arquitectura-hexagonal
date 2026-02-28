package com.demo.hexagonal.domain.port.in;

import com.demo.hexagonal.domain.model.Product;
import java.util.List;

/**
 * PUERTO DE ENTRADA (Driving Port / Primary Port)
 *
 * Define QUÉ puede hacer la aplicación desde afuera.
 * Es el contrato que el mundo exterior (REST, CLI, UI) debe usar para hablar con el dominio.
 *
 * ANALOGÍA: Es como el volante de un auto. El conductor (Controller) lo usa
 * sin saber cómo funciona el motor (lógica de negocio) por dentro.
 *
 * Quien implementa esta interfaz: ProductService (capa de aplicación)
 * Quien la usa: ProductController (infraestructura de entrada)
 */
public interface ProductUseCase {

    // Guarda un producto nuevo o actualiza uno existente
    Product save(Product product);

    // Retorna todos los productos almacenados
    List<Product> findAll();

    // Elimina un producto por su identificador
    void deleteById(Long id);
}
