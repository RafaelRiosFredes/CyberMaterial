package com.rrios.msvc.productos.msvc_productos.init;

import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;
import com.rrios.msvc.productos.msvc_productos.repositories.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    //CommandLineRunner:elemento que automaticamente se ejecuta cuando parte el main SOLO TEST
    @Bean
    CommandLineRunner initDataBase(ProductoRepository productoRepository){
        return args -> {
            if(productoRepository.count() == 0) {
                Producto caja = new Producto("Caja", 1200.00, "Caja");
                Producto lapizGrafito = new Producto("Lapiz grafito", 1200.00, "Lapiz grafito");

                log.info("Carga inicial {}", productoRepository.save(caja));
                log.info("Carga inicial {}", productoRepository.save(lapizGrafito));
            }
        };
    }
}
