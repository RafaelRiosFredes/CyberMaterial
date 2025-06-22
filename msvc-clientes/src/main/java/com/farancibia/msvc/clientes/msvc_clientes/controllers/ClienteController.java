package com.farancibia.msvc.clientes.msvc_clientes.controllers;


import com.farancibia.msvc.clientes.msvc_clientes.dtos.ClienteDTO;
import com.farancibia.msvc.clientes.msvc_clientes.dtos.ErrorDTO;
import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import com.farancibia.msvc.clientes.msvc_clientes.services.ClienteService;
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

@RestController
@RequestMapping("/api/v1/clientes")
@Validated
@Tag(name = "Clientes", description = "operaciones CRUD de Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping
    @Operation(summary = "obtiene todos los clientes", description = "Devuelve un List de Clientes en el Body")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion existosa",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)
                    )
            )
    })

    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.clienteService.findAll());
    }

    @GetMapping("/{id}")
    @Operation (summary = "Obtiene un Cliente", description = "A través de ID suministrado devuleve el cliente con esa id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )

    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico del cliente", required = true)
    })
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.clienteService.findById(id));
    }


    @PostMapping
    @Operation(
            summary = "Guarda un cliente",
            description = "Con este método podemos enviar los datos mediante un body y relaizar el guardado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Guardado exitoso"),
            @ApiResponse(
                    responseCode = "409",
                    description = "El cliente guardado ya se encuntra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.Parameter.RequestBody(
            description = "Cliente a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class)
            )
    )
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente saved = this.clienteService.save(clienteDTO);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.clienteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
