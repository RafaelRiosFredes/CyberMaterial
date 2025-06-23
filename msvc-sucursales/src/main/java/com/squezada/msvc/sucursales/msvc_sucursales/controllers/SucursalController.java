package com.squezada.msvc.sucursales.msvc_sucursales.controllers;


import com.squezada.msvc.sucursales.msvc_sucursales.dtos.ErrorDTO;
import com.squezada.msvc.sucursales.msvc_sucursales.models.entities.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.services.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sucursales")
@Validated
@Tag(name= "Sucursales", description = "Operaciones CRUD")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    @Operation(
            summary = "Obtiene todas las sucursales",
            description = "Devuelve un List de Sucursales en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Completacion exitosa")})
    public ResponseEntity<List<Sucursal>> findAll(){
        List<Sucursal> sucursales = this.sucursalService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(this.sucursalService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtiene una sucursal por su id",
            description = "Devuelve una sucursal por el body")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Completacion exitosa"),
              @ApiResponse(
                      responseCode = "404",
                      description = "Sucursal no encontrada con el id administrador",
                      content = @Content(
                              mediaType = "application/json",
                              schema = @Schema(implementation = ErrorDTO.class)))})
    @Parameters(value = {
            @Parameter(name = "Id", description = "Este es eñ id unico de la sucursal", required = true)})
    public ResponseEntity<Sucursal> findById(@PathVariable Long id){
        Sucursal sucursal = this.sucursalService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(this.sucursalService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Guarda una sucursal",
            description = "Con este metodo podemos enviar datos a traves de un body y crear una sucursal"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Guardado exitoso"),
            @ApiResponse(
                    responseCode = "409",
                    description = "La sucursal ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Error",
                                    value = "{\"codigo\": \"statusCode\",\"date\":\"fecha\"}"
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Sucursal a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Sucursal.class)
            )
    )
    public ResponseEntity<?> save(@Valid @RequestBody Sucursal sucursal, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream()
                    .map(err -> "Campo '" + err.getField() + "': " + err.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.sucursalService.save(sucursal));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.sucursalService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
