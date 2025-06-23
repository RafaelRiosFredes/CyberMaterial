package com.rrios.msvc.boletas.msvc_boletas.controllers;

import com.rrios.msvc.boletas.msvc_boletas.dtos.BoletaDTO;
import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;
import com.rrios.msvc.boletas.msvc_boletas.services.BoletaService;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/boletas")
@Validated
@Tag(name = "Boletas",description = "Operaciones CRUD de Boletas")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    @Operation(
            summary = "Obtiene todos las boletas",
            description = "Devuelve un List de boletas en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoletaDTO.class)
                    )
            ),
    })
    public ResponseEntity<List<BoletaDTO>> findAllDTOs(){
        return ResponseEntity.status(HttpStatus.OK).body(this.boletaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtiene una boleta por su id",
            description = "Devuelve una boleta en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operación exitosa."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Boleta no encontrada con el id administrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "Id",
            description = "Este es el id unico de la boleta",required = true)})
    public ResponseEntity<BoletaDTO> findDTOById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.boletaService.findDTOById(id));
    }

    @PostMapping
    @Operation(
            summary = "Guarda una boleta",
            description = "Con este método podemos enviar datos a través de un body y crear un producto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Guardado exitoso."),
            @ApiResponse(
                    responseCode = "409",
                    description = "La boleta ya se encuentra en la base de datos.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation =  ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Boleta a crear.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Boleta.class)
            )
    )
    public ResponseEntity<Boleta> save(@Valid @RequestBody BoletaDTO boleta){
        return ResponseEntity.status((HttpStatus.CREATED)).body(this.boletaService.save(boleta));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Elimina una boleta por su Id.",
            description = "Elimina una boleta de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Eliminación exitosa.")
    })
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Long id){
        this.boletaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
