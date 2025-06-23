package com.rrios.msvc.boletas.msvc_boletas.init;

import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;
import com.rrios.msvc.boletas.msvc_boletas.repositories.BoletaRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    BoletaRepository boletaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(boletaRepository.count() == 0){
            for(int i = 0;i<100;i++){
                Boleta boleta = new Boleta();

                boleta.setIdCliente(1L);
                boleta.setIdSucursal(1L);
                boleta.setFechaBoleta(LocalDate.parse("2025-05-23"));
                boleta.setEntregaPresencial(true);
                boleta.setEstadoPago(true);

                String numeroStr = faker.idNumber().valid();
                boleta = boletaRepository.save(boleta);
                log.info("La boleta creado es {}",boleta);
            }
        }
    }
}
