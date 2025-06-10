package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.BoletaClientRest;
import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.InventarioClientRest;
import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.ProductoClientRest;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.BoletaDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.ProductoDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.exceptions.DetallecomprasException;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Boleta;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Inventario;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Producto;
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

    @Override
    public Detallecompras findById(Long id) {
        return this.detallecomprasRepository.findById(id).orElseThrow(
                () -> new DetallecomprasException("El detalle de compra con id: " + id + " no se encuentra en la base de datos")
        );
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
    public List<Detallecompras> findByIdBoleta(Long id) {
         this.detallecomprasRepository.findById()
    }


}

