package br.com.cwi.application.screen;

import br.com.cwi.application.Application;

public class MenuScreen implements IScreen{

	public final int ADD_MENU = 1;
	public final int REMOVE_MENU = 2;
	public final int LIST = 3;
	public final int CHANGE_LIST = 4;
	public final int EXIT = 0;
	
	private Application application;

	public MenuScreen(Application application) {
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
		case ADD_MENU:
			new AddScreen(application).show();
			break;
		case REMOVE_MENU:
			new RemoveScreen(application).show();
			break;
		case LIST:
			new ListScreen(application.getActualList().list()).show();
			break;
		case CHANGE_LIST:
			application.changeList();
			System.out.println("Lista trocada!!!");
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

		sb.append(ADD_MENU + "-Adicionar" + newLine);
		sb.append(REMOVE_MENU + "-Remover" + newLine);
		sb.append(LIST + "-Listar" + newLine);
		sb.append(CHANGE_LIST + "-Trocar lista(atual: " + actualListType + ")" + newLine);
		sb.append(EXIT + "-Sair" + newLine);

		return sb.toString();
	}
}
