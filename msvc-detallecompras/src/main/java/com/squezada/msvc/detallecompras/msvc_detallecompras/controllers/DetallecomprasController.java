package com.squezada.msvc.detallecompras.msvc_detallecompras.controllers;


import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Detallecompras;

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

    @GetMapping
    public ResponseEntity<List<Detallecompras>> findAll(){
        List<Detallecompras> detallecompras = this.detallecomprasService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(detallecompras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detallecompras> findById(@PathVariable Long id){
        Detallecompras detallecompra = this.detallecomprasService.findById(id);
        if(detallecompra == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(detallecompra);

    }


    @PostMapping
    public ResponseEntity<Detallecompras> save(@Valid @RequestBody Detallecompras detallecompra){
        Detallecompras saved = this.detallecomprasService.save(detallecompra);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    // Estos metodos nos permitiran mostrar los detalles de compras filtradas por id producto

    @GetMapping("/producto/{id}")
    public ResponseEntity<List<Detallecompras>> findByidproducto(@PathVariable Long id){
      return ResponseEntity.status(HttpStatus.OK).body(this.detallecomprasService.findByIdproducto(id));
    }
}
