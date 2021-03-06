package list.br.com.cwi.list.linked.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import list.br.com.cwi.list.exception.EmptyListException;
import list.br.com.cwi.list.linked.LinkedList;

public class LinkedListTest {

    @Test
    public void adicionarUmNodoNalistaVazia() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("primeiro");

        assertEquals("primeiro", linkedList.getFirst());
        assertEquals("primeiro", linkedList.getLast());
    }

    @Test
    public void adicionarTresNodosNalistaVazia() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("primeiro");
        linkedList.addFirst("segundo");
        linkedList.addFirst("terceiro");

        assertEquals("terceiro", linkedList.getFirst());
        assertEquals("primeiro", linkedList.getLast());
    }

    @Test
    public void adicionarUltimo() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("primeiro");
        linkedList.addFirst("segundo");
        linkedList.addLast("último");

        assertEquals("segundo", linkedList.getFirst());
        assertEquals("último", linkedList.getLast());
    }

    @Test
    public void adicionarApenasUltimo() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addLast("último");

        assertEquals("último", linkedList.getFirst());
        assertEquals("último", linkedList.getLast());
    }

    @Test
    public void listaTresNodos() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addLast("último");
        linkedList.addFirst("primeiro");
        linkedList.addFirst("segundo");

        assertEquals(3, linkedList.list().size());
    }

    @Test
    public void listaUmNodo() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("primeiro");

        assertEquals(1, linkedList.list().size());
    }

    @Test
    public void listaNenhumNodo() {
        LinkedList<String> linkedList = new LinkedList<String>();

        assertEquals(0, linkedList.list().size());
    }

    @Test
    public void removerPrimeiro() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addLast("último");
        linkedList.addFirst("primeiro");
        linkedList.addFirst("segundo");

        linkedList.removeFirst();

        assertEquals(2, linkedList.list().size());
        assertEquals("primeiro", linkedList.getFirst());
    }

    @Test
    public void removerPrimeiroEmListaVazia() {
        LinkedList<String> linkedList = new LinkedList<String>();

        try {
            linkedList.removeFirst();
            assertEquals(0, linkedList.list().size());
            assertEquals(null, linkedList.getFirst());
        } catch (EmptyListException e) {
            assertEquals(0, linkedList.list().size());
        }
    }

    @Test
    public void insereNaPosicaoZero() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("Valor");
        linkedList.addFirst("Valor3");
        linkedList.addFirst("Valor4");
        linkedList.add(0, "Valor2");

        assertEquals(4, linkedList.list().size());
        assertEquals("Valor2", linkedList.list().get(0));
    }

    @Test
    public void insereNaPosicaoUm() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("Valor");
        linkedList.addFirst("Valor3");
        linkedList.addFirst("Valor4");
        linkedList.add(1, "Valor2");

        assertEquals(4, linkedList.list().size());
        assertEquals("Valor2", linkedList.list().get(1));
    }

    @Test
    public void insereNaListaVazia() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add(1, "Valor2");

        assertEquals(1, linkedList.list().size());
        assertEquals("Valor2", linkedList.list().get(0));
    }

    @Test
    public void removeTerceiroNodo() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("Valor");
        linkedList.addFirst("Valor3");
        linkedList.addFirst("Valor4");
        linkedList.add(1, "Valor2");
        linkedList.remove(3);

        assertEquals(3, linkedList.list().size());
        assertEquals("Valor3", linkedList.list().get(2));
    }

    @Test
    public void removePrimeiroNodo() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("Valor");
        linkedList.addFirst("Valor3");
        linkedList.addFirst("Valor4");
        linkedList.add(1, "Valor2");
        linkedList.remove(0);

        assertEquals(3, linkedList.list().size());
        assertEquals("Valor2", linkedList.list().get(0));
    }

    @Test
    public void removeDaListaVazia() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.remove(0);

        assertEquals(0, linkedList.list().size());
    }

    @Test
    public void removeDePosicaoInexistente() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("Valor");
        linkedList.addFirst("Valor3");
        linkedList.addFirst("Valor4");
        linkedList.add(1, "Valor2");
        linkedList.remove(6);

        assertEquals(4, linkedList.list().size());
        assertEquals("Valor4", linkedList.list().get(0));
    }

    @Test
    public void adicionaEmPosicaoInexistente() {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.addFirst("Valor");
        linkedList.addFirst("Valor3");
        linkedList.addFirst("Valor4");
        linkedList.add(1, "Valor2");
        linkedList.remove(6);

        assertEquals(4, linkedList.list().size());
        assertEquals("Valor4", linkedList.list().get(0));
    }

}
