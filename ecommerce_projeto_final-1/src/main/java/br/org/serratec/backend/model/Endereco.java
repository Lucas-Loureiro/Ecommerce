package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.org.serratec.backend.dto.EnderecoDTO;
import io.swagger.annotations.ApiModelProperty;


@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	@ApiModelProperty(value= "Identificador único do endereço", required = true)
	private Long id;
	@ApiModelProperty(value= "CEP", required = true)
	private String cep;
	@ApiModelProperty(value= "LOGRADOURO", required = true)
	private String logradouro;
	@ApiModelProperty(value= "BAIRRO", required = true)
	private String bairro;
	@ApiModelProperty(value= "LOCALIDADE", required = true)
	private String localidade;
	@ApiModelProperty(value= "ESTADO", required = true)
	private String uf;

	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(Long id, String cep, String logradouro, String bairro, String localidade, String uf) {
		super();
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		
		this.uf = uf;
	}
	public Endereco(EnderecoDTO dto) {
		this.cep = dto.getCep();
		this.logradouro = dto.getLogradouro();
		this.bairro = dto.getBairro();
		this.localidade = dto.getLocalidade();
		this.uf = dto.getUf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
