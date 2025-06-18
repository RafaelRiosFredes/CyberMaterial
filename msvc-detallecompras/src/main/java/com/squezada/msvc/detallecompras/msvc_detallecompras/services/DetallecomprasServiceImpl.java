package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.*;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.*;
import com.squezada.msvc.detallecompras.msvc_detallecompras.exceptions.DetallecomprasException;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.*;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;
import com.squezada.msvc.detallecompras.msvc_detallecompras.repositories.DetallecomprasRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallecomprasServiceImpl  implements DetallecomprasService {

    @Autowired
    private DetallecomprasRepository detallecomprasRepository;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Autowired
    private BoletaClientRest boletaClientRest;

    @Autowired
    private InventarioClientRest inventarioClientRest;

    @Autowired
    private ClienteClientRest clienteClientRest;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Override
    public DetalledecomprasDTO findById(Long id) {
        Detallecompras detallecompras =  this.detallecomprasRepository.findById(id).orElseThrow(
                () -> new DetallecomprasException("El detalle de compra con id: " + id + " no se encuentra en la base de datos")
        );

        Producto producto = this.productoClientRest.findById(detallecompras.getIdProducto());
        Boleta boleta = this.boletaClientRest.findById(detallecompras.getIdBoleta());
        Cliente cliente = this.clienteClientRest.findById(boleta.getIdCliente());
        Sucursal sucursal = this.sucursalClientRest.findById(boleta.getIdSucursal());

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setIdProducto(producto.getIdProducto());
        productoDTO.setNombreProducto(producto.getNombreProducto());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setDescripcion(producto.getDescripcion());

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getIdCliente());
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

        DetalledecomprasDTO detalledecomprasDTO = new DetalledecomprasDTO();
        detalledecomprasDTO.setCantidad(detallecompras.getCantidad());
        detalledecomprasDTO.setTotal(detallecompras.getTotal());
        detalledecomprasDTO.setProductoDTO(productoDTO);
        detalledecomprasDTO.setBoletaDTO(boletaDTO);

        return detalledecomprasDTO;
    }


    @Override
    public Detallecompras save(Detallecompras detallecompras) {
        try {
            Producto producto = this.productoClientRest.findById(detallecompras.getIdProducto());
            Boleta boleta = this.boletaClientRest.findById(detallecompras.getIdBoleta());
            Long idSucursal = this.boletaClientRest.findById(detallecompras.getIdBoleta()).getIdSucursal();
            Long idProducto = this.productoClientRest.findById(detallecompras.getIdProducto()).getIdProducto();
            Inventario inventario = this.inventarioClientRest.findByIdSucursalAndIdProducto(idSucursal,idProducto);

            if(inventario.getStock() > 0){
                return this.detallecomprasRepository.save(detallecompras);
            }
        }catch (FeignException ex){
            throw new DetallecomprasException("Existen problemas con la asociación producto boleta");
        }
        return null;
    }

    @Override
    public List<Detallecompras> findByIdBoleta(Long id) { return this.detallecomprasRepository.findById();}

}