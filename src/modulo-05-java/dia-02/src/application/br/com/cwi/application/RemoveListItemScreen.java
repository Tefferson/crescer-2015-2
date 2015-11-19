package application.br.com.cwi.application;

import java.util.Scanner;

import list.br.com.cwi.list.linked.ILinkedList;

public class RemoveListItemScreen implements IScreen {

	public final int REMOVE = 1;
	public final int REMOVE_FIRST = 2;

	private Scanner scanner;
	private ILinkedList<String> actualList;

	public RemoveListItemScreen(Application application) {
		this.scanner = application.getScanner();
		this.actualList = application.getActualList();
	}

	@Override
	public void show() {
		System.out.println(options());
		action();
	}

	@Override
	public void action() {

		int option = scanner.nextInt();

		switch (option) {
		case REMOVE:
			System.out.println("Informe a posição:");
			int index = scanner.nextInt();
			actualList.remove(index);
			break;
		case REMOVE_FIRST:
			actualList.removeFirst();
			break;
		}
	}

	@Override
	public String options() {

		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		sb.append(REMOVE + "-Remover" + newLine);
		sb.append(REMOVE_FIRST + "-Remover primeiro" + newLine);

		return sb.toString();
	}

}
