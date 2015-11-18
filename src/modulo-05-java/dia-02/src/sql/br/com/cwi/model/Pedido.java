package sql.br.com.cwi.model;

public class Pedido extends BaseModel {

	private String dsPedido;
	private Long idCliente;

	public String getDsPedido() {
		return dsPedido;
	}

	public void setDsPedido(String dsPedido) {
		this.dsPedido = dsPedido;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		String newLine = System.lineSeparator();

		sb.append(newLine + "Id: " + this.id + newLine);
		sb.append("IdCliente: " + this.idCliente + newLine);
		sb.append("Descrição: " + this.dsPedido + newLine);

		return sb.toString();

	}
}
