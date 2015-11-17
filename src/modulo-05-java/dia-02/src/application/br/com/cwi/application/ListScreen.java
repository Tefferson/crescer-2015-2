package application.br.com.cwi.application;

import java.util.List;

public class ListScreen implements IScreen {

	private List<String> list;

	public ListScreen(List<String> list) {
		this.list = list;
	}

	@Override

	public void show() {
		System.out.println(options());
	}

	@Override
	public void action() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String options() {
		StringBuilder sb = new StringBuilder();

		int size = list.size();
		String newLine = System.getProperty("line.separator");

		for (int i = 0; i < size; i++) {
			sb.append(i + ": " + list.get(i) + newLine);
		}
		
		return sb.toString();
	}

}
