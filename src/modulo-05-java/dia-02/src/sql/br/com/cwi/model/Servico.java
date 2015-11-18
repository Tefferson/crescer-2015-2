package sql.br.com.cwi.model;

public class Servico extends BaseModel{
	
	private String dsServico;
	
	public String getDsServico() {
		return dsServico;
	}
	public void setDsServico(String dsServico) {
		this.dsServico = dsServico;
	}
	
	public String toString() {

		StringBuilder sb = new StringBuilder();

		String newLine = System.lineSeparator();

		sb.append(newLine + "Id: " + super.getId() + newLine);
		sb.append("Descrição: " + this.dsServico + newLine);

		return sb.toString();
	}
		
}
