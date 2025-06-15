package com.farancibia.msvc.inventarios.msvc_inventarios.services;

import com.farancibia.msvc.inventarios.msvc_inventarios.clients.ProductoClientRest;
import com.farancibia.msvc.inventarios.msvc_inventarios.clients.SucursalClientRest;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Producto;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Sucursal;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import com.farancibia.msvc.inventarios.msvc_inventarios.repositories.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {

    @Mock
    private ProductoClientRest productoClientRest;

    @Mock
    private SucursalClientRest sucursalClientRest;

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioServiceImpl inventarioService;

    private Producto productoTest;
    private Sucursal sucursalTest;
    private Inventario inventarioTest;


    @BeforeEach
    public void setUp() {
        productoTest = new Producto();
        productoTest.setIdProducto(Long.valueOf(1L));
        productoTest.setNombreProducto("Lapiz pasta");
        productoTest.setDescripcion("Lapiz pasta de color azul");
        productoTest.setPrecio(Long.valueOf("450")); //revisar tipo de dato en precio

        sucursalTest = new Sucursal();
        sucursalTest.setIdSucursal(Long.valueOf(1L));
        sucursalTest.setDireccion("Calle falsa 123,Valparaiso");
        sucursalTest.setHoraApertura("Desde las 8:00 hasta las 16:30");

        inventarioTest = new Inventario();
        inventarioTest.setIdInventario(Long.valueOf(1L));
        inventarioTest.setIdSucursal(Long.valueOf(1L));
        inventarioTest.setIdProducto(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Creacion de test")
    public void shouldCreateInventario(){

        //Realiza las validaciones
        when(productoClientRest.findById(Long.valueOf(1L))).thenReturn(productoTest);
        when(sucursalClientRest.findById(Long.valueOf(1L))).thenReturn(sucursalTest);

        //Realizar el guardado
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioTest);

        //Llamo al servicio
        Inventario result = inventarioService.save(inventarioTest);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(inventarioTest);

        verify(productoClientRest,times(1)).findById(Long.valueOf(1L));
        verify(sucursalClientRest,times(1)).findById(Long.valueOf(1L));
        verify(inventarioRepository,times(1)).save(any(Inventario.class));
    }


}
