package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.ProductoClientRest;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Producto;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;
import com.squezada.msvc.detallecompras.msvc_detallecompras.repositories.DetallecomprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class DetallecomprasServicelmpl {

        @Service
        public abstract class DetallecomprasServicel implements DetallecomprasService {

        @Autowired
        private DetallecomprasRepository detallecomprasRepository;

        @Autowired
        private ProductoClientRest productoClientRest;

        // Guardar un nuevo DetalleCompra en la base de datos

        @Override
        public Detallecompras crear(Detallecompras detalleCompra) {

            // Busca el producto por ID para asegurarse de que existe (findById)

            Long idProducto = detalleCompra.getProducto().getIdProducto();
            Producto producto = productoRepository.findById(idProducto)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Asigna el producto encontrado al detalle

            detalleCompra.setProducto(producto);

            // Guarda el detalle de compra (save)
            return detallecomprasRepository.save(detalleCompra);
        }

        // Busca un detalle de compra por su ID (findById)
        @Override
        public Detallecompras obtenerPorId(Long id) {
            return detallecomprasRepository.findById(id).orElse(null);
        }

        // Retorna todos los detalles de compra existentes (findAll)
        @Override
        public List<Detallecompras> obtenerTodos() {
            return detallecomprasRepository.findAll();
        }


    }

}
