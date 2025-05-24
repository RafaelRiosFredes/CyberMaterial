package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.ProductoClientRest;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.ProductoDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.exceptions.DetallecomprasException;

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

    @Override
    public Detallecompras findByIdproducto( Long idProducto) {
        return findByIdproducto( idProducto);
    }

    @Override
    public List<DetalledecomprasDTO> findAll() {
        return this.detallecomprasRepository.findAll().stream().map(Detallecompras -> {

            Producto producto = null;
            try {
                producto = this.productoClientRest.findById(producto.getIdProducto());

                ProductoDTO productoDTO = new ProductoDTO();
                productoDTO.setIdProducto(producto.getIdProducto());

                DetalledecomprasDTO detalledecomprasDTO = new DetalledecomprasDTO();
                detalledecomprasDTO.setProducto(productoDTO);

                return detalledecomprasDTO;
            } catch (FeignException ex) {
                throw new DetallecomprasException("el producto buscado no existe");
            }




        }).toList();
    }





    @Override
    public Detallecompras save(Detallecompras detallecompras) {
        return this.detallecomprasRepository.save(detallecompras);
    }



}

