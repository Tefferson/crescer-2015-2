package br.com.cwi.crescer.domain;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public SituacaoItem getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoItem situacao) {
		this.situacao = situacao;
	}

	public static final String SEQUENCE_NAME = "seq_item";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@Column(name = "IDItem")
	private Long idItem;

	@Column(name = "IDPEDIDO")
	@Basic(optional = false)
	private Long idPedido;

	@Column(name = "IDPRODUTO")
	@Basic(optional = false)
	private Long idProduto;

	@Column(name = "PESO", precision = 12, scale = 2)
	@Basic(optional = false)
	private BigDecimal peso;

	@Column(name = "VALORUNITARIO", precision = 12, scale = 2)
	@Basic(optional = false)
	private BigDecimal valorUnitario;

	@Column(name = "VALORDESCONTO", precision = 12, scale = 2)
	@Basic(optional = false)
	private BigDecimal valorDesconto;

	@Column(name = "VALORTOTAL", precision = 12, scale = 2)
	@Basic(optional = false)
	private BigDecimal valorTotal;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "SITUACAO")
	private SituacaoItem situacao;

	public static enum SituacaoItem {
		PENDENTE, PROCESSANDO, PROCESSADO;
	}

}
