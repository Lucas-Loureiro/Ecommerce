package br.org.serratec.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired 
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Pedido criar(Pedido pedido, Cliente cliente) {
		Random random = new Random();   
		Pedido p1 = new Pedido();
		p1.setCliente(cliente);
		p1.setDataEntrega(LocalDate.now().plusDays(random.nextInt(10)));
		p1.setDataEnvio(LocalDate.now().plusDays(random.nextInt(3)));
		p1.setDataPedido(LocalDate.now());
		p1.setStatus(pedido.getStatus().NAO_FINALIZADO);
		pedidoRepository.save(p1);
		return p1;
		
	}
}
