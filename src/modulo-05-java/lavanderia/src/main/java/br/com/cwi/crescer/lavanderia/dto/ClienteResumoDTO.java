package br.com.cwi.crescer.lavanderia.dto;

import br.com.cwi.crescer.lavanderia.domain.Cliente.SituacaoCliente;

public class ClienteResumoDTO {

	private Long id;
	private String nome;
	private String cpf;
	private SituacaoCliente situacao;

	public ClienteResumoDTO() {
	}

	public ClienteResumoDTO(Long id, String nome, String cpf, SituacaoCliente situacao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.situacao = situacao;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SituacaoCliente getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoCliente situacao) {
		this.situacao = situacao;
	}

}
