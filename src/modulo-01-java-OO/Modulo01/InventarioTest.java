
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    
    
}
