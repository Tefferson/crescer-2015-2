package application.br.com.cwi.application;

import java.io.IOException;
import java.util.Scanner;

import list.br.com.cwi.list.linked.DoublyLinkedList;
import list.br.com.cwi.list.linked.ILinkedList;
import list.br.com.cwi.list.linked.LinkedList;
import sql.br.com.cwi.dao.ClienteDAO;
import sql.br.com.cwi.model.Cliente;

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

		Cliente cliente = new Cliente();

		cliente.setIdCliente(3L);
		cliente.setNmCliente("Tefferson");
		cliente.setNrCpf("019283asy");

		try {
			System.out.println(new ClienteDAO().listAll());
//			new ClienteDAO().insert(cliente);
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
