package com.rrios.msvc.boletas.msvc_boletas.services;

import com.rrios.msvc.boletas.msvc_boletas.clients.ClienteClientRest;
import com.rrios.msvc.boletas.msvc_boletas.clients.SucursalClientRest;
import com.rrios.msvc.boletas.msvc_boletas.dtos.BoletaDTO;
import com.rrios.msvc.boletas.msvc_boletas.dtos.ClienteDTO;
import com.rrios.msvc.boletas.msvc_boletas.dtos.SucursalDTO;
import com.rrios.msvc.boletas.msvc_boletas.exceptions.BoletaException;
import com.rrios.msvc.boletas.msvc_boletas.models.Cliente;
import com.rrios.msvc.boletas.msvc_boletas.models.Sucursal;
import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;
import com.rrios.msvc.boletas.msvc_boletas.repositories.BoletaRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BoletaServiceTest {
    @Mock
    private BoletaRepository boletaRepository;

    @Mock
    private ClienteClientRest clienteClientRest;

    @Mock
    private SucursalClientRest sucursalClientRest;

    @InjectMocks
    private BoletaServiceImpl boletaService;

    private Cliente clienteTest;
    private ClienteDTO clienteTestDTO;
    private Sucursal sucursalTest;
    private SucursalDTO sucursalTestDTO;
    private Boleta boletaTest;
    private BoletaDTO boletaTestDTO;

    private List<BoletaDTO> boletaDTOList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        clienteTest = new Cliente();
        clienteTest.setIdCliente(Long.valueOf(1L));
        clienteTest.setRun("20.333.666-2");
        clienteTest.setNombres("Rafael Ignacio");
        clienteTest.setApellidos("Ríos Fredes");
        clienteTest.setTelefono(1234567);
        clienteTest.setCorreo("micorreo@duocuc.cl");
        clienteTest.setDireccion("Direccion Falsa 123");

        sucursalTest = new Sucursal();
        sucursalTest.setIdSucursal(Long.valueOf(1L));
        sucursalTest.setHorario("09:00-19:00");
        sucursalTest.setDireccion("Av Falsa 321, Peñablanca");

        this.boletaTest = new Boleta(
                1L,1L,"2025-05-25",true,true
        );

        this.boletaTestDTO = new BoletaDTO();

        clienteTestDTO.setIdCliente(clienteTest.getIdCliente());
        clienteTestDTO.setRun(clienteTest.getRun());
        clienteTestDTO.setNombres(clienteTest.getNombres());
        clienteTestDTO.setApellidos(clienteTest.getApellidos());
        clienteTestDTO.setTelefono(clienteTest.getTelefono());
        clienteTestDTO.setCorreo(clienteTest.getCorreo());
        clienteTestDTO.setDireccion(clienteTest.getDireccion());

        sucursalTestDTO.setIdSucursal(sucursalTest.getIdSucursal());
        sucursalTestDTO.setHorario(sucursalTest.getHorario());
        sucursalTestDTO.setDireccion(sucursalTest.getDireccion());

        boletaTestDTO.setClienteDTO(clienteTestDTO);
        boletaTestDTO.setSucursalDTO(sucursalTestDTO);
        boletaTestDTO.setFechaBoleta(boletaTest.getFechaBoleta());
        boletaTestDTO.setEstadoPago(boletaTest.getEstadoPago());
        boletaTestDTO.setEntregaPresencial(boletaTest.getEntregaPresencial());

        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Boleta boletaCreate = new Boleta();
            String numeroStr = faker.idNumber().valid();

            Cliente clienteCreate = new Cliente();
            clienteCreate.setIdCliente(Long.valueOf(1L));
            clienteCreate.setRun("20.333.666-2");
            clienteCreate.setNombres("Rafael Ignacio");
            clienteCreate.setApellidos("Ríos Fredes");
            clienteCreate.setTelefono(1234567);
            clienteCreate.setCorreo("micorreo@duocuc.cl");
            clienteCreate.setDireccion("Direccion Falsa 123");

            Sucursal sucursalCreate = new Sucursal();
            sucursalCreate = new Sucursal();
            sucursalCreate.setIdSucursal(Long.valueOf(1L));
            sucursalCreate.setHorario("09:00-19:00");
            sucursalCreate.setDireccion("Av Falsa 341, Peñablanca");

            boletaCreate.setIdCliente(1L);
            boletaCreate.setIdSucursal(1L);
            boletaCreate.setFechaBoleta(LocalDate.parse("2025-05-25"));
            boletaCreate.setEntregaPresencial(true);
            boletaCreate.setEstadoPago(true);

            ClienteDTO clienteDTOCreate = new ClienteDTO();
            SucursalDTO sucursalDTOCreate = new SucursalDTO();
            BoletaDTO boletaDTOCreate = new BoletaDTO();

            clienteDTOCreate.setIdCliente(clienteCreate.getIdCliente());
            clienteDTOCreate.setRun(clienteCreate.getRun());
            clienteDTOCreate.setNombres(clienteCreate.getNombres());
            clienteDTOCreate.setApellidos(clienteCreate.getApellidos());
            clienteDTOCreate.setTelefono(clienteCreate.getTelefono());
            clienteDTOCreate.setCorreo(clienteCreate.getCorreo());
            clienteDTOCreate.setDireccion(clienteCreate.getDireccion());

            sucursalDTOCreate.setIdSucursal(sucursalCreate.getIdSucursal());
            sucursalDTOCreate.setHorario(sucursalCreate.getHorario());
            sucursalDTOCreate.setDireccion(sucursalCreate.getDireccion());

            boletaDTOCreate.setClienteDTO(clienteDTOCreate);
            boletaDTOCreate.setSucursalDTO(sucursalDTOCreate);
            boletaDTOCreate.setFechaBoleta(boletaCreate.getFechaBoleta());
            boletaDTOCreate.setEstadoPago(boletaCreate.getEstadoPago());
            boletaDTOCreate.setEntregaPresencial(boletaCreate.getEntregaPresencial());

            boletaDTOList.add(boletaDTOCreate);
        }
    }
    @Test
    @DisplayName("Debe listar todas las boletas")
    public void shouldFindAllBoletaDTOs()
    {
        List<BoletaDTO> boletaDTOS = this.boletaDTOList;
        boletaDTOS.add(boletaTestDTO);

        List<BoletaDTO> result = boletaService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(boletaTestDTO);

        verify(boletaRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar una boleta")
    public void shouldFindDTOById(){
        when(boletaRepository.findDTOByIdBoleta(Long.valueOf(1L))).thenReturn(boletaTestDTO);

        BoletaDTO result = boletaService.findDTOById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(boletaTestDTO);
        verify(boletaRepository,times(1)).findDTOByIdBoleta(1L);
    }

    @Test
    @DisplayName("Debe buscar una boleta con un id que no existe")
    public void shouldNotFindBoletaId(){
        Long idInexistente = (Long) 999L;
        assertThatThrownBy(()->{
            boletaService.findDTOById(idInexistente);
        }).isInstanceOf(BoletaException.class)
                .hasMessageNotContaining("La boleta con id "+
                        idInexistente + " no se encuentra en la base de datos.");
        verify(boletaRepository,times(1)).findDTOByIdBoleta(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar una nueva boleta")
    public void shouldSaveBoleta(){
        when(boletaRepository.save(any(BoletaDTO.class))).thenReturn(boletaTest);
        Boleta result = boletaService.save(boletaTestDTO);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(boletaTestDTO);

        verify(boletaRepository,times(1)).save(any(BoletaDTO.class));
    }

    @Test
    @DisplayName("Debe eliminar una boleta por id")
    public void shouldDeleteBoletaById(){
        Long id = (Long) 1L;
        boletaService.deleteById(id);

        verify(boletaRepository,times(1)).deleteById(id);
    }

}


