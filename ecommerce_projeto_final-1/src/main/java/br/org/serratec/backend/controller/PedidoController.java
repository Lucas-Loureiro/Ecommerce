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

import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.PedidoRepository;
import br.org.serratec.backend.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Adicionar um Pedido", notes = "Adiciona Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Pedido adicionado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Pedido> inserir(@Valid @RequestBody Pedido pedido){
		return ResponseEntity.ok(pedidoService.criar(pedido));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Listar Pedido", notes = "Lista Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Pedido listado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<List<Pedido>> listar(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		return ResponseEntity.ok(pedidos);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Atualizar Pedido", notes = "Atualiza Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Pedido atualizado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido, @PathVariable Long id){
		if(!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		Optional<Pedido> p = pedidoRepository.findById(id);
		return ResponseEntity.ok((pedidoService.atualizar(p.get(), id)));
		
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Remover um Pedido", notes = "Remove Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="Pedido removido com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<Pedido> remover(@PathVariable Long id) {
		
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pedidoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}