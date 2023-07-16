package com.carrinho.produto.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.estudo.pagamento.entities.Status;


@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();

    public Carrinho() {
    }

    public Carrinho(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
	
}
