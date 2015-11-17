package br.com.cwi.application;

import java.util.Scanner;

import br.com.cwi.list.linked.LinkedList;
import br.com.cwi.application.screen.MenuScreen;
import br.com.cwi.list.linked.DoublyLinkedList;
import br.com.cwi.list.linked.ILinkedList;

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

	public static void main(String[] args) {

		try {
			new Application().start();
		} catch (Exception e) {
			System.out.println("Erro grave! Programa finalizado.");
		}
	}
}
