package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;
import br.org.serratec.backend.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		List<Categoria> categorias = categoriaRepository.findAll();
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping("{nome}")
	public ResponseEntity<Categoria> buscar(@PathVariable String nome){
		Optional<Categoria> c1 = categoriaRepository.findByNome(nome);
		if(!c1.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(c1.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> inserir(@Valid @RequestBody Categoria categoria) {
		
			return ResponseEntity.ok(categoriaService.inserir(categoria));
		
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Categoria> remover(@PathVariable Long id) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		categoriaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
		if(!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoria.setId(id);
		categoria = categoriaRepository.save(categoria);
		return ResponseEntity.ok(categoria);
	
	}
}
