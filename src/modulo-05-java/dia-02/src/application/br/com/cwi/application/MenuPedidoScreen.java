package application.br.com.cwi.application;

public class MenuPedidoScreen implements IScreen {

	public final int INCLUIR_PEDIDO = 1;
	public final int LOAD_PEDIDO = 2;
	public final int LIST_CLIENTE_PEDIDOS = 3;
	public final int EXIT = 0;

	private Application application;

	public MenuPedidoScreen(Application application) {
		this.application = application;
	}

	@Override
	public void show() {
		System.out.println(options());
		action();
	}

	@Override
	public void action() {

		System.out.println("Informe uma opção:");

		switch (application.getScanner().nextInt()) {
		case INCLUIR_PEDIDO:
			new AddPedidoScreen(application).show();
			break;
		case LOAD_PEDIDO:
			new LoadPedidoScreen(application).show();
			break;
		case LIST_CLIENTE_PEDIDOS:
			new ListPedidoScreen(application).show();
			break;
		case EXIT:
			System.out.println("Programa finalizado!");
			System.exit(0);
			break;
		}

		System.out.println();
		System.out.println();

	}

	@Override
	public String options() {

		StringBuilder sb = new StringBuilder();
		String newLine = System.lineSeparator();

		sb.append(INCLUIR_PEDIDO + "-Incluir pedido" + newLine);
		sb.append(LOAD_PEDIDO + "-Buscar pedido" + newLine);
		sb.append(LIST_CLIENTE_PEDIDOS + "-Listar" + newLine);
		sb.append(EXIT + "-Sair" + newLine);

		return sb.toString();
	}

}
