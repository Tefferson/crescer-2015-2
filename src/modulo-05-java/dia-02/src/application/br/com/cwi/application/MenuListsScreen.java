package application.br.com.cwi.application;

public class MenuListsScreen implements IScreen{

	public final int ADD_MENU = 1;
	public final int REMOVE_MENU = 2;
	public final int LIST = 3;
	public final int CHANGE_LIST = 4;
	public final int WRITE_LIST = 5;
	public final int EXIT = 0;
	
	private Application application;

	public MenuListsScreen(Application application) {
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
			new AddListItemScreen(application).show();
			break;
		case REMOVE_MENU:
			new RemoveListItemScreen(application).show();
			break;
		case LIST:
			new ListListElementsScreen(application.getActualList().list()).show();
			break;
		case CHANGE_LIST:
			application.changeList();
			System.out.println("Lista trocada!!!");
			break;
		case WRITE_LIST:
			new WriteListToFileScreen(application).show();
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
		sb.append(WRITE_LIST + "-Gravar lista" + newLine);
		sb.append(EXIT + "-Sair" + newLine);

		return sb.toString();
	}
}
