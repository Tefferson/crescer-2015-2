package br.com.cwi.crescer.lavanderia.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
@SequenceGenerator(name = Cliente.SEQUENCE_NAME, sequenceName = Cliente.SEQUENCE_NAME, allocationSize = 1)
public class Cliente {

	public static final String SEQUENCE_NAME = "seq_cliente";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@Column(name = "IDCliente")
	private Long idCliente;

	@Column(name = "Nome", length = 70)
	@Basic(optional = false)
	private String nome;

	@Column(name = "CPF", length = 11)
	@Basic(optional = false)
	private String cpf;

	@Column(name = "Email", length = 100)
	private String email;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "Situacao", length = 1)
	private SituacaoCliente situacao;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	@Embedded
	private Endereco endereco;

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public static enum SituacaoCliente {
		ATIVO, INATIVO;
	}

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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void ativar(){
		this.situacao = SituacaoCliente.ATIVO;
	}
	
	public void desativar(){
		this.situacao = SituacaoCliente.INATIVO;
	}

	public SituacaoCliente getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoCliente situacao) {
		this.situacao = situacao;
	}

}
