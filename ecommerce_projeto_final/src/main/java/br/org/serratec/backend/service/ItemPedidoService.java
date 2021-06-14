package br.org.serratec.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ItemPedidoRepository;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ItemPedido inserir (ItemPedido itemPedido) {
		Optional<Produto> produto = produtoRepository.findById(itemPedido.getProduto().getId());
		itemPedido.setPrecoVenda(produto.get().getValorUnitario());
		System.out.println(produto.get().getValorUnitario());
		itemPedido = itemPedidoRepository.save(itemPedido);
		
		return itemPedido;
	}
}
