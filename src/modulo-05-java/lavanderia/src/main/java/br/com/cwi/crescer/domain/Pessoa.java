package br.com.cwi.crescer.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Pessoa")
@SequenceGenerator(name = Pessoa.SEQUENCE_NAME, sequenceName = Pessoa.SEQUENCE_NAME)
public class Pessoa {

	public static final String SEQUENCE_NAME = "pessoa_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@Column(name = "IDPessoa")
	private Long idPessoa;

	@Column(name = "Nome", length = 50)
	@Basic(optional = false)
	private String nome;

	@Column(name = "IDEndereco", length = 10)
	private Long idEndereco;

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

}
