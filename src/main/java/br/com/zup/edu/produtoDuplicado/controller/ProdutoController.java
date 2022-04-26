package br.com.zup.edu.produtoDuplicado.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.produtoDuplicado.model.Produto;
import br.com.zup.edu.produtoDuplicado.model.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private final ProdutoRepository repository;

	public ProdutoController(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoDTO request, UriComponentsBuilder uriComponentsBuilder){
		
		if(repository.existsByCodigo(request.getCodigo())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Produto já existe no sistema");
		}
		
        Produto produto = request.toModel();

        repository.save(produto);

        URI location = uriComponentsBuilder.path("/produtos/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
	
	@ExceptionHandler
	public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e){
		
		Map<String, Object> body = Map.of(
				"message", "produto já existente no sistema",
				"timestamp", LocalDateTime.now()
		);
		
		return ResponseEntity.unprocessableEntity().body(body);
	}
	
	
}
