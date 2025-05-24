package com.rrios.msvc.boletas.msvc_boletas.services;

import com.rrios.msvc.boletas.msvc_boletas.clients.ClienteClientRest;
import com.rrios.msvc.boletas.msvc_boletas.clients.DetallecomprasClientRest;
import com.rrios.msvc.boletas.msvc_boletas.clients.SucursalClientRest;
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
    private DetallecomprasClientRest detallecomprasClientRest;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Override
    public List<Boleta> findAll() {
        return this.boletaRepository.findAll().stream().map(boleta -> {
            Cliente cliente = null;
            try {
                cliente = this.clienteClientRest.findById(boleta.getIdCliente());
            }catch (FeignException ex){
                throw new BoletaException("La boleta no existe en la base de datos");
            }
            Sucursal sucursal = null;
            try {
                sucursal = this.sucursalClientRest.findById(boleta.getIdSucursal());
            }catch (FeignException ex){
                throw new BoletaException("La sucursal no existe en la base de datos");
            }

            Detallecompras detallecompras = null;
            try {
                detallecompras = this.detallecomprasClientRest.findById(boleta.getIdDetalleCompra());
            }catch (FeignException ex){
                throw new BoletaException("El detalle de compra no existe en la base de datos");
            }
            return boleta;
        }).toList();
    }

    @Override
    public Boleta findById(Long id) {
        return this.boletaRepository.findById(id).orElseThrow(
                () -> new BoletaException("La boleta con id: " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Boleta save(Boleta boleta) {
        try {
            Cliente cliente = this.clienteClientRest.findById(boleta.getIdCliente());
            Sucursal sucursal = this.sucursalClientRest.findById(boleta.getIdSucursal());
        }catch (FeignException ex){
            throw new BoletaException("Existen problemas con la asociación");
        }
        return this.boletaRepository.save(boleta);
    }

}
