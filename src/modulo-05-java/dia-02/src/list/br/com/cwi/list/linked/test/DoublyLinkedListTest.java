package list.br.com.cwi.list.linked.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import list.br.com.cwi.list.linked.DoublyLinkedList;


public class DoublyLinkedListTest {

	@Test
    public void adicionaPrimeiro() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("primeiro");

        assertEquals("primeiro", list.list().get(0));
    }
	
	@Test
    public void retornaOPrimeiro() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("segundo");
        list.addFirst("primeiro");

        assertEquals("primeiro", list.getFirst());
    }
	
	@Test
    public void retornaOUltimo() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("�ltimo");
        list.addFirst("segundo");
        list.addFirst("primeiro");

        assertEquals("�ltimo", list.getLast());
    }
	
	@Test
    public void listaEstaVazia() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("�ltimo");
        list.addFirst("segundo");
        list.removeFirst();
        list.removeFirst();

        assertEquals(true, list.isEmpty());
    }
	
	@Test
    public void listaElementos() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("�ltimo");
        list.addFirst("segundo");
        list.addFirst("primeiro");

        ArrayList<String> esperado = new ArrayList<String>();
        esperado.add("primeiro");
        esperado.add("segundo");
        esperado.add("�ltimo");
        
        assertEquals(esperado, list.list());
    }
	
	@Test
    public void adicionaUltimo() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("segundo");
        list.addFirst("primeiro");
        list.addLast("�ltimo");

        assertEquals("�ltimo", list.list().get(2));
    }
	
	@Test
    public void removeOPrimeiro() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("segundo");
        list.addFirst("primeiro");
        list.addLast("�ltimo");
        
        list.removeFirst();

        assertEquals("segundo", list.getFirst());
    }
	
	@Test
	public void insereNaTerceiraPosicao() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("quarto");
        list.addFirst("segundo");
        list.addFirst("primeiro");
        list.addLast("�ltimo");
        
        list.add(2, "terceiro");

        assertEquals("terceiro", list.list().get(2));
    }
	
	@Test
    public void insereNaUltimaPosicao() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("terceiro");
        list.addFirst("segundo");
        list.addFirst("primeiro");
        
        list.add(2, "�ltimo");

        assertEquals("�ltimo", list.list().get(2));
    }
	
	public void removeDaTerceiraPosicao() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("terceiro");
        list.addFirst("segundo");
        list.addFirst("primeiro");
        list.addLast("�ltimo");
        
        list.remove(2);

        assertEquals("�ltimo", list.list().get(2));
    }
	
	@Test
    public void removeDaPrimeiraPosicao() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("terceiro");
        list.addFirst("segundo");
        list.addFirst("primeiro");
        
        list.remove(0);

        assertEquals("segundo", list.getFirst());
    }
	
	
}
