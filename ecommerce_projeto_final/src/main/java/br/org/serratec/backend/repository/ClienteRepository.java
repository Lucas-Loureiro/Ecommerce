package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	public Cliente findByCpf(String cpf);
	public Cliente findByEmail(String email);
	public Cliente findByNomeUsuario(String nomeUsuario);
}
