package com.squezada.msvc.detallecompras.msvc_detallecompras.controllers;


import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;

import com.squezada.msvc.detallecompras.msvc_detallecompras.services.DetallecomprasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detallecompras")
@Validated
public class DetallecomprasController {

    @Autowired
    private DetallecomprasService detallecomprasService;

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<Detallecompras> getDetallecompras(@PathVariable Long idProducto){
        return ResponseEntity.status(HttpStatus.OK).body(this.detallecomprasService.findByIdproducto(idProducto));
    }

    @GetMapping
    public ResponseEntity<List<DetalledecomprasDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.detallecomprasService.findAll());
    }

    @PostMapping
    public ResponseEntity<Detallecompras> save(@RequestBody Detallecompras detallecompras){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.detallecomprasService.save(detallecompras));
    }
}
