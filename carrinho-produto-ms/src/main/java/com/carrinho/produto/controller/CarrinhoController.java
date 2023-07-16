package com.carrinho.produto.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrinho.produto.dtos.CarrinhoDTO;
import com.carrinho.produto.services.CarrinhoService;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<List<CarrinhoDTO>> getAllCarrinhos() {
        List<CarrinhoDTO> carrinhos = carrinhoService.findAll();
        return ResponseEntity.ok(carrinhos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoDTO> getCarrinhoById(@PathVariable Long id) {
        CarrinhoDTO carrinho = carrinhoService.findById(id);
        return ResponseEntity.ok(carrinho);
    }

    @PostMapping
    public ResponseEntity<CarrinhoDTO> createCarrinho(@RequestBody @Valid CarrinhoDTO carrinhoDto) {
        CarrinhoDTO createdCarrinhoDTO = carrinhoService.createCarrinho(carrinhoDto);
        return ResponseEntity.created(URI.create("/carrinho/" + createdCarrinhoDTO.getId()))
                .body(createdCarrinhoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrinhoDTO> updateCarrinho(@PathVariable Long id, @RequestBody @Valid CarrinhoDTO carrinhoDto) {
        CarrinhoDTO updatedCarrinho = carrinhoService.update(id, carrinhoDto);
        return ResponseEntity.ok(updatedCarrinho);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrinho(@PathVariable Long id) {
        carrinhoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
	
}
