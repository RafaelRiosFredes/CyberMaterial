package com.squezada.msvc.detallecompras.msvc_detallecompras.controllers;


import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.ErrorDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;

import com.squezada.msvc.detallecompras.msvc_detallecompras.services.DetallecomprasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detallecompras")
@Validated
@Tag(name = "Detallecompras", description = "Operaciones CRUD")
public class DetallecomprasController {

    @Autowired
    private DetallecomprasService detallecomprasService;



    @GetMapping
    @Operation(
            summary = "Obtiene todos los detalle de compras de una boleta",
            description = "Devuelve un List de detalle de compras de una boleta en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Completacion exitosa")})
    @Parameters(value = {
            @Parameter(name = "Id", description = "Este es el id unico de detalle de compras", required = true)})
    public ResponseEntity<List<DetalledecomprasDTO>> findByIdBoleta(@Valid @PathVariable Long id){
        List<DetalledecomprasDTO> detallecompras = this.detallecomprasService.findByIdBoleta(id);
        return ResponseEntity.status(HttpStatus.OK).body(detallecompras);
    }

    @GetMapping("/{id}")
    @Operation(
    summary = "Obtiene un detalle de compras por su id",
    description = "Devuelve un Detalle de compras en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Completacion exitosa"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Detalle de compras no encontrado con el id administrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))})
    @Parameters(value = {
            @Parameter(name = "Id",description = "Este es el id unico del detalle de compras",required = true)})
    public ResponseEntity<DetalledecomprasDTO> getDetallecompras(@Valid @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.detallecomprasService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Guarda un detalle de compras",
            description = "Con este metodo podemos enviar datos a traves de un body y crear un detalle de compras"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Guardado exitoso"),
            @ApiResponse(
                    responseCode = "409",
                    description = "El detalle de compras ya se encuentra en la base de datos",
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
            description = "Detalle de compras a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Detallecompras.class)
            )
    )
    public ResponseEntity<Detallecompras> save(@Valid @RequestBody Detallecompras detallecompras){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.detallecomprasService.save(detallecompras));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.detallecomprasService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
