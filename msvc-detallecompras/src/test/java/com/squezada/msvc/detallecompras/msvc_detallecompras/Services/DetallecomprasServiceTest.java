package com.squezada.msvc.detallecompras.msvc_detallecompras.Services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.*;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.BoletaDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.InventarioDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.ProductoDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.exceptions.DetallecomprasException;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.*;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;
import com.squezada.msvc.detallecompras.msvc_detallecompras.repositories.DetallecomprasRepository;
import com.squezada.msvc.detallecompras.msvc_detallecompras.services.DetallecomprasService;
import com.squezada.msvc.detallecompras.msvc_detallecompras.services.DetallecomprasServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)

 public class  DetallecomprasServiceTest {

    @Mock
    private DetallecomprasRepository detallecomprasRepository;

    @Mock
    private ProductoClientRest productoClientRest;

    @Mock
    private BoletaClientRest boletaClientRest;

    @Mock
    private InventarioClientRest inventarioClientRest;

    @Mock
    private ClienteClientRest clienteClientRest;

    @Mock
    private SucursalClientRest sucursalClientRest;




    @InjectMocks
    private DetallecomprasServiceImpl detallecomprasService;

    private Detallecompras detallePrueba;

    private List<Detallecompras> detalles = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        detallePrueba = new Detallecompras();
        detallePrueba.setCantidad(2L);
        detallePrueba.setTotal(4000.0);
        detallePrueba.setIdProducto(1L);
        detallePrueba.setIdBoleta(1L);
        detallePrueba.setIdInventario(1L);

        Faker faker = new Faker(new Locale("es", "CL"));
        for (int i = 0; i < 100; i++) {
            Detallecompras d = new Detallecompras();
            d.setCantidad((long) (i + 1));
            d.setTotal(1000.0 * (i + 1));
            d.setIdProducto((long) (i + 1));
            d.setIdBoleta(1L);
            d.setIdInventario((long) (i + 1));
            detalles.add(d);
        }
    }

    @Test
    @DisplayName("Debe listar todos los detalles de compras")
    public void shouldFindAllDetalles() {
        detalles.add(detallePrueba);
        when(detallecomprasRepository.findAll()).thenReturn(detalles);

        List<Detallecompras> result = detallecomprasService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(detallePrueba);

        verify(detallecomprasRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar un detalle de compra por ID")
    public void shouldFindById() {
        when(detallecomprasRepository.findById(1L)).thenReturn(Optional.of(detallePrueba));

        Producto productoMock = new Producto();
        productoMock.setIdProducto(1L);
        productoMock.setPrecio(2000.0);
        when(productoClientRest.findById(1L)).thenReturn(productoMock);

        Boleta boletaMock = new Boleta();
        boletaMock.setIdBoleta(1L);
        boletaMock.setIdSucursal(1L);
        when(boletaClientRest.findById(1L)).thenReturn(boletaMock);

        Cliente clienteMock = new Cliente();
        clienteMock.setIdCliente(1L);
        // Usar matcher anyLong para aceptar cualquier ID
        when(clienteClientRest.findById(any())).thenReturn(clienteMock);

        Sucursal sucursalMock = new Sucursal();
        sucursalMock.setIdSucursal(1L);
        when(sucursalClientRest.findById(any())).thenReturn(sucursalMock);
        DetalledecomprasDTO result = detallecomprasService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getCantidad()).isEqualTo(detallePrueba.getCantidad());
        assertThat(result.getTotal()).isEqualTo(detallePrueba.getTotal());

        verify(detallecomprasRepository, times(1)).findById(1L);
        verify(productoClientRest).findById(1L);
        verify(boletaClientRest).findById(1L);
        verify(clienteClientRest).findById(any());
        verify(sucursalClientRest).findById(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción si el detalle de compra no existe")
    public void shouldThrowExceptionWhenIdNotFound() {
        long idInexistente = 999L;
        when(detallecomprasRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> detallecomprasService.findById(idInexistente))
                .isInstanceOf(DetallecomprasException.class)
                .hasMessageContaining("El detalle de compra con id: " + idInexistente + " no se encuentra");

        verify(detallecomprasRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo detalle de compra")
    public void shouldSaveDetalle() {
        // Mock objetos externos
        Producto productoMock = new Producto();
        productoMock.setIdProducto(1L);
        productoMock.setPrecio(2000.0);

        Boleta boletaMock = new Boleta();
        boletaMock.setIdBoleta(1L);
        boletaMock.setIdSucursal(1L);

        Inventario inventarioMock = new Inventario();
        inventarioMock.setIdInventario(1L);
        inventarioMock.setStock(10);

        when(productoClientRest.findById(1L)).thenReturn(productoMock);
        when(boletaClientRest.findById(1L)).thenReturn(boletaMock);
        when(inventarioClientRest.findByIdSucursalAndIdProducto(1L, 1L)).thenReturn(inventarioMock);
        when(detallecomprasRepository.save(any(Detallecompras.class))).thenReturn(detallePrueba);

        Detallecompras nuevoDetalle = new Detallecompras();
        nuevoDetalle.setCantidad(2L);
        nuevoDetalle.setIdProducto(1L);
        nuevoDetalle.setIdBoleta(1L);

        Detallecompras result = detallecomprasService.save(nuevoDetalle);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(detallePrueba);

        verify(detallecomprasRepository, times(1)).save(any(Detallecompras.class));
        verify(inventarioClientRest).update(eq(1L), any(Inventario.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando stock es insuficiente")
    public void shouldThrowExceptionWhenStockIsInsufficient() {
        // Preparar detalle de compra con cantidad solicitada mayor al stock
        Detallecompras detalle = new Detallecompras();
        detalle.setCantidad(10L); // cantidad solicitada
        detalle.setIdProducto(1L);
        detalle.setIdBoleta(1L);

        Producto productoMock = new Producto();
        productoMock.setIdProducto(1L);
        productoMock.setPrecio(1000.0);

        Boleta boletaMock = new Boleta();
        boletaMock.setIdBoleta(1L);
        boletaMock.setIdSucursal(1L);

        Inventario inventarioMock = new Inventario();
        inventarioMock.setIdInventario(1L);
        inventarioMock.setStock(5); // stock menor que cantidad solicitada

        // Mockear los llamados a servicios externos
        when(productoClientRest.findById(1L)).thenReturn(productoMock);
        when(boletaClientRest.findById(1L)).thenReturn(boletaMock);
        when(inventarioClientRest.findByIdSucursalAndIdProducto(boletaMock.getIdSucursal(), productoMock.getIdProducto()))
                .thenReturn(inventarioMock);

        // Verificar que al intentar guardar lanza la excepción
        assertThatThrownBy(() -> detallecomprasService.save(detalle))
                .isInstanceOf(DetallecomprasException.class)
                .hasMessageContaining("Stock insuficiente");

        // Verificar que no se llamó a guardar en el repositorio porque falla antes
        verify(detallecomprasRepository, never()).save(any(Detallecompras.class));
    }
}


