package com.carrinho.produto.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrinho.produto.dtos.ProdutoDTO;
import com.carrinho.produto.entities.Produto;
import com.carrinho.produto.repositories.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ModelMapper mapper;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ModelMapper mapper) {
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;
    }

    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> mapper.map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    public ProdutoDTO findById(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            return mapper.map(produto, ProdutoDTO.class);
        }
        throw new RuntimeException("Produto not found");
    }

    public ProdutoDTO save(ProdutoDTO produtoDto) {
        Produto produto = mapper.map(produtoDto, Produto.class);
        Produto savedProduto = produtoRepository.save(produto);
        return mapper.map(savedProduto, ProdutoDTO.class);
    }

    public ProdutoDTO update(Long id, ProdutoDTO produtoDto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.setNome(produtoDto.getNome());
            produto.setQtde(produtoDto.getQtde());
            produto.setDescricao(produtoDto.getDescricao());
            Produto updatedProduto = produtoRepository.save(produto);
            return mapper.map(updatedProduto, ProdutoDTO.class);
        }
        throw new RuntimeException("Produto not found");
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }
}
