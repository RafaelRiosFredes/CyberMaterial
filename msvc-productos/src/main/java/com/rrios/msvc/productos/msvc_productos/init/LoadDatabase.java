package com.rrios.msvc.productos.msvc_productos.init;

import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;
import com.rrios.msvc.productos.msvc_productos.repositories.ProductoRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class LoadDatabase implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    ProductoRepository productoRepository;
    //CommandLineRunner:elemento que automaticamente se ejecuta cuando parte el main SOLO TEST

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(productoRepository.count() == 0){
            for(int i = 0;i<100;i++){
                Producto producto = new Producto();

                producto.setDescripcion("Caja 30x40x50");
                producto.setNombreProducto("Caja");
                producto.setPrecio(4200);

                String numeroStr = faker.idNumber().valid();
            }
        }
    }
}
