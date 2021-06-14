package br.org.serratec.backend.controller;

import java.util.List;

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

import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.PedidoRepository;
import br.org.serratec.backend.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public ResponseEntity<Pedido> inserir(@Valid @RequestBody Pedido pedido){
		return ResponseEntity.ok(pedidoService.criar(pedido));
	}
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listar(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		return ResponseEntity.ok(pedidos);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido, @PathVariable Long id){
		if(!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok((pedidoService.atualizar(pedido, id)));
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Pedido> remover(@PathVariable Long id) {
		
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pedidoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
