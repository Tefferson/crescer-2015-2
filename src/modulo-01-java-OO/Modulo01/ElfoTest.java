import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElfoTest
{
    @Test
    public void elfoCriadoComNomeNasceCom42Flechas() {
        Elfo celeborn = new Elfo("Celeborn");
        assertEquals(42, celeborn.getFlechas());
    }

    @Test
    public void elfoCriadoComNomeNull() {
        Elfo elrond = new Elfo(null);
        assertNull(elrond.getNome());
    }

    @Test
    public void elfoCriadoNasceCom0DeExperiencia() {
        Elfo legolas = new Elfo("Legolas II");
        assertEquals(0, legolas.getExperiencia());
    }

    @Test
    public void elfoCriadoComNomeVazio() {
        Elfo elrond = new Elfo("");
        assertEquals("", elrond.getNome());
    }

    @Test
    public void elfoCriadoComNomeEPoucasFlechas() {
        Elfo elfoHumilde = new Elfo("Elfo Humilde", 2);
        assertEquals("Elfo Humilde", elfoHumilde.getNome());
        assertEquals(2, elfoHumilde.getFlechas());
    }

    @Test
    public void elfoCriadoComNomeEComMuitasFlechas() {
        Elfo elfoHumilde = new Elfo("Elfo Bacana", 349857);
        assertEquals("Elfo Bacana", elfoHumilde.getNome());
        assertEquals(349857, elfoHumilde.getFlechas());
    }

    @Test
    public void elfoCriadoComNomeEDevendoFlechas() {
        Elfo elfoHumilde = new Elfo("Elfo Devedor", -349857);
        assertEquals("Elfo Devedor", elfoHumilde.getNome());
        assertEquals(-349857, elfoHumilde.getFlechas());
    }

    @Test
    public void elfoAtiraFlechaEmDwarf() {
        // Arrange
        Elfo elfo = new Elfo("Elfo caçador");
        Dwarf balin = new Dwarf();
        int qtdFlechasEsperada = 41;
        int experienciaEsperada = 1;
        int vidaEsperada = 100;
        // Act
        elfo.atirarFlecha(balin);
        // Assert
        assertEquals(qtdFlechasEsperada, elfo.getFlechas());
        assertEquals(experienciaEsperada, elfo.getExperiencia());
        assertEquals(vidaEsperada, balin.getVida());
    }

    @Test
    public void elfoAtiraEmDoisAnoes() {
        // Arrange
        Elfo elfo = new Elfo("Elfo caçador");
        Dwarf balin = new Dwarf();
        Dwarf filhoDeBalin = new Dwarf();
        int qtdFlechasEsperada = 40;
        int experienciaEsperada = 2;
        int vidaEsperada = 100;
        // Act
        elfo.atirarFlecha(balin);
        elfo.atirarFlecha(filhoDeBalin);
        // Assert
        assertEquals(qtdFlechasEsperada, elfo.getFlechas());
        assertEquals(experienciaEsperada, elfo.getExperiencia());
        assertEquals(vidaEsperada, balin.getVida());
        assertEquals(vidaEsperada, filhoDeBalin.getVida());
    }

    @Test
    public void doisElfosAtiramEmDoisDwarves() {
        // Arrange
        Elfo elfo1 = new Elfo("Elfo caçador");
        Elfo elfo2 = new Elfo("Segundo elfo");
        Dwarf balin = new Dwarf();
        Dwarf filhoDeBalin = new Dwarf();
        int qtdFlechasEsperada = 40;
        int experienciaEsperada = 2;
        int vidaEsperada = 90;
        // Act
        elfo1.atirarFlecha(balin);
        elfo1.atirarFlecha(filhoDeBalin);
        elfo2.atirarFlecha(balin);
        elfo2.atirarFlecha(filhoDeBalin);
        // Assert
        assertEquals(qtdFlechasEsperada, elfo1.getFlechas());
        assertEquals(experienciaEsperada, elfo1.getExperiencia());
        assertEquals(qtdFlechasEsperada, elfo2.getFlechas());
        assertEquals(experienciaEsperada, elfo2.getExperiencia());
        assertEquals(vidaEsperada, balin.getVida());
        assertEquals(vidaEsperada, filhoDeBalin.getVida());
    }

}
