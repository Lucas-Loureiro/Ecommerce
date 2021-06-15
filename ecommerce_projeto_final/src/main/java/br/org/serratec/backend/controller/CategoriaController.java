
package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;
import br.org.serratec.backend.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Listar Categoria", notes = "Listar Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Categoria listadas com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<List<Categoria>> listar(){
		List<Categoria> categorias = categoriaRepository.findAll();
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping("{nome}")
	@ApiOperation(value="Buscar uma Categoria", notes = "Buscar uma Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Categoria listadas com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Categoria> buscar(@PathVariable String nome){
		Optional<Categoria> c1 = categoriaRepository.findByNome(nome);
		if(!c1.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(c1.get());
	}
	
	@PostMapping
	@ApiOperation(value="Adicionar uma Categoria", notes = "Adiciona Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Categoria adicionada com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Object> inserir(@Valid @RequestBody Categoria categoria) {
		
			return ResponseEntity.ok(categoriaService.inserir(categoria));
		
	}
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Remover uma Categoria", notes = "Remove Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Categoria removida com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Categoria> remover(@PathVariable Long id) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		categoriaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Atualizar uma Categoria", notes = "Atualiza Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Categoria atualizada com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
		if(!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoria.setId(id);
		categoria = categoriaRepository.save(categoria);
		return ResponseEntity.ok(categoria);
	
	}
}