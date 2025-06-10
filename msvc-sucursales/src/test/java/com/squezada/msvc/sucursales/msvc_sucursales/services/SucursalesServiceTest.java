package com.squezada.msvc.sucursales.msvc_sucursales.services;


import com.squezada.msvc.sucursales.msvc_sucursales.models.entities.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.repositories.SucursalRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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



}
