package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.ProductoClientRest;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.ProductoDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.exceptions.DetallecomprasException;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Detallecompras;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Producto;
import com.squezada.msvc.detallecompras.msvc_detallecompras.repositories.DetallecomprasRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public abstract class DetallecomprasServiceImpl implements DetallecomprasService {

    @Autowired
    private DetallecomprasRepository detallecomprasRepository;

    @Autowired
    private ProductoClientRest productoClientRest;


    // Guardar un nuevo DetalleCompra en la base de datos

    @Override
    public List<Detallecompras> findAll() {
        return this.detallecomprasRepository.findAll().stream().map(Detallecompras -> {

            Producto producto = null;
            try {
                producto = this.productoClientRest.findById(detallecompras.getIdProducto());
                if (producto == null) {
                    throw new DetallecomprasException("Producto no encontrado");
                }
            } catch (FeignException ex) {
                throw new DetallecomprasException("El producto no existe");
            }

            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdProducto(producto.getIdproducto());
            productoDTO.setNombreProducto(producto.getNombreProducto());
            productoDTO.setPrecio(producto.getPrecio());
            productoDTO.setDescripcion(producto.getDescripcion());

            DetalledecomprasDTO detalledecomprasDTO = new DetalledecomprasDTO();
            detalledecomprasDTO.setProducto(productoDTO);
            return detalledecomprasDTO;


        }).toList();
    }

    @Override
    public Detallecompras findById(Long id) {
        return this.detallecomprasRepository.findById(id).orElseThrow(
                () -> new DetallecomprasException("El detalle de compra con id: " + id + " no se encuentra en la base de datos")
        );

    }

    @Override
    public Detallecompras save(Detallecompras detallecompras) {
        try {
            Producto producto = this.productoClientRest.findById(detallecompras.getIdproducto());

        } catch (FeignException ex) {
            throw new DetallecomprasException("Existen problemas con la asoción de producto detalle de compras");
        }
        return this.detallecomprasRepository.save(detallecompras);
    }

    @Override
    public List<Detallecompras> findByProductoId(Long productoId) {
        return this.detallecomprasRepository.findByIdProducto(productoId);
    }

}