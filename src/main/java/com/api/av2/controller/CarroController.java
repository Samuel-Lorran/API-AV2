package com.api.av2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.av2.model.Carro;
import com.api.av2.service.CarroService;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    // Endpoint para criar um novo carro - somente admin
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Carro> createCarro(@RequestBody Carro carro) {
        Carro novoCarro = carroService.createCarro(carro);
        return ResponseEntity.ok(novoCarro);
    }
    
 // Endpoint para listar carros com filtros opcionais
    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Carro> listarCarro(@RequestParam(required = false) String cidade,
                                    @RequestParam(required = false) String marca) {
        return carroService.listarCarro(cidade, marca);
    }

    // Endpoint para atualizar um carro existente - somente admin
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Carro> updateCarro(@PathVariable Long id, @RequestBody Carro carro) {
        Carro carroAtualizado = carroService.updateCarro(id, carro);
        return ResponseEntity.ok(carroAtualizado);
    }

    // Endpoint para excluir um carro - somente admin
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCarro(@PathVariable Long id) {
        carroService.deleteCarro(id);
        return ResponseEntity.noContent().build();
    }

}
