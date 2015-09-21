
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
        Inventario inventario = dwarf.getInventario();
        inventario.adicionarItem(new Item("bala", 4));
        inventario.adicionarItem(new Item("bolo", 5));
        inventario.adicionarItem(new Item("salada", 1));         
        Inventario esperado = new Inventario();
        esperado.adicionarItem(new Item("bala", 10004));
        esperado.adicionarItem(new Item("bolo", 15005));
        esperado.adicionarItem(new Item("salada", 1001));
        dwarf.receberFlechada();
        dwarf.receberFlechada();

        dwarf.tentarSorte();

        assertEquals(esperado, inventario);
    }
    
    @Test
    public void dwarfTentaSorteEGanhaItensNegativos(){
        IrishDwarf dwarf = new IrishDwarf(null, new DataTerceiraEra(1,1,2000));
        Inventario inventario = dwarf.getInventario();
        inventario.adicionarItem(new Item("bala", 4));
        inventario.adicionarItem(new Item("bolo", -5));
        inventario.adicionarItem(new Item("salada", 1));         
        Inventario esperado = new Inventario();
        esperado.adicionarItem(new Item("bala", 10004));
        esperado.adicionarItem(new Item("bolo", -15005));
        esperado.adicionarItem(new Item("salada", 1001));
        dwarf.receberFlechada();
        dwarf.receberFlechada();

        dwarf.tentarSorte();

        assertEquals(esperado, inventario);
    }

    @Test
    public void dwarfTentaSorteEGanhaItensZerados(){
        IrishDwarf dwarf = new IrishDwarf(null, new DataTerceiraEra(1,1,2000));
        Inventario inventario = dwarf.getInventario();
        inventario.adicionarItem(new Item("bala", 0));
        inventario.adicionarItem(new Item("bolo", 0));
        inventario.adicionarItem(new Item("salada", -0));         
        Inventario esperado = new Inventario();
        esperado.adicionarItem(new Item("bala", 0));
        esperado.adicionarItem(new Item("bolo", -0));
        esperado.adicionarItem(new Item("salada", 0));
        dwarf.receberFlechada();
        dwarf.receberFlechada();

        dwarf.tentarSorte();

        assertEquals(esperado, inventario);
    }
    
    public void dwarfTentaSorteENãoGanhaItens(){
        IrishDwarf dwarf = new IrishDwarf(null, new DataTerceiraEra(1,1,2001));
        Inventario inventario = dwarf.getInventario();
        inventario.adicionarItem(new Item("bala", 3));
        inventario.adicionarItem(new Item("bolo", 2));
        inventario.adicionarItem(new Item("salada", 1));         
        Inventario esperado = new Inventario();
        esperado.adicionarItem(new Item("bala", 3));
        esperado.adicionarItem(new Item("bolo", 2));
        esperado.adicionarItem(new Item("salada", 1)); 
        dwarf.receberFlechada();
        dwarf.receberFlechada();

        dwarf.tentarSorte();

        assertEquals(esperado, inventario);
    }
}
