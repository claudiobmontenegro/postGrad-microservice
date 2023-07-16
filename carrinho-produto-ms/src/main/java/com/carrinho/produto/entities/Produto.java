package com.carrinho.produto.entities;

import javax.persistence.*;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;
    private String nome;
    private int qtde;
    private String descricao;

    public Produto() {
    }

    public Produto(String nome, int quantidade, String descricao, Carrinho carrinho) {
        this.nome = nome;
        this.qtde = quantidade;
        this.descricao = descricao;
        this.carrinho = carrinho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
