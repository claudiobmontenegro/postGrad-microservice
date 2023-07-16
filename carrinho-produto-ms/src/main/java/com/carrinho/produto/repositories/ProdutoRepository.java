package com.carrinho.produto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carrinho.produto.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}