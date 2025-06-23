package com.farancibia.msvc.inventarios.msvc_inventarios.services;

import com.farancibia.msvc.inventarios.msvc_inventarios.clients.ProductoClientRest;
import com.farancibia.msvc.inventarios.msvc_inventarios.clients.SucursalClientRest;
import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.InventarioDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.ProductoDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.SucursalDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.exceptions.InventarioException;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Producto;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Sucursal;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import com.farancibia.msvc.inventarios.msvc_inventarios.repositories.InventarioRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Override
    public Inventario findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto) {
        List<Inventario> inventarioList = inventarioRepository.findByIdSucursalAndIdProducto(idSucursal, idProducto);
        return inventarioList.stream().findFirst()
                .orElseThrow(() -> new InventarioException("Inventario no encontrado"));
    }

    @Override
    public List<InventarioDTO> findAll() {
        return this.inventarioRepository.findAll().stream().map(inventario -> {
            InventarioDTO dto = new InventarioDTO();
            dto.setStock(inventario.getStock());

            SucursalDTO sucursalDTO = new SucursalDTO();
            sucursalDTO.setIdSucursal(inventario.getIdSucursal());
            try {
                Sucursal sucursal = this.sucursalClientRest.findById(inventario.getIdSucursal());
                if(sucursal != null) {
                    sucursalDTO.setNombreSucursal(sucursal.getDireccion());
                }
            } catch (FeignException ex) {
                System.err.println("Error obteniendo sucursal: " + ex.getMessage());
            }
            dto.setSucursal(sucursalDTO);

            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdProducto(inventario.getIdProducto());
            try {
                Producto producto = this.productoClientRest.findById(inventario.getIdProducto());
                if(producto != null) {
                    productoDTO.setNombreProducto(producto.getNombreProducto());

                }
            } catch (FeignException ex) {
                System.err.println("Error obteniendo producto: " + ex.getMessage());
            }
            dto.setProducto(productoDTO);

            return dto;
        }).toList();
    }

    @Override
    public Inventario save(Inventario inventario) {
        boolean relacionesExisten = true;
        try {
            Producto producto = this.productoClientRest.findById(inventario.getIdProducto());
            Sucursal sucursal = this.sucursalClientRest.findById(inventario.getIdSucursal());
            if(producto == null || sucursal == null) {
                relacionesExisten = false;
            }
        } catch (FeignException ex) {
            relacionesExisten = false;
        }

        if(!relacionesExisten) {
            throw new InventarioException("El producto o sucursal asociados no existen");
        }

        return this.inventarioRepository.save(inventario);
    }
}
