package com.demo.hexagonal.infrastructure.adapter.in;

import com.demo.hexagonal.domain.model.Product;
import com.demo.hexagonal.application.port.in.ProductUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ADAPTADOR DE ENTRADA (Driving Adapter) - El controlador REST.
 *
 * Traduce peticiones HTTP al lenguaje del dominio.
 * Es el "conductor" que usa el volante (ProductUseCase) para manejar el auto.
 *
 * IMPORTANTE: Este controlador NO sabe nada sobre:
 * - Qué base de datos se usa
 * - Cómo se persisten los datos
 * - Qué adaptador de salida está activo
 *
 * Solo conoce el puerto de entrada (ProductUseCase) y objetos de dominio (Product).
 * Si mañana cambiamos de REST a GraphQL, solo cambia este archivo.
 */
@RestController
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend HTML
public class ProductController {

    // Inyectamos el PUERTO DE ENTRADA, no la implementación concreta
    private final ProductUseCase useCase;

    // Leemos el perfil activo desde las properties para mostrarlo en la UI
    @Value("${spring.profiles.active:unknown}")
    private String activeProfile;

    public ProductController(ProductUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Endpoint para que la UI sepa qué base de datos está activa.
     * Útil para la demo visual de intercambio de adaptadores.
     */
    @GetMapping("/active-db")
    public ResponseEntity<Map<String, String>> activeDb() {
        // Mapeamos el perfil técnico a un nombre legible para la UI
        String dbName = switch (activeProfile) {
            case "sqlserver" -> "SQL Server 2022 (Docker)";
            case "postgres"  -> "PostgreSQL (Mac nativo)";
            case "h2"        -> "H2 (En memoria)";
            default          -> activeProfile;
        };
        return ResponseEntity.ok(Map.of("db", dbName, "profile", activeProfile));
    }

    /**
     * POST /products - Crea un nuevo producto.
     * Recibe JSON → convierte a Product (dominio) → delega al caso de uso.
     */
    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(useCase.save(product));
    }

    /**
     * GET /products - Lista todos los productos.
     * El caso de uso consulta el repositorio activo (sea cual sea la BD).
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(useCase.findAll());
    }

    /**
     * DELETE /products/{id} - Elimina un producto por ID.
     * Retorna 204 No Content si fue exitoso.
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        useCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
