package com.carrinho.produto.dtos;

public class ProdutoDTO {
	 private Long id;
	    private String nome;
	    private int qtde;
	    private String descricao;

	    public ProdutoDTO() {
	    }

	    public ProdutoDTO(Long id, String nome, int qtde, String descricao) {
	        this.id = id;
	        this.nome = nome;
	        this.qtde = qtde;
	        this.descricao = descricao;
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
