package application.br.com.cwi.application;

import sql.br.com.cwi.dao.PedidoDAO;

public class MenuPedidoScreen implements IScreen {

	public final int INCLUIR_PEDIDO = 1;
	public final int REMOVE_MENU = 2;
	public final int LIST = 3;
	public final int CHANGE_LIST = 4;
	public final int WRITE_LIST = 5;
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
		case REMOVE_MENU:
			new RemoveScreen(application).show();
			break;
		case LIST:
			new ListPedidoScreen((PedidoDAO) application.getPedidoDAO()).show();
			break;
		case CHANGE_LIST:
			application.changeList();
			System.out.println("Lista trocada!!!");
			break;
		case WRITE_LIST:
			new WriteScreen(application).show();
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
		String newLine = System.getProperty("line.separator");

		String actualListType = application.getActualList().getClass().getSimpleName();

		sb.append(INCLUIR_PEDIDO + "-Incluir pedido" + newLine);
		sb.append(REMOVE_MENU + "-Remover" + newLine);
		sb.append(LIST + "-Listar" + newLine);
		sb.append(CHANGE_LIST + "-Trocar lista(atual: " + actualListType + ")" + newLine);
		sb.append(WRITE_LIST + "-Gravar lista" + newLine);
		sb.append(EXIT + "-Sair" + newLine);

		return sb.toString();
	}

}
