package com.rrios.msvc.productos.msvc_productos.services;

import com.rrios.msvc.productos.msvc_productos.dtos.ProductoDTO;
import com.rrios.msvc.productos.msvc_productos.exceptions.ProductoException;
import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;
import com.rrios.msvc.productos.msvc_productos.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto productoPrueba;

    private ProductoDTO productoPruebaDTO;

    @BeforeEach
    public void setUp(){
        this.productoPrueba = new Producto(
                "Lapiz pasta",1200,"Lapiz pasta azul"
        );


    }

    @Test
    @DisplayName("Debo listar todos los metodos")
    public void shouldFindAllProductos(){
        Producto otroProducto = new Producto(
                "Cartulina",5000,"Cartulina española rosa"
        );
        List<Producto> productos = Arrays.asList(productoPrueba,otroProducto);
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> result = productoService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(productoPrueba);

        verify(productoRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Debo buscar un producto")
    public void shouldFIndById(){
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoPrueba));

        Producto result = productoService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(productoPrueba);
        verify(productoRepository,times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe buscar un producto con un id que no existe")
    public void shouldNotFindProductoId(){
        Long idInexistente = 999L;
        when(productoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            productoService.findById(idInexistente);
        }).isInstanceOf(ProductoException.class).hasMessageNotContaining("El producto con id "+ idInexistente +" no se encuentra en la base de datos");
        verify(productoRepository,times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo producto")
    public void shouldSaveProducto(){
        when(productoRepository.save(any(Producto.class))).thenReturn(productoPrueba);
        Producto result = productoService.save(productoPruebaDTO);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(productoPrueba);

        verify(productoRepository,times(1)).save(any(Producto.class));
    }
}
