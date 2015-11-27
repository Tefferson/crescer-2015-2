package br.com.cwi.crescer.lavanderia.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Servico")
@SequenceGenerator(name = Servico.SEQUENCE_NAME, sequenceName = Servico.SEQUENCE_NAME)
public class Servico {

	public static final String SEQUENCE_NAME = "seq_servico";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@Column(name = "IDServico")
	private Long idServico;

	@Column(name = "Descricao", length = 30)
	@Basic(optional = false)
	private String descricao;

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
