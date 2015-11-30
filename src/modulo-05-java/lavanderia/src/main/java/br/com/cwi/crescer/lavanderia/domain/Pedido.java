package br.com.cwi.crescer.lavanderia.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Pedido")
@SequenceGenerator(name = Pedido.SEQUENCE_NAME, sequenceName = Pedido.SEQUENCE_NAME)
public class Pedido {

	public static final String SEQUENCE_NAME = "seq_pedido";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@Column(name = "IDPedido")
	private Long idPedido;

	@ManyToOne
	@JoinColumn(name = "IDCliente")
	@Basic(optional = false)
	private Cliente cliente;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAINCLUSAO")
	@Basic(optional = false)
	private Date dataInclusao;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATAENTREGA")
	private Date dataEntrega;

	@Column(name = "Valorbruto", precision = 12, scale = 2)
	private BigDecimal valorBruto;

	@Column(name = "Valordesconto", precision = 12, scale = 2)
	private BigDecimal valorDesconto;

	@Column(name = "Valorfinal", precision = 12, scale = 2)
	private BigDecimal valorFinal;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "Situacao")
	private SituacaoPedido situacao;

	@OneToMany(mappedBy = "idPedido", fetch = FetchType.EAGER)
	private List<Item> itens;

	public static enum SituacaoPedido {
		PENDENTE, PROCESSANDO, PROCESSADO, ENCERRADO, CANCELADO;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public SituacaoPedido getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoPedido situacao) {
		this.situacao = situacao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public boolean isProcessando() {
		return situacao == SituacaoPedido.PROCESSANDO;
	}

	public boolean isPendente() {
		return situacao == SituacaoPedido.PENDENTE;
	}

	public void incrementarValorBruto(BigDecimal incremento) {
		valorBruto = valorBruto.add(incremento);
	}

	public BigDecimal getPesoTotalDosItens() {
		BigDecimal pesoTotal = BigDecimal.ZERO;

		for (Item item : itens) {
			pesoTotal = pesoTotal.add(item.getPeso());
		}

		return pesoTotal;
	}

	public boolean isAguardandoRetirada() {
		return situacao == SituacaoPedido.PROCESSADO;
	}

	public boolean isCancelado() {
		return situacao == SituacaoPedido.CANCELADO;
	}

	public boolean isEncerrado() {
		return situacao == SituacaoPedido.ENCERRADO;
	}
}
