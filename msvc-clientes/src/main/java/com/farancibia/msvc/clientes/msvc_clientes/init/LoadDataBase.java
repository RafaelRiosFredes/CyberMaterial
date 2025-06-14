package com.farancibia.msvc.clientes.msvc_clientes.init;


import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import com.farancibia.msvc.clientes.msvc_clientes.repositories.ClienteRepository;
import net.datafaker.Faker;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.logging.Logger;

@Component
public class LoadDataBase implements CommandLineRunner {

    private static final Logger log = (Logger) LoggerFactory.getLogger(LoadDataBase.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(clienteRepository.count() == 0){
            for(int i = 0;i<100;i++){
                Cliente cliente = new Cliente();

                String numeroStr = faker.idNumber().valid().replaceAll("-", "");
                String ultimo = numeroStr.substring(numeroStr.length() -1);
                String restante = numeroStr.substring(0, numeroStr.length()-1);

                cliente.setRun(restante+"-"+ultimo);
                cliente.setNombres(faker.name().fullName());
                cliente.setApellidos(faker.name().fullName());
                cliente.setCorreo(faker.internet().emailAddress());
                cliente.setDireccion(faker.address().fullAddress());

                cliente = clienteRepository.save(cliente);
                log.info("El cliente creado es "); // REVISAR ESTA LINEA
            }
        }

    }
}
