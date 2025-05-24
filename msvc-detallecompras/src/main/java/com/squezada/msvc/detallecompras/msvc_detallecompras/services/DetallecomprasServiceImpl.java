package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.clients.ProductoClientRest;
import com.squezada.msvc.detallecompras.msvc_detallecompras.exceptions.DetallecomprasException;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Detallecompras;
import com.squezada.msvc.detallecompras.msvc_detallecompras.repositories.DetallecomprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallecomprasServiceImpl  implements DetallecomprasService {


    @Autowired
    private DetallecomprasRepository detallecomprasRepository;
    private ProductoClientRest productoClientRest;


    @Override
    public List<Detallecompras> findAll() {
        return this.detallecomprasRepository.findAll();
    }

    @Override
    public Detallecompras findById(Long id) {
        return this.detallecomprasRepository.findById(id).orElseThrow(
                () -> new DetallecomprasException("El detalle de compras con id " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Detallecompras save(Detallecompras detallecompras) {
        return this.detallecomprasRepository.save(detallecompras);
    }

    @Override
    public List<Detallecompras> findByIdproducto(Long idProducto) {
        return this.detallecomprasRepository.findByIdProducto(idProducto);
    }
}

