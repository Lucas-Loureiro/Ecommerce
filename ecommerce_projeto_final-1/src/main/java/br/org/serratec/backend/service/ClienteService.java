package br.org.serratec.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.exception.UsuarioException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.ClienteRepository;
import br.org.serratec.backend.repository.EnderecoRepository;
import br.org.serratec.backend.repository.PedidoRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoService service;

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
		EnderecoDTO dto = service.buscar(cliente.getEndereco().getCep());
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

	public Cliente atualizar(Cliente c1, Long id) throws CpfException, EmailException, UsuarioException {

		EnderecoDTO dto = service.buscar(c1.getEndereco().getCep());
		if (dto != null) {
			c1.setEndereco(new Endereco(dto));
		}
		c1.setCpf(c1.getCpf());
		c1.setEmail(c1.getEmail());
		c1.setNomeUsuario(c1.getNomeUsuario());
		c1.setDataNasc(c1.getDataNasc());
		c1.setNomeCompleto(c1.getNomeCompleto());
		c1.setSenha(bCryptPasswordEncoder.encode(c1.getSenha()));
		c1.setTelefone(c1.getTelefone());
		c1.setComplemento(c1.getComplemento());
		c1.setNumero(c1.getNumero());

		clienteRepository.save(c1);
		return c1;

	}
}
