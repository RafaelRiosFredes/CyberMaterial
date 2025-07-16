package com.rrios.msvc.boletas.msvc_boletas.services;

import com.rrios.msvc.boletas.msvc_boletas.clients.ClienteClientRest;
import com.rrios.msvc.boletas.msvc_boletas.clients.DetallecomprasClientRest;
import com.rrios.msvc.boletas.msvc_boletas.clients.ProductoClientRest;
import com.rrios.msvc.boletas.msvc_boletas.clients.SucursalClientRest;
import com.rrios.msvc.boletas.msvc_boletas.dtos.*;
import com.rrios.msvc.boletas.msvc_boletas.exceptions.BoletaException;
import com.rrios.msvc.boletas.msvc_boletas.models.Cliente;
import com.rrios.msvc.boletas.msvc_boletas.models.Detallecompras;
import com.rrios.msvc.boletas.msvc_boletas.models.Producto;
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

    @Mock
    private DetallecomprasClientRest detallecomprasClientRest;

    @Mock
    private ProductoClientRest productoClientRest;

    @InjectMocks
    private BoletaServiceImpl boletaService;

    private Cliente clienteTest;
    private ClienteDTO clienteTestDTO;
    private Sucursal sucursalTest;
    private SucursalDTO sucursalTestDTO;
    private Producto productoTest;
    private ProductoDTO productoDTOTest;
    private Detallecompras detallecomprasTest;
    private DetallecomprasDTO detallecomprasDTOTest;
    private Boleta boletaTest;
    private BoletaDTO boletaTestDTO;

    private List<Detallecompras> detallecomprasList = new ArrayList<>();

    private List<DetallecomprasDTO> detallecomprasDTOS = new ArrayList<>();

    private List<BoletaDTO> boletaDTOList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        clienteTest = new Cliente();
        clienteTest.setIdCliente(Long.valueOf(1L));
        clienteTest.setRun("20.333.666-2");
        clienteTest.setNombres("Rafael Ignacio");
        clienteTest.setApellidos("Ríos Fredes");
        clienteTest.setTelefono("(29) 123 4567");
        clienteTest.setCorreo("micorreo@duocuc.cl");
        clienteTest.setDireccion("Direccion Falsa 123");

        sucursalTest = new Sucursal();
        sucursalTest.setIdSucursal(Long.valueOf(1L));
        sucursalTest.setHorario("09:00-19:00");
        sucursalTest.setDireccion("Av Falsa 321, Peñablanca");

        productoTest = new Producto();

        productoTest.setIdProducto(1L);
        productoTest.setNombreProducto("Cartulina");
        productoTest.setPrecio(5000);
        productoTest.setDescripcion("Cartulina española rosa");

        productoDTOTest = new ProductoDTO();
        productoDTOTest.setIdProducto(productoTest.getIdProducto());
        productoDTOTest.setNombreProducto(productoTest.getNombreProducto());
        productoDTOTest.setPrecio(productoTest.getPrecio());
        productoDTOTest.setDescripcion(productoTest.getDescripcion());

        this.boletaTest = new Boleta(
                1L,1L,"2025-05-25",true,true
        );

        this.boletaTestDTO = new BoletaDTO();

        clienteTestDTO = new ClienteDTO();

        clienteTestDTO.setRun(clienteTest.getRun());
        clienteTestDTO.setNombres(clienteTest.getNombres());
        clienteTestDTO.setApellidos(clienteTest.getApellidos());
        clienteTestDTO.setTelefono(clienteTest.getTelefono());
        clienteTestDTO.setCorreo(clienteTest.getCorreo());
        clienteTestDTO.setDireccion(clienteTest.getDireccion());

        sucursalTestDTO = new SucursalDTO();

        sucursalTestDTO.setIdSucursal(sucursalTest.getIdSucursal());
        sucursalTestDTO.setHorario(sucursalTest.getHorario());
        sucursalTestDTO.setDireccion(sucursalTest.getDireccion());

        detallecomprasTest = new Detallecompras();

        detallecomprasTest.setIdDetallecompras(Long.valueOf(1L));
        detallecomprasTest.setCantidad(1L);
        detallecomprasTest.setTotal(1000.0);
        detallecomprasTest.setIdProducto(productoTest.getIdProducto());

        detallecomprasList.add(detallecomprasTest);

        detallecomprasDTOTest = new DetallecomprasDTO();
        detallecomprasDTOTest.setIdDetallecompras(detallecomprasTest.getIdDetallecompras());
        detallecomprasDTOTest.setCantidad(detallecomprasTest.getCantidad());
        detallecomprasDTOTest.setTotal(detallecomprasTest.getTotal());
        detallecomprasDTOTest.setProductoDTO(productoDTOTest);

        detallecomprasDTOS.add(detallecomprasDTOTest);

        boletaTestDTO.setClienteDTO(clienteTestDTO);
        boletaTestDTO.setSucursalDTO(sucursalTestDTO);
        boletaTestDTO.setFechaBoleta(boletaTest.getFechaBoleta());
        boletaTestDTO.setEstadoPago(boletaTest.getEstadoPago());
        boletaTestDTO.setEntregaPresencial(boletaTest.getEntregaPresencial());
        boletaTestDTO.setDetallesCompras(detallecomprasDTOS);

        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Boleta boletaCreate = new Boleta();


            Cliente clienteCreate = new Cliente();
            clienteCreate.setIdCliente(Long.valueOf(1L));
            clienteCreate.setRun("20.333.666-2");
            clienteCreate.setNombres("Rafael Ignacio");
            clienteCreate.setApellidos("Ríos Fredes");
            clienteCreate.setTelefono("(29) 123 4567");
            clienteCreate.setCorreo("micorreo@duocuc.cl");
            clienteCreate.setDireccion("Direccion Falsa 123");

            Sucursal sucursalCreate = new Sucursal();
            sucursalCreate.setIdSucursal(Long.valueOf(1L));
            sucursalCreate.setHorario("09:00-19:00");
            sucursalCreate.setDireccion("Av Falsa 341, Peñablanca");

            Producto productoCreate = new Producto();
            productoCreate.setIdProducto(1L);
            productoCreate.setNombreProducto("Cartulina");
            productoCreate.setPrecio(5000);
            productoCreate.setDescripcion("Cartulina española rosa");

            Detallecompras detallecomprasCreate = new Detallecompras();
            detallecomprasCreate.setIdDetallecompras(Long.valueOf(1L));
            detallecomprasCreate.setCantidad(1L);
            detallecomprasCreate.setTotal(1000.0);
            detallecomprasCreate.setIdProducto(productoCreate.getIdProducto());

            boletaCreate.setIdCliente(1L);
            boletaCreate.setIdSucursal(1L);
            boletaCreate.setFechaBoleta(LocalDate.parse("2025-05-25"));
            boletaCreate.setEntregaPresencial(true);
            boletaCreate.setEstadoPago(true);

            ClienteDTO clienteDTOCreate = new ClienteDTO();
            SucursalDTO sucursalDTOCreate = new SucursalDTO();
            ProductoDTO productoDTOCreate = new ProductoDTO();
            DetallecomprasDTO detallecomprasDTOCreate = new DetallecomprasDTO();
            BoletaDTO boletaDTOCreate = new BoletaDTO();

            clienteDTOCreate.setRun(clienteCreate.getRun());
            clienteDTOCreate.setNombres(clienteCreate.getNombres());
            clienteDTOCreate.setApellidos(clienteCreate.getApellidos());
            clienteDTOCreate.setTelefono(clienteCreate.getTelefono());
            clienteDTOCreate.setCorreo(clienteCreate.getCorreo());
            clienteDTOCreate.setDireccion(clienteCreate.getDireccion());

            sucursalDTOCreate.setIdSucursal(sucursalCreate.getIdSucursal());
            sucursalDTOCreate.setHorario(sucursalCreate.getHorario());
            sucursalDTOCreate.setDireccion(sucursalCreate.getDireccion());

            productoDTOCreate.setIdProducto(productoCreate.getIdProducto());
            productoDTOCreate.setNombreProducto(productoCreate.getNombreProducto());
            productoDTOCreate.setPrecio(productoCreate.getPrecio());
            productoDTOCreate.setDescripcion(productoCreate.getDescripcion());

            detallecomprasDTOCreate.setIdDetallecompras(detallecomprasCreate.getIdDetallecompras());
            detallecomprasDTOCreate.setCantidad(detallecomprasCreate.getCantidad());
            detallecomprasDTOCreate.setTotal(detallecomprasCreate.getTotal());
            detallecomprasDTOCreate.setProductoDTO(productoDTOCreate);

            detallecomprasDTOS.add(detallecomprasDTOCreate);

            boletaDTOCreate.setClienteDTO(clienteDTOCreate);
            boletaDTOCreate.setSucursalDTO(sucursalDTOCreate);
            boletaDTOCreate.setFechaBoleta(boletaCreate.getFechaBoleta());
            boletaDTOCreate.setEstadoPago(boletaCreate.getEstadoPago());
            boletaDTOCreate.setEntregaPresencial(boletaCreate.getEntregaPresencial());
            boletaDTOCreate.setDetallesCompras(detallecomprasDTOS);

            String numeroStr = faker.idNumber().valid();

            boletaDTOList.add(boletaDTOCreate);
        }
    }
    @Test
    @DisplayName("Debe listar todas las boletas")
    public void shouldFindAllBoletaDTOs()
    {
        boletaDTOList.add(boletaTestDTO);
        when(boletaRepository.findAllDTOs()).thenReturn(boletaDTOList);

        List<BoletaDTO> result = boletaService.findAllDTOs();

        assertThat(result).hasSize(101);
        assertThat(result).contains(boletaTestDTO);

        verify(clienteClientRest,times(1)).findById(1L);
        verify(sucursalClientRest,times(1)).findById(1L);
        verify(productoClientRest,times(1)).getProducto(1L);
        verify(boletaRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar una boleta")
    public void shouldFindDTOById(){
        when(boletaRepository.findById(1L)).thenReturn(Optional.of(boletaTest));
        when(boletaRepository.findDTOByIdBoleta(boletaTest.getIdBoleta())).thenReturn(boletaTestDTO);

        BoletaDTO result = boletaService.findDTOById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(boletaTestDTO);
        verify(clienteClientRest,times(1)).findById(boletaTest.getIdCliente());
        verify(sucursalClientRest,times(1)).findById(boletaTest.getIdSucursal());
        verify(productoClientRest,times(1)).getProducto(1L);
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
        when(productoClientRest.getProducto(1L)).thenReturn(productoTest);
        when(clienteClientRest.findById(1L)).thenReturn(clienteTest);
        when(sucursalClientRest.findById(1L)).thenReturn(sucursalTest);
        when(detallecomprasClientRest.findByIdBoleta(1L)).thenReturn(detallecomprasDTOS);

        when(boletaRepository.save(any(Boleta.class))).thenReturn(boletaTest);
        Boleta result = boletaService.save(boletaTest);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(boletaTest);

        verify(clienteClientRest,times(1)).findById(boletaTest.getIdCliente());
        verify(sucursalClientRest,times(1)).findById(boletaTest.getIdSucursal());
        verify(productoClientRest,times(1)).getProducto(1L);
        verify(detallecomprasClientRest,times(1)).findByIdBoleta(1L);
        verify(boletaRepository,times(1)).save(any(Boleta.class));
    }

    @Test
    @DisplayName("Debe eliminar una boleta por id")
    public void shouldDeleteBoletaById(){
        Long id = (Long) 1L;
        boletaService.deleteById(id);

        verify(clienteClientRest,times(1)).findById(1L);
        verify(sucursalClientRest,times(1)).findById(1L);
        verify(productoClientRest,times(1)).getProducto(1L);
        verify(boletaRepository,times(1)).deleteById(id);
    }

}


