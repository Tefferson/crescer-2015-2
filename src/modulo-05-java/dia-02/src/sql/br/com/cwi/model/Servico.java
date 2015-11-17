package sql.br.com.cwi.model;

public class Servico {
	private Long idServico;
	private String dsServico;
	
	public Long getIdServico() {
		return idServico;
	}
	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}
	public String getDsServico() {
		return dsServico;
	}
	public void setDsServico(String dsServico) {
		this.dsServico = dsServico;
	}
	
	public String toString() {

		StringBuilder sb = new StringBuilder();

		String newLine = System.lineSeparator();

		sb.append(newLine + "Id: " + this.idServico + newLine);
		sb.append("Descrição: " + this.dsServico + newLine);

		return sb.toString();
	}
		
}
