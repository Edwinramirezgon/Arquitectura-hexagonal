package com.demo.hexagonal.application;

import com.demo.hexagonal.domain.model.Product;
import com.demo.hexagonal.application.port.in.ProductUseCase;
import com.demo.hexagonal.application.port.out.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * CAPA DE APLICACIÓN - El orquestador.
 *
 * ProductService conecta los puertos de entrada con los de salida.
 * Implementa ProductUseCase (puerto de entrada) para recibir peticiones.
 * Usa ProductRepository (puerto de salida) para persistir datos.
 *
 * IMPORTANTE: Este servicio NO sabe nada sobre:
 * - Qué base de datos se está usando (SQL Server, Postgres, H2)
 * - Cómo llegan las peticiones (HTTP, gRPC, CLI)
 * - Detalles de JPA, JDBC, o cualquier tecnología de persistencia
 *
 * Solo conoce objetos del dominio (Product) e interfaces (puertos).
 * Eso lo hace completamente testeable sin levantar ninguna infraestructura.
 */
@Service
public class ProductService implements ProductUseCase {

    // Inyectamos el PUERTO, no la implementación concreta.
    // Spring decide en tiempo de ejecución qué adaptador inyectar según el perfil activo.
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        // Aquí podría ir lógica de negocio: validaciones, cálculos, reglas, etc.
        // Por ejemplo: if (product.getPrice() < 0) throw new InvalidPriceException();
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
