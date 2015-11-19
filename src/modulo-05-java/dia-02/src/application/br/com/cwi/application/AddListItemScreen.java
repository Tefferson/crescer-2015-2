package application.br.com.cwi.application;

import java.util.Scanner;

import list.br.com.cwi.list.linked.ILinkedList;

public class AddListItemScreen implements IScreen{
	
	public final int ADD = 1;
	public final int ADD_FIRST = 2;
	public final int ADD_LAST = 3;

	private Scanner scanner;
	private ILinkedList<String> actualList;
	
	public AddListItemScreen(Application application){
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
		scanner.nextLine();
		
		System.out.println("Informe o valor:");
		
		String value = scanner.nextLine();

		switch (option) {
		case ADD:
			System.out.println("Informe a posição:");
			int index = scanner.nextInt();
			actualList.add(index, value);
			break;
		case ADD_FIRST:
			actualList.addFirst(value);
			break;
		case ADD_LAST:
			actualList.addLast(value);
			break;
		}
	}

	@Override
	public String options() {
		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		sb.append(ADD + "-Adicionar" + newLine);
		sb.append(ADD_FIRST + "-Adicionar primeiro" + newLine);
		sb.append(ADD_LAST + "-Adicionar último" + newLine);

		return sb.toString();
	}

}
