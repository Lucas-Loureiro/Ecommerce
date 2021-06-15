
package br.org.serratec.backend.controller;

import java.util.List;

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

import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.exception.UsuarioException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.repository.ClienteRepository;
import br.org.serratec.backend.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;

	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Listar Clientes", notes = "Listar Clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Clientes listados com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<List<Cliente>> listar(){
		List<Cliente> clientes = clienteRepository.findAll();
		return ResponseEntity.ok(clientes);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Adicionar um Cliente", notes = "Adiciona Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Cliente adicionado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Object> adicionar(@Valid @RequestBody Cliente cliente) {
		try {
			Cliente c = clienteService.inserir(cliente);
			return ResponseEntity.ok(c);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Atualizar um Cliente", notes = "Atualizar Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Cliente atualizado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Object> atualiazar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
	
		try {
			return ResponseEntity.ok(clienteService.atualizar(cliente, id));
		} catch (CpfException | EmailException | UsuarioException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Remover um Cliente", notes = "Remove Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Cliente removido com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Cliente> deletar(@PathVariable Long id){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}