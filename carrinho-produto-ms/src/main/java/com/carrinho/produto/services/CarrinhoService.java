package com.carrinho.produto.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrinho.produto.dtos.CarrinhoDTO;
import com.carrinho.produto.entities.Carrinho;
import com.carrinho.produto.repositories.CarrinhoRepository;

@Service
@Transactional
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ModelMapper mapper;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository, ModelMapper mapper) {
        this.carrinhoRepository = carrinhoRepository;
        this.mapper = mapper;
    }

    public List<CarrinhoDTO> findAll() {
        List<Carrinho> carrinhos = carrinhoRepository.findAll();
        return carrinhos.stream()
                .map(carrinho -> mapper.map(carrinho, CarrinhoDTO.class))
                .collect(Collectors.toList());
    }

    public CarrinhoDTO findById(Long id) {
        Carrinho carrinho = carrinhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrinho not found"));
        return mapper.map(carrinho, CarrinhoDTO.class);
    }

    public CarrinhoDTO save(CarrinhoDTO carrinhoDto) {
        Carrinho carrinho = mapper.map(carrinhoDto, Carrinho.class);
        Carrinho savedCarrinho = carrinhoRepository.save(carrinho);
        return mapper.map(savedCarrinho, CarrinhoDTO.class);
    }

    public CarrinhoDTO update(Long id, CarrinhoDTO carrinhoDto) {
        Carrinho carrinho = mapper.map(carrinhoDto, Carrinho.class);
        carrinho.setId(id);
        Carrinho updatedCarrinho = carrinhoRepository.save(carrinho);
        return mapper.map(updatedCarrinho, CarrinhoDTO.class);
    }

    public void deleteById(Long id) {
        carrinhoRepository.deleteById(id);
    }
	
}
