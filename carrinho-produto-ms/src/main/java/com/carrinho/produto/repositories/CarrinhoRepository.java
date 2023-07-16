package com.carrinho.produto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrinho.produto.entities.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
