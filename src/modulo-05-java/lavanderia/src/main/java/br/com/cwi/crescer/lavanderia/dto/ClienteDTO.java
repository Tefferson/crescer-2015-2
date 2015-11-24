package br.com.cwi.crescer.lavanderia.dto;

public class ClienteDTO {

	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private Long idCidade;
	private String bairro;
	private String endereco;
	private Integer cep;
		
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public static enum SituacaoCliente {
		ATIVO, INATIVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idCliente) {
		this.id = idCliente;		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
