package br.com.cwi.crescer.lavanderia.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class PedidoIncluirItemDTO {

	@NotNull
	private Long idPedido;

	@NotNull
	private Long idServico;

	@NotNull
	private Long idMaterial;

	@NotNull
	private BigDecimal peso;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
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

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

}
