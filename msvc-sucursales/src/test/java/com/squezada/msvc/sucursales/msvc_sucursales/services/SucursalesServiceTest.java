package com.squezada.msvc.sucursales.msvc_sucursales.services;


import com.squezada.msvc.sucursales.msvc_sucursales.exceptions.SucursalException;
import com.squezada.msvc.sucursales.msvc_sucursales.models.entities.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.repositories.SucursalRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SucursalesServiceTest {

    @Mock
    private SucursalRepository sucursalRepository;


    @InjectMocks
    private SucursalServicelmpl sucursalService;

    private Sucursal sucursalPrueba;


    private List<Sucursal> sucursales = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.sucursalPrueba = new Sucursal(
                "9:00-19:00","Anibal Pinto 259, Santiago"
        );
    }

    @Test
    @DisplayName("Debe buscar una sucursal")
    public void shouldFindById(){
        when(sucursalRepository.findById(Long.valueOf(1l))).thenReturn(Optional.of(sucursalPrueba));

        Sucursal result = sucursalService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);
        verify(sucursalRepository, times(1)).findById(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Debe buscar una sucursal con id que no existe")
    public void shouldNotFindSucursalId(){
        Long idInexistente = 999L;
        when(sucursalRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            sucursalService.findById(idInexistente);

        }).isInstanceOf(SucursalException.class).hasMessageNotContaining("La sucursal con id"+ idInexistente+ "no se encuentra en la base de datos");
        verify(sucursalRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar una nueva sucursal")
    public void ShouldSaveProducto(){
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalPrueba);
        Sucursal result = sucursalService.save(sucursalPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);

        verify(sucursalRepository, times(1)).save(any(Sucursal.class));
    }





}
