package com.squezada.msvc.detallecompras.msvc_detallecompras.init;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;
import com.squezada.msvc.detallecompras.msvc_detallecompras.repositories.DetallecomprasRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LoadDatabase implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    DetallecomprasRepository detallecomprasRepository;
    //CommandLineRunner:elemento que automaticamente se ejecuta cuando parte el main SOLO TEST

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(detallecomprasRepository.count() == 0){
            for(int i = 0;i<100;i++){
                Detallecompras detallecompras = new Detallecompras();

                detallecompras.setCantidad(2L);
                detallecompras.setTotal(2000.0);
                detallecompras.setIdProducto(1L);
                detallecompras.setIdBoleta(1L);
                detallecompras.setIdInventario(10L);


                String numeroStr = faker.idNumber().valid();
                detallecompras= detallecomprasRepository.save(detallecompras);
                log.info("EL detalle de compras creado es {}",detallecompras);
            }
        }
    }
}
