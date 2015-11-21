package br.com.cwi.crescer.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Cliente")
@SequenceGenerator(name = Cliente.SEQUENCE_NAME, sequenceName = Cliente.SEQUENCE_NAME)
public class Cliente {

	public static final String SEQUENCE_NAME = "seq_cliente";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@Column(name = "IdCliente")
	private Long idCliente;

	@Column(name = "Nome", length = 70)
	@Basic(optional = false)
	private String nome;
	
	@Column(name = "CPF", length = 11)
	@Basic(optional = false)
	private String cpf;
	
	@Column(name = "Email", length = 100)
	private String email;

	@Column(name = "Endereco", length = 50)
	private String endereco;
	
	@Column(name = "Bairro", length = 50)
	private String bairro;
	
	@Column(name = "IdCidade")
	private Long IdCidade;
	
	@Column(name = "CEP")
	@Length(min =0,max =99999999)
	private Integer cep;
	
	@Column(name = "Situacao")
	private Character situacao;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Long getIdCidade() {
		return IdCidade;
	}

	public void setIdCidade(Long idCidade) {
		IdCidade = idCidade;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}
	
}
