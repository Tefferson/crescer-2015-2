package br.com.cwi.crescer.lavanderia.domain;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Produto")
@SequenceGenerator(name = Produto.SEQUENCE_NAME, sequenceName = Produto.SEQUENCE_NAME)
public class Produto {

	public static final String SEQUENCE_NAME = "seq_produto";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@Column(name = "IDProduto")
	private Long idProduto;

	@ManyToOne
	@JoinColumn(name = "IDServico")
	@Basic(optional = false)
	private Servico servico;

	@ManyToOne
	@JoinColumn(name = "IDMaterial")
	@Basic(optional = false)
	private Material material;

	@Column(name = "Valor", precision = 12, scale = 2)
	@Basic(optional = false)
	private BigDecimal valor;

	@Column(name = "Situacao")
	private SituacaoProduto situacao;

	@Column(name = "Prazo")
	@Basic(optional = false)
	private Integer prazo;

	public SituacaoProduto getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoProduto situacao) {
		this.situacao = situacao;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public enum SituacaoProduto {
		ATIVO, INATIVO;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void ativar() {
		situacao = SituacaoProduto.ATIVO;
	}

	public void desativar() {
		situacao = SituacaoProduto.INATIVO;
	}

	@Override
	public String toString() {
		return servico.getDescricao() + " " + material.getDescricao();
	}

}
