package com.rrios.msvc.productos.msvc_productos.services;

import com.rrios.msvc.productos.msvc_productos.dtos.ProductoDTO;
import com.rrios.msvc.productos.msvc_productos.exceptions.ProductoException;
import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;
import com.rrios.msvc.productos.msvc_productos.repositories.ProductoRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto productoTest;

    private ProductoDTO productoTestDTO;

    private List<Producto> productos = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.productoTest = new Producto(
                "Lapiz pasta",1200,"Lapiz pasta azul"
        );
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Producto productoCreate = new Producto();

            String numeroStr = faker.idNumber().valid();
            productoCreate.setNombreProducto("Cartulina");
            productoCreate.setPrecio(5000);
            productoCreate.setDescripcion("Cartulina española rosa");

            productos.add(productoCreate);
        }
    }

    @Test
    @DisplayName("Debo listar todos los metodos")
    public void shouldFindAllProductos(){
        List<Producto> productos = this.productos;
        productos.add(productoTest);
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> result = productoService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(productoTest);

        verify(productoRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Debo buscar un producto")
    public void shouldFindById(){
        when(productoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(productoTest));

        Producto result = productoService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(productoTest);
        verify(productoRepository,times(1)).findById(Long.valueOf(1L));
    }

    @Test
    @DisplayName("Debe buscar un producto con un id que no existe")
    public void shouldNotFindProductoId(){
        Long idInexistente = (Long) 999L;
        when(productoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            productoService.findById(idInexistente);
        }).isInstanceOf(ProductoException.class)
                .hasMessageNotContaining("El producto con id "+
                        idInexistente +" no se encuentra en la base de datos");
        verify(productoRepository,times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo producto")
    public void shouldSaveProducto(){
        when(productoRepository.save(any(Producto.class))).thenReturn(productoTest);
        Producto result = productoService.save(productoTestDTO);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(productoTest);

        verify(productoRepository,times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("Debe eliminar un producto por su id")
    public void shouldDeleteProductoById(){
        Long id = (Long) 1L;
        productoService.deleteById(id);

        verify(productoRepository,times(1)).deleteById(id);
    }
}
