package br.org.serratec.backend.model;

import java.time.LocalDate;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;





@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;
	@NotBlank(message = "Email não pode estar em branco")
	@Size(max = 30, message = "Email com máximo de {max} caracteres")
	@Email(message = "Email inválido")
	private String email;
	@NotBlank(message = "Nome usuário não pode estar em branco")
	@Size(max = 20, min = 3, message = "Nome do usuário não pode ser maior que {max} caracteres e menor que {min} caracteres")
	private String nomeUsuario;
	@NotBlank(message = "Nome usuário não pode estar em branco")
	@Size(max = 20, message = "Nome do usuário não pode ser maior que {max} caracteres")
	private String nomeCompleto;
	@NotBlank(message = "Senha não pode estar em branco")
	@Size(min = 8, message = "Senha não pode ser menor que {min} caracteres")
	private String senha;
	@NotBlank(message = "CPF não pode estar em branco")
	@CPF(message = "CPF inválido")
	private String cpf;
	@NotBlank(message = "Telefone não pode estar em branco")
	@Size(max = 11, min = 11, message = "Telefone tem que ser no mínimo {min} caracteres e no máximo {max} caracteres")
	private String telefone;
	@Size(max = 20, message = "Complemento tem que ser no máximo {max} caracteres")
	private String complemento;
	private Integer numero;
	@Past(message = "Data não existente")
	private LocalDate dataNasc;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	

	public Cliente(Long id,
			@NotBlank(message = "Email não pode estar em branco") @Size(max = 30, message = "Email com máximo de {max} caracteres") @Email(message = "Email inválido") String email,
			@NotBlank(message = "Nome usuário não pode estar em branco") @Size(max = 20, min = 3, message = "Nome do usuário não pode ser maior que {max} caracteres e menor que {min} caracteres") String nomeUsuario,
			@NotBlank(message = "Nome usuário não pode estar em branco") @Size(max = 20, message = "Nome do usuário não pode ser maior que {max} caracteres") String nomeCompleto,
			@NotBlank(message = "Senha não pode estar em branco") @Size(min = 8, message = "Senha não pode ser menor que {min} caracteres") String senha,
			@NotBlank(message = "CPF não pode estar em branco") @CPF(message = "CPF inválido") String cpf,
			@NotBlank(message = "Telefone não pode estar em branco") @Size(max = 11, min = 11, message = "Telefone tem que ser no mínimo {min} caracteres e no máximo {max} caracteres") String telefone,
			@Past(message = "Data não existente") LocalDate dataNasc, Endereco endereco,
			@NotBlank(message = "Complemento não pode estar em branco") @Size(max = 20, message = "Complemento tem que ser no máximo {max} caracteres") String complemento,
			Integer numero) {
		super();
		this.id = id;
		this.email = email;
		this.nomeUsuario = nomeUsuario;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNasc = dataNasc;
		this.endereco = endereco;
		this.complemento = complemento;
		this.numero = numero;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
