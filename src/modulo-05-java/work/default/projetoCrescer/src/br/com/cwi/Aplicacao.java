package br.com.cwi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        String outra = scanner.nextLine();
        System.out.println(outra);

        LinkedList linkedList = new LinkedList();

        linkedList.addFirst("primeiro");
        linkedList.addFirst("segundo");
        linkedList.addFirst("terceiro");
        linkedList.addLast("último");
        // System.out.println(linkedList.list());

        LinkedList listaNova = new LinkedList() {

            @Override
            public List<String> list() {
                ArrayList<String> lista = new ArrayList<>();
                Node node = first;
                int indice = 0;
                while (node != null) {
                    lista.add(indice + ":" + node);
                    node = node.getNext();
                    indice++;
                }
                return lista;
            }
        };

        listaNova.addFirst("primeiro");
        listaNova.addFirst("segundo");
        listaNova.addFirst("terceiro");
        listaNova.addLast("último");

        System.out.println(listaNova.list());
    }
}
