package br.com.zup.edu.produtoDuplicado.controller;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.edu.produtoDuplicado.model.Produto;

public class ProdutoDTO {
	@NotBlank
    private String nome;

    @NotBlank
    @Size(max = 6)
    private String codigo;

    @NotNull
    private BigDecimal preco;

	public ProdutoDTO(@NotBlank String nome, @NotBlank @Size(max = 6) String codigo, @NotBlank BigDecimal preco) {
		this.nome = nome;
		this.codigo = codigo;
		this.preco = preco;
	}
    
	public ProdutoDTO() {
		
	}

	public Produto toModel() {
		return new Produto(nome, codigo, preco);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	
}
