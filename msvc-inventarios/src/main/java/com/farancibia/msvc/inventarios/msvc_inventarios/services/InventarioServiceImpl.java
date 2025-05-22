package com.farancibia.msvc.inventarios.msvc_inventarios.services;

import com.farancibia.msvc.inventarios.msvc_inventarios.clients.ProductoClientRest;
import com.farancibia.msvc.inventarios.msvc_inventarios.clients.SucursalClientRest;
import com.farancibia.msvc.inventarios.msvc_inventarios.exceptions.InventarioExceptions;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Producto;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Sucursal;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import com.farancibia.msvc.inventarios.msvc_inventarios.repositories.InventarioRepository;
import feign.FeignException;

public class InventarioServiceImpl implements InventarioService{
    private InventarioRepository inventarioRepository;
    private SucursalClientRest sucursalClientRest;
    private ProductoClientRest productoClientRest;
    @Override
    public Inventario findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto) {
            return findByIdSucursalAndIdProducto(idSucursal, idProducto);
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
