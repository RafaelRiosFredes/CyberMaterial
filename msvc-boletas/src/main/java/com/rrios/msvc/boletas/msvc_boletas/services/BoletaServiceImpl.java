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

            clienteDTO.setRun(cliente.getRun());
            clienteDTO.setNombres(cliente.getNombres());
            clienteDTO.setApellidos(cliente.getApellidos());
            clienteDTO.setTelefono(cliente.getTelefono());
            clienteDTO.setCorreo(cliente.getCorreo());
            clienteDTO.setDireccion(cliente.getDireccion());

            SucursalDTO sucursalDTO = new SucursalDTO();

            sucursalDTO.setIdSucursal(sucursal.getIdSucursal());
            sucursalDTO.setHorario(sucursal.getHorario());
            sucursalDTO.setDireccion(sucursal.getDireccion());

            BoletaDTO boletaDTO = new BoletaDTO();
            boletaDTO.setIdBoleta(boleta.getIdBoleta());
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

        Optional<Boleta> boleta = this.boletaRepository.findById(id);

        Cliente cliente = clienteClientRest.findById(boleta.get().getIdCliente());

        Sucursal sucursal = sucursalClientRest.findById(boleta.get().getIdSucursal());

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setRun(cliente.getRun());
        clienteDTO.setNombres(cliente.getNombres());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setCorreo(cliente.getCorreo());
        clienteDTO.setDireccion(cliente.getDireccion());

        SucursalDTO sucursalDTO = new SucursalDTO();

        sucursalDTO.setIdSucursal(sucursal.getIdSucursal());
        sucursalDTO.setHorario(sucursal.getHorario());
        sucursalDTO.setDireccion(sucursal.getDireccion());

        List<DetallecomprasDTO> detallecomprasDTOList = detallecomprasClientRest.findByIdBoleta(id);

        BoletaDTO boletaDTO = new BoletaDTO();

        boletaDTO.setIdBoleta(boleta.get().getIdBoleta());
        boletaDTO.setClienteDTO(clienteDTO);
        boletaDTO.setSucursalDTO(sucursalDTO);
        boletaDTO.setFechaBoleta(boleta.get().getFechaBoleta());
        boletaDTO.setEntregaPresencial(boleta.get().getEntregaPresencial());
        boletaDTO.setEstadoPago(boleta.get().getEstadoPago());
        boletaDTO.setDetallesCompras(detallecomprasDTOList);

        return boletaDTO;
    }



    @Override
    public Boleta save(Boleta boleta) {
        try {
            Long idCliente = boleta.getIdCliente();
        }catch (FeignException ex){
            throw new BoletaException("Existen problemas con el cliente");
        }
        try {
            Long idSucursal = boleta.getIdSucursal();
        }catch (FeignException ex){
            throw new BoletaException("Existen problemas con la sucursal");
        }

        boleta.setFechaBoleta(boleta.getFechaBoleta());
        boleta.setEntregaPresencial(boleta.getEntregaPresencial());
        boleta.setEstadoPago(boleta.getEstadoPago());

        return this.boletaRepository.save(boleta);
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
