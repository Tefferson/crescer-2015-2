package application.br.com.cwi.application;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import io.br.com.cwi.io.FileIO;

public class WriteListToFileScreen implements IScreen {

	private List<String> list;
	private Scanner scanner;

	public WriteListToFileScreen(Application application) {
		this.list = application.getActualList().list();
		this.scanner = application.getScanner();
	}

	@Override

	public void show() {
		System.out.println(options());
		action();
		System.out.println("Arquivo gravado!");
	}

	@Override
	public void action() {

		scanner.nextLine();
		String path = scanner.nextLine();
		
		try {
			FileIO.writeLines(list, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String options() {
		return "Informe o caminho do arquivo";
	}

}
