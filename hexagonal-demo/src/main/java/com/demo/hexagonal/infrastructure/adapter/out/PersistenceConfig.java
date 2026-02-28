package com.demo.hexagonal.infrastructure.adapter.out;

import com.demo.hexagonal.domain.port.out.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * CONFIGURACIÓN DE PERSISTENCIA - El punto de intercambio de adaptadores.
 *
 * Esta clase es donde ocurre la "magia" de la arquitectura hexagonal.
 * Según el perfil activo en application.properties, Spring inyecta
 * un adaptador diferente en ProductService, sin que este lo sepa.
 *
 * FLUJO:
 * 1. Spring lee: spring.profiles.active=sqlserver
 * 2. Activa el @Bean marcado con @Profile("sqlserver")
 * 3. Inyecta SqlServerProductAdapter donde se pida ProductRepository
 * 4. ProductService recibe el adaptador correcto automáticamente
 *
 * Para cambiar de BD en producción:
 *   ANTES: spring.profiles.active=sqlserver
 *   DESPUÉS: spring.profiles.active=postgres
 *   CAMBIOS EN EL DOMINIO: CERO
 *   CAMBIOS EN LA LÓGICA: CERO
 *   CAMBIOS EN EL CONTROLADOR: CERO
 */
@Configuration
public class PersistenceConfig {

    // Se activa SOLO cuando el perfil "sqlserver" está activo
    @Bean
    @Profile("sqlserver")
    public ProductRepository sqlServerAdapter(JpaProductRepository jpa) {
        return new SqlServerProductAdapter(jpa);
    }

    // Se activa SOLO cuando el perfil "postgres" está activo
    @Bean
    @Profile("postgres")
    public ProductRepository postgresAdapter(JpaProductRepository jpa) {
        return new PostgresProductAdapter(jpa);
    }

    // Se activa SOLO cuando el perfil "h2" está activo
    @Bean
    @Profile("h2")
    public ProductRepository h2Adapter(JpaProductRepository jpa) {
        return new H2ProductAdapter(jpa);
    }
}
