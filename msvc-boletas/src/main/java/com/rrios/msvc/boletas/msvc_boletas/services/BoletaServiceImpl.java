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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public BoletaDTO findById(Long id) {
        Boleta boleta = this.boletaRepository.findById(id).orElseThrow(
                () -> new BoletaException("La boleta con id: " + id + " no se encuentra en la base de datos")
        );

        Detallecompras detallecompras = this.detallecomprasClientRest.getDetallecompras(id);


        BoletaDTO boletaDTO = new BoletaDTO();
    }



    @Override
    public Boleta save(Boleta boleta) {
        try {
            Cliente cliente = this.clienteClientRest.findById(boleta.getIdCliente());
        }catch (FeignException ex){
            throw new BoletaException("Existen problemas con el cliente");
        }
        try {

            Sucursal sucursal = this.sucursalClientRest.findById(boleta.getIdSucursal());
        }catch (FeignException ex){
            throw new BoletaException("Existen problemas con la sucursal");
        }

        return this.boletaRepository.save(boleta);
    }


}
