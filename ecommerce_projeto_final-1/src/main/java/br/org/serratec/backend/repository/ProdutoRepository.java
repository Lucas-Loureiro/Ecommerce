package br.org.serratec.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public Optional<Produto> findByNome(String nome);

}
