package com.squezada.msvc.sucursales.msvc_sucursales.init;


import com.squezada.msvc.sucursales.msvc_sucursales.models.entities.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.repositories.SucursalRepository;
import net.datafaker.Faker;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.logging.Logger;

@Component
public class LoadDatabase implements CommandLineRunner {
    private static final Logger log = (Logger) LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));

        if (sucursalRepository.count() == 0) {
            for (int i = 0; i < 100; i++) {
                Sucursal sucursal = new Sucursal();

                sucursal.setHorario("Horario 9:00-19:00 hrs.");
                sucursal.setDireccion("Av Matta 123, Coquimbo");
            }
        }
    }

}
