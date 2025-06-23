package com.rrios.msvc.boletas.msvc_boletas.services;

import com.rrios.msvc.boletas.msvc_boletas.clients.ClienteClientRest;
import com.rrios.msvc.boletas.msvc_boletas.clients.DetallecomprasClientRest;
import com.rrios.msvc.boletas.msvc_boletas.clients.SucursalClientRest;
import com.rrios.msvc.boletas.msvc_boletas.dtos.BoletaDTO;
import com.rrios.msvc.boletas.msvc_boletas.dtos.ClienteDTO;
import com.rrios.msvc.boletas.msvc_boletas.dtos.DetallecomprasDTO;
import com.rrios.msvc.boletas.msvc_boletas.dtos.SucursalDTO;
import com.rrios.msvc.boletas.msvc_boletas.exceptions.BoletaException;
import com.rrios.msvc.boletas.msvc_boletas.models.Cliente;
import com.rrios.msvc.boletas.msvc_boletas.models.Detallecompras;
import com.rrios.msvc.boletas.msvc_boletas.models.Sucursal;
import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;
import com.rrios.msvc.boletas.msvc_boletas.repositories.BoletaRepository;
import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BoletaServiceImpl implements BoletaService{
    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ClienteClientRest clienteClientRest;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Autowired
    private DetallecomprasClientRest detallecomprasClientRest;


    @Override
    public List<BoletaDTO> findAll() {
        return this.boletaRepository.findAll().stream().map(boleta -> {
            Cliente cliente = null;
            try {
                cliente = this.clienteClientRest.findById(boleta.getIdCliente());
            }catch (FeignException ex){
                throw new BoletaException("El cliente no existe en la base de datos");
            }
            Sucursal sucursal = null;
            try {
                sucursal = this.sucursalClientRest.findById(boleta.getIdSucursal());
            }catch (FeignException ex){
                throw new BoletaException("La sucursal no existe en la base de datos");
            }

            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setIdCliente(boleta.getIdCliente());

            SucursalDTO sucursalDTO = new SucursalDTO();
            sucursalDTO.setIdSucursal(boleta.getIdSucursal());

            BoletaDTO boletaDTO = new BoletaDTO();
            boletaDTO.setClienteDTO(clienteDTO);
            boletaDTO.setSucursalDTO(sucursalDTO);
            boletaDTO.setFechaBoleta(boleta.getFechaBoleta());
            boletaDTO.setEntregaPresencial(boleta.getEntregaPresencial());
            boletaDTO.setEstadoPago(boleta.getEstadoPago());

            return boletaDTO;
        }).toList();
    }

    @Override
    public BoletaDTO findDTOById(Long id) {
        BoletaDTO boleta = this.boletaRepository.findDTOByIdBoleta(id);

        List<DetallecomprasDTO> detallecomprasDTOList = detallecomprasClientRest.findByIdBoleta(id);

        boleta.setDetallesCompras(detallecomprasDTOList);

        return boleta;
    }



    @Override
    public Boleta save(BoletaDTO boleta) {
        try {
            ClienteDTO cliente = boleta.getClienteDTO();
        }catch (FeignException ex){
            throw new BoletaException("Existen problemas con el cliente");
        }
        try {
            SucursalDTO sucursal = boleta.getSucursalDTO();
        }catch (FeignException ex){
            throw new BoletaException("Existen problemas con la sucursal");
        }
        Boleta boletaNew = new Boleta();

        boletaNew.setIdCliente(boleta.getClienteDTO().getIdCliente());
        boletaNew.setIdSucursal(boleta.getSucursalDTO().getIdSucursal());
        boletaNew.setFechaBoleta(boleta.getFechaBoleta());
        boletaNew.setEntregaPresencial(boleta.getEntregaPresencial());
        boletaNew.setEstadoPago(boleta.getEstadoPago());

        return this.boletaRepository.save(boletaNew);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Elimina un producto por su Id.",
            description = "Elimina un producto de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Eliminación exitosa.")
    })
    public void deleteById(@Valid  @PathVariable Long id) {
        this.boletaRepository.deleteById(id);
    }


}
