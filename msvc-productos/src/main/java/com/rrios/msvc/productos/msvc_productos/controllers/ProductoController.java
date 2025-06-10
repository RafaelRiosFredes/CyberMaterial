package com.rrios.msvc.productos.msvc_productos.controllers;

import com.rrios.msvc.productos.msvc_productos.dtos.ProductoDTO;
import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;
import com.rrios.msvc.productos.msvc_productos.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Validated
@Tag(name = "Productos",description = "Operaciones CRUD")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(
            summary = "Obtiene todos los productos",
            description = "Devuelve un List de Productos en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Completacion exitosa"),
            @ApiResponse(responseCode = "404",description = "Producto no encontrado")
    })
    @Parameters(value = {
            @Parameter(name = "Id",description = "Este es el id unico del producto",required = true)
    })
    public ResponseEntity<List<Producto>> findAll(){
        List<Producto> productos = this.productoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        Producto producto = this.productoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> save(@Valid @RequestBody ProductoDTO productoDTO){
        Producto saved = this.productoService.save(productoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}



















