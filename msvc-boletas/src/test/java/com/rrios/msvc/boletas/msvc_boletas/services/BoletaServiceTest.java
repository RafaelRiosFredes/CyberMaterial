package com.rrios.msvc.boletas.msvc_boletas.services;

import com.rrios.msvc.boletas.msvc_boletas.clients.ClienteClientRest;
import com.rrios.msvc.boletas.msvc_boletas.clients.SucursalClientRest;
import com.rrios.msvc.boletas.msvc_boletas.models.Cliente;
import com.rrios.msvc.boletas.msvc_boletas.models.Sucursal;
import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;
import com.rrios.msvc.boletas.msvc_boletas.repositories.BoletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private Sucursal sucursalTest;
    private Boleta boletaTest;

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
    }

}
