package br.com.zup.edu.produtoDuplicado.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String codigo;
	
	@Column(nullable = false)
    private BigDecimal preco;

	public Produto(String nome, String codigo, BigDecimal preco) {
		this.nome = nome;
		this.codigo = codigo;
		this.preco = preco;
	}
	
	/**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
	public Produto() {
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public BigDecimal getPreco() {
		return preco;
	}
	
}
