package br.com.cwi.crescer.lavanderia.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class Endereco {

	@ManyToOne
	@JoinColumn(name = "IDCidade")
	private Cidade cidade;

	@Column(name = "Endereco", length = 50)
	private String endereco;

	@Column(name = "Bairro", length = 50)
	private String bairro;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	@Column(name = "CEP")
	@Length(min = 0, max = 99999999)
	private Integer cep;

}
