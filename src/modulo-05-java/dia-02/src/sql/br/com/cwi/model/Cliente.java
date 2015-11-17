package sql.br.com.cwi.model;

public class Cliente {

	private Long idCliente;

	private String nmCliente;

	private String nrCpf;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNmCliente() {
		return nmCliente;
	}

	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}

	public String getNrCpf() {
		return nrCpf;
	}

	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		String newLine = System.lineSeparator();

		sb.append(newLine + "Nome: " + this.nmCliente + newLine);
		sb.append("Id: " + this.idCliente + newLine);
		sb.append("CPF: " + this.nrCpf + newLine);

		return sb.toString();
	}

}
