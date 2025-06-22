package com.farancibia.msvc.clientes.msvc_clientes.services;

import com.farancibia.msvc.clientes.msvc_clientes.dtos.ClienteDTO;
import com.farancibia.msvc.clientes.msvc_clientes.exceptions.ClienteException;
import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import com.farancibia.msvc.clientes.msvc_clientes.repositories.ClienteRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente clientePrueba;

    private ClienteDTO clienteTestDTO;

    private List<Cliente> clientes = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        this.clientePrueba = new Cliente(
                "11111111-1", "Maria Jose", "Arancibia Muñoz", "Ma.524@gmail.com","Calle falsa 123"
        );

        this.clienteTestDTO = new ClienteDTO();
        clienteTestDTO.setRun("11111111-1");
        clienteTestDTO.setNombres("Maria Jose");
        clienteTestDTO.setApellidos("Arancibia Muñoz");
        clienteTestDTO.setCorreo("Ma.524@gmail.com");
        clienteTestDTO.setDireccion("Calle falsa 123");

        Faker faker = new Faker(Locale.of("es", "CL"));
        for(int i = 0; i < 100; i++){
            Cliente clienteCreate = new Cliente();

            String numeroStr = faker.idNumber().valid().replaceAll("-", "");
            String ultimo = numeroStr.substring(numeroStr.length() -1);
            String restante = numeroStr.substring(0, numeroStr.length()-1);

            clienteCreate.setRun(restante+"-"+ultimo);
            clienteCreate.setNombres(faker.name().fullName());
            clienteCreate.setApellidos(faker.name().fullName());
            clienteCreate.setCorreo(faker.internet().emailAddress());
            clienteCreate.setDireccion(faker.address().fullAddress());

            clientes.add(clienteCreate);
        }
    }

    @Test
    @DisplayName("Debe listar todos los clientes")
    public void shouldFindAllClientes(){

        List<Cliente> clientes = this.clientes;
        clientes.add(clientePrueba);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(clientePrueba);

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar un cliente")
    public void shouldFindById(){
        when(clienteRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(this.clientePrueba));

        Cliente result = clienteService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(clientePrueba);
        verify(clienteRepository, times(1)).findById(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Debe buscar un cliente con ID que no existe")
    public void shoulNotFindClienteId(){
        long idInexistente = (Long) 999L;
        when(clienteRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            clienteService.findById(idInexistente);
        }).isInstanceOf(ClienteException.class)
                .hasMessageContaining("El Cliente con id " +
                idInexistente + " no se encuentra en la base de datos");
        verify(clienteRepository, Mockito.times(1)).findById(idInexistente);

    }

    @Test
    @DisplayName("Debe guardar un nuevo cliente")
    public void shouldsaveCliente(){
        when(clienteRepository.save(any(Cliente.class))).thenReturn(this.clientePrueba);
        Cliente result = clienteService.save(clienteTestDTO);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(clientePrueba);
        verify(clienteRepository, times (1)).save(any(Cliente.class));
    }
}
