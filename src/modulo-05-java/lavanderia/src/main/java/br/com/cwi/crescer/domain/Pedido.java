package br.com.cwi.crescer.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Pedido")
@SequenceGenerator(name = Pedido.SEQUENCE_NAME, sequenceName = Pedido.SEQUENCE_NAME)
public class Pedido {

	public static final String SEQUENCE_NAME = "seq_pedido";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@Column(name = "IdPedido")
	private Long idPedido;
	
	@Column(name = "IdCliente")
	@Basic(optional = false)
	private Long idCliente;
	
	@Column(name = "DataInclusao")
	@Basic(optional = false)
	private Date dataInclusao;
	
	@Column(name = "DataEntrega")
	private Date dataEntrega;
	
	@Column(name = "Valor", precision = 12, scale = 2)
	@Basic(optional = false)
	private Double valor;

	@Column(name = "Situacao")
	private Character situacao;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}
	
}
