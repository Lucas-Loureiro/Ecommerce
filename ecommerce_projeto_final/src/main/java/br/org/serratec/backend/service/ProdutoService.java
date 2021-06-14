package br.org.serratec.backend.service;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.CategoriaRepository;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FotoService fotoService;


	public List<ProdutoDTO> listar() {
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> produtoDTOs = new ArrayList<ProdutoDTO>();
		for (Produto produto : produtos) {
			produtoDTOs.add(adicionarFotoUrl(produto));
		}
		return produtoDTOs;
	}

	public ProdutoDTO inserir(Produto p1, MultipartFile file) throws IOException {
		p1.setDataCadastro(LocalDate.now());
		p1.setFoto(fotoService.inserir(file));
		produtoRepository.save(p1);
		return adicionarFotoUrl(p1);
	}

	public ProdutoDTO adicionarFotoUrl(Produto produto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produto/{id}/foto")
				.buildAndExpand(produto.getFoto().getId()).toUri();

		System.out.println("Uri" + uri);

		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setCategoria(produto.getCategoria());
		produtoDTO.setDataCadastro(LocalDate.now());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setQtdEstoque(produto.getQtdEstoque());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		produtoDTO.setUrl(uri.toString());
		return produtoDTO;

	}

	public ProdutoDTO buscar(String nome) {
		Optional<Produto> produto = produtoRepository.findByNome(nome);
		return adicionarFotoUrl(produto.get());
	}

}
