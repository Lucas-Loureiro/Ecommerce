package br.org.serratec.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long id;
	@NotBlank(message = "Nome não pode estar em branco")
	@Size(max = 30, message = "A quantidade máxima de caracteres é {max}")
	private String nome;
	@NotBlank(message = "Descrição não pode estar em branco")
	@Size(max = 150, message = "A quantidade máxima de caracteres é {max}")
	private String descricao;
	
	

	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(Long id,
			@NotBlank(message = "Nome não pode estar em branco") @Size(max = 30, message = "A quantidade máxima de caracteres é {max}") String nome,
			@NotBlank(message = "Descrição não pode estar em branco") @Size(max = 150, message = "A quantidade máxima de caracteres é {max}") String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
