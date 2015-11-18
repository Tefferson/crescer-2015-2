package application.br.com.cwi.application;

import java.io.IOException;
import java.util.Scanner;

import list.br.com.cwi.list.linked.DoublyLinkedList;
import list.br.com.cwi.list.linked.ILinkedList;
import list.br.com.cwi.list.linked.LinkedList;
import sql.br.com.cwi.dao.DAOFactory;
import sql.br.com.cwi.dao.IDAO;

public class Application {
	private LinkedList<String> linkedList;
	private DoublyLinkedList<String> doublyLinkedList;
	private ILinkedList<String> actualList;
	private Scanner scanner;

	private void start() {

		linkedList = new LinkedList<>();
		doublyLinkedList = new DoublyLinkedList<>();
		actualList = linkedList;
		scanner = new Scanner(System.in);

		while (true) {
			new MenuScreen(this).show();

		}
	}

	public void changeList() {
		actualList = actualList instanceof LinkedList ? doublyLinkedList : linkedList;
	}

	public ILinkedList<String> getActualList() {
		return this.actualList;
	}

	public Scanner getScanner() {
		return this.scanner;
	}

	public static void main(String[] args) throws IOException {

		IDAO<?> clientDAO = DAOFactory.createClienteDAO();
		
		try {
			System.out.println(clientDAO.listAll());
			
		} catch (Exception e) {

			System.out.println("Erro sql!!!");
		}

		// try {
		// new Application().start();
		// } catch (Exception e) {
		// System.out.println("Erro grave! Programa finalizado.");
		// }

	}
}
