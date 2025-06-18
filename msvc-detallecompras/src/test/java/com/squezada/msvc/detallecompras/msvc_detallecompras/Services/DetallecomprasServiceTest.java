package com.squezada.msvc.detallecompras.msvc_detallecompras.Services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.exceptions.DetallecomprasException;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;
import com.squezada.msvc.detallecompras.msvc_detallecompras.repositories.DetallecomprasRepository;
import com.squezada.msvc.detallecompras.msvc_detallecompras.services.DetallecomprasService;
import com.squezada.msvc.detallecompras.msvc_detallecompras.services.DetallecomprasServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DetallecomprasServiceTest {

    @Mock
    private DetallecomprasRepository detallecomprasRepository;

    @InjectMocks
    private DetallecomprasServiceImpl detallecomprasService;

    private Detallecompras detallecomprasTest;

    private DetalledecomprasDTO detallecomprasPruebaDTO;

    @BeforeEach
    public void setUp(){
        detallecomprasTest = new Detallecompras();
        detallecomprasTest.setCantidad(2L);
        detallecomprasTest.setTotal(2000.0);
        detallecomprasTest.setIdProducto(1L);
        detallecomprasTest.setIdBoleta(1L);
        detallecomprasTest.setIdInventario(1L);

        detallecomprasPruebaDTO = new DetalledecomprasDTO();
        detallecomprasPruebaDTO.setCantidad(2L);
        detallecomprasPruebaDTO.setTotal(2000.0);
    }

    @Test
    @DisplayName("Debo listar todos los detalles de compras")
    public void shouldFindAllDetallecompras(){
        Detallecompras otroDetallecompras = new Detallecompras();
        otroDetallecompras.setCantidad(3L);
        otroDetallecompras.setTotal(3000.0);
        otroDetallecompras.setIdProducto(2L);
        otroDetallecompras.setIdBoleta(1L);
        otroDetallecompras.setIdInventario(2L);

        List<Detallecompras> detallecompras = Arrays.asList(detallecomprasTest,otroDetallecompras);
        when(detallecomprasRepository.findAll()).thenReturn(detallecompras);

        List<Detallecompras> result = detallecomprasService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(detallecomprasTest);

        verify(detallecomprasRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Debo buscar un detalle de compras por id")
    public void shouldFIndDetallecomprasById(){
        when(detallecomprasRepository.findById(1L)).thenReturn(Optional.of(detallecomprasTest));

        Detallecompras result = detallecomprasService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(detallecomprasTest);
        verify(detallecomprasRepository,times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe buscar un detalle de compras con un id que no existe")
    public void shouldNotFindDetallecomprasId(){
        Long idInexistente = 999L;
        when(detallecomprasRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            detallecomprasService.findById(idInexistente);
        }).isInstanceOf(DetallecomprasException.class).hasMessageNotContaining("El detalle de compras con id "+ idInexistente +" no se encuentra en la base de datos");
        verify(detallecomprasRepository,times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo detalle de compras")
    public void shouldSaveDetallecompras(){
        when(detallecomprasService.save(any(Detallecompras.class))).thenReturn(detallecomprasTest);
        Detallecompras result = detallecomprasService.save(detallecomprasPruebaDTO);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(detallecomprasTest);

        verify(detallecomprasRepository,times(1)).save(any(Detallecompras.class));
    }
}
