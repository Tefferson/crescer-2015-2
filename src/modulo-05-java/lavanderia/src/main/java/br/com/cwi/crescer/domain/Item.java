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
@Table(name = "Item")
@SequenceGenerator(name = Item.SEQUENCE_NAME, sequenceName = Item.SEQUENCE_NAME)
public class Item {

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	public static final String SEQUENCE_NAME = "seq_item";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@Column(name = "IdItem")
	private Long idItem;
	
	@Column(name = "IdPedido")
	@Basic(optional = false)
	private Long idPedido;
	
	@Column(name = "IdProduto")
	@Basic(optional = false)
	private Long idProduto;
	
	@Column(name = "Peso", precision = 12, scale = 2)
	@Basic(optional = false)
	private Double peso;
	
	@Column(name = "ValorUnitario", precision = 12, scale = 2)
	@Basic(optional = false)
	private Double valorUnitario;
	
	@Column(name = "ValorDeconto", precision = 12, scale = 2)
	@Basic(optional = false)
	private Double valorDesconto;
	
	@Column(name = "ValorTotal", precision = 12, scale = 2)
	@Basic(optional = false)
	private Double valorTotal;

	@Column(name = "Situacao")
	private Character situacao;
	
}
