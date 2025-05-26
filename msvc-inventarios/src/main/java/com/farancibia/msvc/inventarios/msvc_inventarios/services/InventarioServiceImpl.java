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
public class InventarioServiceImpl implements InventarioService{

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Override
    public Inventario findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto) {
        List<Inventario> inventarioList = inventarioRepository.findByIdSucursalAndIdProducto(idSucursal, idProducto);

        if (!inventarioList.isEmpty()){
            return inventarioList.getFirst();
        }else{
            throw new  InventarioException("Inventario no encontrado");
        }
    }

    @Override
    public List<InventarioDTO> findAll() {
        return this.inventarioRepository.findAll().stream().map(inventario-> {
            Sucursal sucursal;
            Producto producto;

            try {
                sucursal = this.sucursalClientRest.findById(inventario.getIdSucursal());
            } catch (FeignException ex) {
                throw new InventarioException("La sucursal con ID" + inventario.getIdSucursal() + "no existe");
            }

            try {
                producto = this.productoClientRest.findById(inventario.getIdProducto());
            } catch (FeignException ex) {
                throw new InventarioException("El producto con ID" + inventario.getIdProducto() + "no existe");
            }

            SucursalDTO sucursalDTO = new SucursalDTO();
            sucursalDTO.setIdSucursal(inventario.getIdSucursal());

            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdProducto(inventario.getIdProducto());

            InventarioDTO inventarioDTO = new InventarioDTO();
            inventarioDTO.setStock(inventario.getStock());
            inventarioDTO.setSucursal(sucursalDTO);
            inventarioDTO.setProducto(productoDTO);


            return inventarioDTO;
        }).toList();
    }

    @Override
    public Inventario save(Inventario inventario) {
        try {
            Producto producto = this.productoClientRest.findById(inventario.getIdProducto());
            Sucursal sucursal = this.sucursalClientRest.findById(inventario.getIdSucursal());
        }catch (FeignException ex){
            throw new InventarioException("Existe problemas con la asociacion sucursal y producto");
        }
        return this.inventarioRepository.save(inventario);
    }
}
