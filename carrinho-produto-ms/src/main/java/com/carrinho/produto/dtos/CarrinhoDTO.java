package com.carrinho.produto.dtos;

import java.util.List;

import com.estudo.pagamento.entities.Status;

public class CarrinhoDTO {
	private Long id;
    private Status status;
    private List<ProdutoDTO> produtos;

    public CarrinhoDTO() {
    }

    public CarrinhoDTO(Long id, Status status, List<ProdutoDTO> produtos) {
        this.id = id;
        this.status = status;
        this.produtos = produtos;
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

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

}
