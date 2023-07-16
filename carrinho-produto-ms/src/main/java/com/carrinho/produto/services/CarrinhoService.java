package com.carrinho.produto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrinho.produto.dtos.CarrinhoDTO;
import com.carrinho.produto.dtos.ProdutoDTO;
import com.carrinho.produto.entities.Carrinho;
import com.carrinho.produto.entities.Produto;
import com.carrinho.produto.repositories.CarrinhoRepository;
import com.carrinho.produto.repositories.ProdutoRepository;

@Service
@Transactional
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ModelMapper mapper;
    
    @Autowired
    private ProdutoRepository produtoRepository;

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

        carrinho = carrinhoRepository.save(carrinho);

        List<Produto> produtos = new ArrayList<>();
        for (ProdutoDTO produtoDto : carrinhoDto.getProdutos()) {
            Produto produto = new Produto(produtoDto.getNome(), produtoDto.getQtde(), produtoDto.getDescricao(), carrinho);
            produtos.add(produto);
        }
        produtoRepository.saveAll(produtos);

        carrinho.setProdutos(produtos);
        carrinhoRepository.save(carrinho);

        CarrinhoDTO carrinhoDtoSalvo = mapper.map(carrinho, CarrinhoDTO.class);
        
        return carrinhoDtoSalvo;
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

	public CarrinhoDTO createCarrinho(@Valid CarrinhoDTO carrinhoDto) {
        Carrinho carrinho = new Carrinho();
        carrinho.setStatus(carrinhoDto.getStatus());

        List<Produto> produtos = new ArrayList<>();
        for (ProdutoDTO produtoDTO : carrinhoDto.getProdutos()) {
            Optional<Produto> optionalProduto = produtoRepository.findById(produtoDTO.getId());
            if (optionalProduto.isPresent()) {
                produtos.add(optionalProduto.get());
            } else {
                throw new NoSuchElementException("Produto não encontrado: " + produtoDTO.getId());
            }
        }

        carrinho.setProdutos(produtos);
        
        carrinho = mapper.map(carrinhoDto, Carrinho.class);        

        carrinho = carrinhoRepository.save(carrinho);

        CarrinhoDTO resultDTO = mapper.map(carrinho, CarrinhoDTO.class);

        return resultDTO;
	}
	
}
