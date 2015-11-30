package br.com.cwi.crescer.lavanderia.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.com.cwi.crescer.lavanderia.domain.Produto.SituacaoProduto;

public class ProdutoEditarDTO {

	private Long id;

	@NotNull
	private SituacaoProduto situacao;

	@NotNull
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valor;

	@NotNull
	@NumberFormat(style=Style.NUMBER)
	private Integer prazo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituacaoProduto getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoProduto situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}
	
}
