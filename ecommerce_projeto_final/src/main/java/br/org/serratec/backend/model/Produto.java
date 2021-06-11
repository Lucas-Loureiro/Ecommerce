package br.org.serratec.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;
	@NotBlank(message = "Nome não pode estar em branco")
	@Size(max = 30, message = "A quantidade máxima de caracteres é {max}")
	private String nome;
	@NotBlank(message = "Descrição não pode estar em branco")
	@Size(max = 100, message = "A quantidade máxima de caracteres é {max}")
	private String descricao;
	@NotBlank(message = "Quantidade de estoque não pode estar em branco")
	@Min(value = 0)
	private Integer qtdEstoque;
	private LocalDate dataCadastro;
	@Positive
	private BigDecimal valorUnitario;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_foto")
	private Foto foto;
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(Long id,
			@NotBlank(message = "Nome não pode estar em branco") @Size(max = 30, message = "A quantidade máxima de caracteres é {max}") String nome,
			@NotBlank(message = "Descrição não pode estar em branco") @Size(max = 100, message = "A quantidade máxima de caracteres é {max}") String descricao,
			@NotBlank(message = "Quantidade de estoque não pode estar em branco") Integer qtdEstoque,
			LocalDate dataCadastro, BigDecimal valorUnitario, Foto foto, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.dataCadastro = dataCadastro;
		this.valorUnitario = valorUnitario;
		this.foto = foto;
		this.categoria = categoria;
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

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
