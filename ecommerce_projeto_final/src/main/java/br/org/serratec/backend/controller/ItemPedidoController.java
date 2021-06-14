package br.org.serratec.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.repository.ItemPedidoRepository;
import br.org.serratec.backend.service.ItemPedidoService;

@RestController
@RequestMapping("/itempedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	@GetMapping
	public ResponseEntity<List<ItemPedido>> listar(){
		List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
		return ResponseEntity.ok(itemPedidos);
	}
	
	@PostMapping
	public ResponseEntity<ItemPedido> inserir(@RequestBody ItemPedido itemPedido){
		
		return ResponseEntity.ok(itemPedidoService.inserir(itemPedido));
		
	}
}
