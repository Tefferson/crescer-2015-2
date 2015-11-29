package br.com.cwi.crescer.lavanderia.dto;

public class ItemResumoDTO {

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean getProcessado() {
		return processado;
	}
	public void setProcessado(boolean processado) {
		this.processado = processado;
	}
	private Long id;
	private boolean processado;

	
	
}
