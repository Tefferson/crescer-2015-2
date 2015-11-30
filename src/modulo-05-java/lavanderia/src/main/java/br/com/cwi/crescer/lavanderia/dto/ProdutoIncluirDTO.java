package br.com.cwi.crescer.lavanderia.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class ProdutoIncluirDTO {

	@NotNull
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valor;

	@NotNull
	@NumberFormat(style=Style.NUMBER)
	private Integer prazo;
	
	@NotNull
	private Long idServico;
	
	@NotNull
	private Long idMaterial;

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

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public Long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}

}
