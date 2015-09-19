
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class InventarioTest
{
    @Test
    public void umItemAdicionadoAoInventário(){
        Item itemEsperado = new Item("bolo", 2);
        Inventario inventario = new Inventario();

        inventario.adicionarItem(new Item("bolo", 2));

        assertEquals(itemEsperado, inventario.getItens().get(0));
    }

    @Test
    public void trêsItensAdicionadosAoInventário(){
        Item bolo = new Item("bolo", 2);
        Item bala = new Item("bala", 3);
        Item salada = new Item("salada", 1);
        Inventario inventario = new Inventario();

        inventario.adicionarItem(new Item("bolo", 2));
        inventario.adicionarItem(new Item("bala", 3));
        inventario.adicionarItem(new Item("salada", 1));
        inventario.perderItem(bala);

        assertNotSame(bala, inventario.getItens().get(1));
    }

    @Test
    public void nomesDosItensSeparadosPorVírgula(){
        Inventario inventario = new Inventario();
        String esperado = "bolo,bala,salada";

        inventario.adicionarItem(new Item("bolo", 2));
        inventario.adicionarItem(new Item("bala", 3));
        inventario.adicionarItem(new Item("salada", 1));

        assertEquals(esperado, inventario.getDescricoesItens());
    }

    @Test
    public void balaÉOItemComMaiorQuantidade(){
        Inventario inventario = new Inventario();        
        Item bala = new Item("bala", 3);
        Item bolo = new Item("bolo", 2);
        Item salada = new Item("salada", 1);
        inventario.adicionarItem(bala);
        inventario.adicionarItem(bolo);
        inventario.adicionarItem(salada); 

        assertEquals(bala, inventario.getItemComMaiorQuantidade());
    }

    @Test
    public void invetárioCom3ItensÉOrdenado(){
        Inventario inventario = new Inventario();        
        Item bala = new Item("bala", 3);
        Item bolo = new Item("bolo", 2);
        Item salada = new Item("salada", 1);

        ArrayList<Item> itens = new ArrayList<>();
        inventario.adicionarItem(bala);
        inventario.adicionarItem(salada); 
        inventario.adicionarItem(bolo);

        inventario.ordenarItens();
        itens = inventario.getItens();

        assertEquals(salada, itens.get(0));
        assertEquals(bolo, itens.get(1));
        assertEquals(bala, itens.get(2));
    }

    @Test
    public void invetárioCom5ItensÉOrdenado(){
        Inventario inventario = new Inventario();   
        Item cafe = new Item("café", 5);
        Item bolacha = new Item("bolacha", 17);     
        Item bala = new Item("bala", 3);
        Item bolo = new Item("bolo", -23);
        Item salada = new Item("salada", 1);

        ArrayList<Item> itens = new ArrayList<>();
        inventario.adicionarItem(bala);
        inventario.adicionarItem(salada); 
        inventario.adicionarItem(cafe);
        inventario.adicionarItem(bolacha); 
        inventario.adicionarItem(bolo);

        inventario.ordenarItens();
        itens = inventario.getItens();

        assertEquals(bolo, itens.get(0));
        assertEquals(salada, itens.get(1));
        assertEquals(bala, itens.get(2));
        assertEquals(cafe, itens.get(3));
        assertEquals(bolacha, itens.get(4));
    }

    @Test
    public void invetárioCom5ItensAlgunsComQuantidadesIguaisÉOrdenado(){
        Inventario inventario = new Inventario();   
        Item cafe = new Item("café", 5);
        Item bolacha = new Item("bolacha", 17);     
        Item bala = new Item("bala", 5);
        Item bolo = new Item("bolo", 1);
        Item salada = new Item("salada", 1);

        ArrayList<Item> itens = new ArrayList<>();
        inventario.adicionarItem(bala);
        inventario.adicionarItem(salada); 
        inventario.adicionarItem(cafe);
        inventario.adicionarItem(bolacha); 
        inventario.adicionarItem(bolo);

        inventario.ordenarItens();
        itens = inventario.getItens();

        assertEquals(bolo, itens.get(0));
        assertEquals(salada, itens.get(1));
        assertEquals(cafe, itens.get(2));
        assertEquals(bala, itens.get(3));
        assertEquals(bolacha, itens.get(4));
    }
}
