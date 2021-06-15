package br.org.serratec.backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.model.Foto;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.FotoRepository;
import br.org.serratec.backend.repository.ProdutoRepository;
import br.org.serratec.backend.service.FotoService;
import br.org.serratec.backend.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoService fotoService;

	@Autowired
	private FotoRepository fotoRepository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Listar Produto", notes = "Lista Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Produto listado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	private ResponseEntity<List<ProdutoDTO>> listar() {
		List<ProdutoDTO> produtos = produtoService.listar();
		return ResponseEntity.ok(produtos);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Adicionar um Produto", notes = "Adiciona Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Produto adicionado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Object> colocar(@RequestParam MultipartFile file,@RequestPart Produto produto) throws IOException{
		try {
			return ResponseEntity.ok(produtoService.inserir(produto, file));
		} catch (IOException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		} 
	}
	
	
	@GetMapping("{nome}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value=" Buscar um Produto", notes = "Busca um Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Produto buscado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<ProdutoDTO> buscar(@PathVariable String nome){
		return ResponseEntity.ok(produtoService.buscar(nome));
		
	}
	

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Remover um Produto", notes = "Remove Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Produto removido com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Produto> remover(@PathVariable Long id) {
		
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		
		produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Atualizar Produto", notes = "Atualiza Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Produto atualizado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestParam MultipartFile file,@Valid @RequestPart Produto produto) {
		if(!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			
			produto.setId(id);
			
			return ResponseEntity.ok(produtoService.inserir(produto, file));
		} catch (IOException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	
	}
	
	@GetMapping("/{id}/foto")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value=" Buscar Por Foto", notes = "Busca um Por Toto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Foto buscada com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<byte[]> buscarPorFoto(@PathVariable Long id){
		Foto foto = fotoService.buscar(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", foto.getTipo());
		headers.add("content-length",String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}
}