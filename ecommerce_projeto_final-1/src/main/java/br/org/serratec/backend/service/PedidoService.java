package br.org.serratec.backend.service;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired 
	private PedidoRepository pedidoRepository;
	

	
	@Autowired
	private MailConfig mailConfig;
	
	public Pedido criar(Pedido pedido) {  
		Pedido p1 = new Pedido();
		p1.setCliente(pedido.getCliente());
		p1.setDataEntrega(LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(5, 10)));
		p1.setDataEnvio(LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(0, 3)));
		p1.setDataPedido(LocalDate.now());
		p1.setStatus("NAO_FINALIZADO");
		pedidoRepository.save(p1);
		
		return p1;
		
	}
	
	public Pedido atualizar (Pedido pedido, Long id) {
		
		
		pedido.setId(id);
		pedido.setCliente(pedido.getCliente());
		pedido.setDataEntrega(LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(5, 10)));
		pedido.setDataEnvio(LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(0, 3)));
		pedido.setDataPedido(LocalDate.now());
		pedido.setStatus(pedido.getStatus());
		pedidoRepository.save(pedido);
		if(pedido.getStatus().equals("FINALIZADO")) {
			System.out.println(pedido.toString() + " " + pedido.getCliente().getEmail() + pedido.getTotal());
			mailConfig.enviarEmail(pedido.getCliente().getEmail(), "Nota fiscal", pedido.toString());
		}
		return pedido;
	}
	

}
