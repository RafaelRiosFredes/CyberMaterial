package com.farancibia.msvc.inventarios.msvc_inventarios.services;

import com.farancibia.msvc.inventarios.msvc_inventarios.clients.ProductoClientRest;
import com.farancibia.msvc.inventarios.msvc_inventarios.clients.SucursalClientRest;
import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.InventarioDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.ProductoDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.SucursalDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.exceptions.InventarioExceptions;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Producto;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Sucursal;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import com.farancibia.msvc.inventarios.msvc_inventarios.repositories.InventarioRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService{

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Override
    public Inventario findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto) {
            return findByIdSucursalAndIdProducto(idSucursal, idProducto);
    }

    @Override
    public List<InventarioDTO> findAll() {
        return this.inventarioRepository.findAll().stream().map(inventario-> {

            Sucursal sucursal = null;
            try {
                sucursal = this.sucursalClientRest.findById(sucursal.getIdSucursal());
            } catch (FeignException ex) {
                throw new InventarioExceptions("La sucursal buscada no existe");
            }

            Producto producto = null;
            try {
                producto = this.productoClientRest.findById(producto.getIdProducto());
            } catch (FeignException ex) {
                throw new InventarioExceptions("el producto buscado no existe");
            }

            SucursalDTO sucursalDTO = new SucursalDTO();
            sucursalDTO.setIdSucursal(sucursal.getIdSucursal());

            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdProducto(producto.getIdProducto());

            InventarioDTO inventarioDTO = new InventarioDTO();
            inventarioDTO.setStock(inventarioDTO.getStock());

            return inventarioDTO;
        }).toList();
    }

    @Override
    public Inventario save(Inventario inventario) {
        try {
            Producto producto = this.productoClientRest.findById(inventario.getIdProducto());
            Sucursal sucursal = this.sucursalClientRest.findById(inventario.getIdSucursal());
        }catch (FeignException ex){
            throw new InventarioExceptions("Existe problemas con la asociacion sucursal y producto");
        }
        return this.inventarioRepository.save(inventario);
    }
}
