package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.ProductoClientRest;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.ProductoDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.exceptions.DetallecomprasException;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Producto;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompra;
import com.squezada.msvc.detallecompras.msvc_detallecompras.repositories.DetallecomprasRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class DetallecomprasServicelmpl {

      @Service
      public abstract class DetallecomprasServiceImpl implements DetallecomprasService {

          @Autowired
          private DetallecomprasRepository detallecomprasRepository;

          @Autowired
          private ProductoClientRest productoClientRest;





          // Guardar un nuevo DetalleCompra en la base de datos

          @Override
          public List<DetalledecomprasDTO> findAll() {
              return this.detallecomprasRepository.findAll().stream().map(Detallecompra -> {

                  Producto producto = null;
                  try {
                      producto = this.productoClientRest.findById(detallecompras.getIdProducto());

                  } catch (FeignException ex) {
                      throw new DetallecomprasException("El producto no existe");
                  }

                  ProductoDTO productoDTO = new ProductoDTO();
                  productoDTO.setIdProducto(producto.getIdproducto());
                  productoDTO.setNombreProducto(producto.getNombre());
                  productoDTO.setPrecio(producto.getPrecio());
                  productoDTO.setDescripcion(producto.getDescripcion());

                  DetalledecomprasDTO detalledecomprasDTO = new DetalledecomprasDTO();
                  DetalledecomprasDTO.setProducto(productoDTO);
                  return detalledecomprasDTO;


              }).toList();
          }


          // Busca un detalle de compra por su ID (findById)
          @Override
          public Detallecompra findById(Long id) {
              return this.detallecomprasRepository.findById(id).orElseThrow(
                      () -> new DetallecomprasException("El detalle de compra con id: " + id + " no se encuentra en la base de datos")
              );

          }

          @Override
          public Detallecompra save(Detallecompra detallecompra) {
              try {
                  Producto producto = this.productoClientRest.findById(detallecompra.getIdproducto);

              } catch (FeignException ex) {
                  throw new DetallecomprasException("Existen problemas con la asoción de producto detalle de compras");
              }
              return this.detallecomprasRepository.save(detallecompra);
          }

          @Override
          public List<Detallecompra> findByProductoId(Long productoId) {
              return this.detallecomprasRepository.findByidProducto(productoId);
          }

      }
  }