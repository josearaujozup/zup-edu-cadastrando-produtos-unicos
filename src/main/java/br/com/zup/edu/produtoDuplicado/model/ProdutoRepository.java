package br.com.zup.edu.produtoDuplicado.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public boolean existsByCodigo(String codigo);
	
}
