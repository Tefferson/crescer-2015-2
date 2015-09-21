
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class IrishDwarfTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class IrishDwarfTest
{
    @Test
    public void dwarfTentaSorteEGanhaItens(){
        IrishDwarf dwarf = new IrishDwarf(null, new DataTerceiraEra(1,1,2000));
        Item bala = new Item("bala", 3);
        Item bolo = new Item("bolo", 2);
        Item salada = new Item("salada", 1);
        Inventario inventario = dwarf.getInventario();
        inventario.adicionarItem(bala);
        inventario.adicionarItem(bolo);
        inventario.adicionarItem(salada); 
        dwarf.receberFlechada();
        dwarf.receberFlechada();

        dwarf.tentarSorte();

        assertEquals(1009,bala.getQuantidade());
        assertEquals(1005,bolo.getQuantidade());
        assertEquals(1002,salada.getQuantidade());
    }

    @Test
    public void dwarfTentaSorteENãoGanhaItens(){
        IrishDwarf dwarf = new IrishDwarf(null, new DataTerceiraEra(1,1,2001));
        Item bala = new Item("bala", 3);
        Item bolo = new Item("bolo", 2);
        Item salada = new Item("salada", 1);
        Inventario inventario = dwarf.getInventario();
        inventario.adicionarItem(bala);
        inventario.adicionarItem(bolo);
        inventario.adicionarItem(salada); 
        dwarf.receberFlechada();
        dwarf.receberFlechada();

        dwarf.tentarSorte();

        assertEquals(3,bala.getQuantidade());
        assertEquals(2,bolo.getQuantidade());
        assertEquals(1,salada.getQuantidade());
    }
}
