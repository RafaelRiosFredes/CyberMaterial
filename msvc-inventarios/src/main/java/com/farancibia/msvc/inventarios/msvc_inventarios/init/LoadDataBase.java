package com.farancibia.msvc.inventarios.msvc_inventarios.init;

import com.farancibia.msvc.inventarios.msvc_inventarios.models.Producto;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Sucursal;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import com.farancibia.msvc.inventarios.msvc_inventarios.repositories.InventarioRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

@Component
public class LoadDataBase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Autowired
    InventarioRepository inventarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es", "CL"));

        if (inventarioRepository.count() == 0) {
            List<Long> productosIds = List.of(1L);
            List<Long> sucursalesIds = List.of(1L);

            for (int i = 0; i < 100; i++) {
                Inventario inventario = new Inventario();
                inventario.setStock(faker.number().numberBetween(10, 1000));

                Long idProducto = faker.options().nextElement(productosIds);
                Long idSucursal = faker.options().nextElement(sucursalesIds);

                inventario.setIdProducto(idProducto);
                inventario.setIdSucursal(idSucursal);

                inventarioRepository.save(inventario);
                log.info("Inventario creado: {}", inventario);
            }
        }
    }
}
