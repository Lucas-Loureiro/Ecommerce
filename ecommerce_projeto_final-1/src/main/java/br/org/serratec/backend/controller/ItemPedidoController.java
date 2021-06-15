package br.org.serratec.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.repository.ItemPedidoRepository;
import br.org.serratec.backend.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/itempedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Listar ItemPedido", notes = "Lista ItemPedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="ItemPedido listado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<List<ItemPedido>> listar(){
		List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
		return ResponseEntity.ok(itemPedidos);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Adicionar um ItemPedido", notes = "Adiciona ItemPedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="ItemPedido adicionado com sucesso"),
			@ApiResponse(code = 401, message="Erro autenticação"),
			@ApiResponse(code = 403, message="Proibido"),
			@ApiResponse(code = 404, message="Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message="Ocorreu uma exceção")
	})
	public ResponseEntity<ItemPedido> inserir(@RequestBody ItemPedido itemPedido){
		
		return ResponseEntity.ok(itemPedidoService.inserir(itemPedido));
		
	}
}