
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ElfoVerdeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ElfoVerdeTest
{
    @Test
    public void elfoAtiraFlechaEmDwarfEGanha2NiveisDeExperiencia() {
        // Arrange
        Elfo elfo = new ElfoVerde("Elfo caçador");
        Dwarf balin = new Dwarf("Barílio");
        int qtdFlechasEsperada = 41;
        int experienciaEsperada = 2;
        int vidaEsperada = 100;
        // Act
        elfo.atirarFlecha(balin);
        // Assert
        assertEquals(qtdFlechasEsperada, elfo.getFlechas());
        assertEquals(experienciaEsperada, elfo.getExperiencia());
        assertEquals(vidaEsperada, balin.getVida());
    }

    @Test
    public void elfoAtiraFlechaEmIrishDwarfEGanha2NiveisDeExperiencia() {
        // Arrange
        Elfo elfo = new ElfoVerde("Elfo caçador");
        Dwarf balin = new IrishDwarf("Barílio");
        int qtdFlechasEsperada = 41;
        int experienciaEsperada = 2;
        int vidaEsperada = 100;
        // Act
        elfo.atirarFlecha(balin);
        // Assert
        assertEquals(qtdFlechasEsperada, elfo.getFlechas());
        assertEquals(experienciaEsperada, elfo.getExperiencia());
        assertEquals(vidaEsperada, balin.getVida());
    }

    @Test
    public void elfoAdicionaItemArcoEFlechaDeVidro(){
        Elfo elfo = new ElfoVerde("elfo");
        Inventario esperado = new Inventario();
        esperado.adicionarItem(new Item("Arco e Flecha de Vidro",1));
        elfo.adicionarItem(new Item("Arco e Flecha de Vidro",1));

        assertEquals(esperado, elfo.getInventario());
    }

    @Test
    public void elfoNãoConsegueAdicionarItem(){
        Elfo elfo = new ElfoVerde("elfo");
        Inventario esperado = new Inventario();

        elfo.adicionarItem(new Item("Arco Simples",1));

        assertEquals(esperado, elfo.getInventario());
    }

    @Test
    public void elfoNãoConsegueAdicionarItemComdescriçãoNula(){
        Elfo elfo = new ElfoVerde("elfo");
        Inventario esperado = new Inventario();
        elfo.adicionarItem(new Item("Arco e Flecha de Vidro",1));
        esperado.adicionarItem(new Item("Arco e Flecha de Vidro",1));

        assertEquals(esperado, elfo.getInventario());
    }

    @Test
    public void elfoNãoConsegueAdicionaItemNulo(){
        Elfo elfo = new ElfoVerde("elfo");
        Inventario esperado = new Inventario();
        elfo.adicionarItem(null);

        assertEquals(esperado, elfo.getInventario());
    }
}

