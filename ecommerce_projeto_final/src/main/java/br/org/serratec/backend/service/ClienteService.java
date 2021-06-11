package br.org.serratec.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.exception.UsuarioException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.ClienteRepository;
import br.org.serratec.backend.repository.EnderecoRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Cliente inserir(Cliente cliente) throws CpfException, EmailException, UsuarioException {
		Cliente cCpf = clienteRepository.findByCpf(cliente.getCpf());
		if (cCpf != null) {
			throw new CpfException("CPF já existente");
		}
		Cliente cEmail = clienteRepository.findByEmail(cliente.getEmail());
		if (cEmail != null) {
			throw new EmailException("Email já existente");
		}
		Cliente cUsuario = clienteRepository.findByNomeUsuario(cliente.getNomeUsuario());
		if (cUsuario != null) {
			throw new UsuarioException("Usuário já existente");
		}
		Cliente c1 = new Cliente();
		EnderecoDTO dto = buscar(cliente.getEndereco().getCep());
		if (dto != null) {
			c1.setEndereco(new Endereco(dto));
		}

		c1.setCpf(cliente.getCpf());
		c1.setEmail(cliente.getEmail());
		c1.setNomeUsuario(cliente.getNomeUsuario());
		c1.setDataNasc(cliente.getDataNasc());
		c1.setNomeCompleto(cliente.getNomeCompleto());
		c1.setSenha(bCryptPasswordEncoder.encode(cliente.getSenha()));
		c1.setTelefone(cliente.getTelefone());
		c1.setComplemento(cliente.getComplemento());
		c1.setNumero(cliente.getNumero());

		clienteRepository.save(c1);
		return c1;
	}

	public EnderecoDTO buscar(String cep) {
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			System.out.println("aqui");
			return new EnderecoDTO(endereco.get());
		} else {
			System.out.println("aqui2");
			RestTemplate restTemplate = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(uri, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				return adicionar(enderecoViaCep.get());
			} else {
				return null;
			}
		}
	}

	public EnderecoDTO adicionar(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return new EnderecoDTO(endereco);
	}

	public Cliente atualizar(Cliente cliente, Long id) {

		/*
		 * if (buscar(cliente.getEndereco().getCep()) != null) { EnderecoDTO dto =
		 * buscar(cliente.getEndereco().getCep()); cliente.setEndereco(new
		 * Endereco(dto)); }
		 */

		cliente.setId(id);
		// cliente = inserir(cliente);
		return cliente;
	}
}
