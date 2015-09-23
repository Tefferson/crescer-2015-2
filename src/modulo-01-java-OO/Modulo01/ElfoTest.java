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
        Dwarf balin = new Dwarf("Barílio");
        int qtdFlechasEsperada = 41;
        int experienciaEsperada = 1;
        int vidaEsperada = 100;
        // Act
        elfo.atirarFlecha(balin);
        // Assert
        assertEquals(qtdFlechasEsperada, elfo.getFlechas());
        assertEquals(experienciaEsperada, elfo.getExperiencia());
        assertEquals(vidaEsperada, balin.getVida(), 0);
    }

    @Test
    public void elfoAtiraEmDoisAnoes() {
        // Arrange
        Elfo elfo = new Elfo("Elfo caçador");
        Dwarf balin = new Dwarf("Sueco");
        Dwarf filhoDeBalin = new Dwarf("SuecoFilho");
        int qtdFlechasEsperada = 40;
        int experienciaEsperada = 2;
        int vidaEsperada = 100;
        // Act
        elfo.atirarFlecha(balin);
        elfo.atirarFlecha(filhoDeBalin);
        // Assert
        assertEquals(qtdFlechasEsperada, elfo.getFlechas());
        assertEquals(experienciaEsperada, elfo.getExperiencia());
        assertEquals(vidaEsperada, balin.getVida(), 0);
        assertEquals(vidaEsperada, filhoDeBalin.getVida(), 0);
    }

    @Test
    public void doisElfosAtiramEmDoisDwarves() {
        // Arrange
        Elfo elfo1 = new Elfo("Elfo caçador");
        Elfo elfo2 = new Elfo("Segundo elfo");
        Dwarf balin = new Dwarf("Balin");
        Dwarf filhoDeBalin = new Dwarf("Balin Filho");
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
        assertEquals(vidaEsperada, balin.getVida(), 0);
        assertEquals(vidaEsperada, filhoDeBalin.getVida(), 0);
    }

    @Test
    public void elfoComNomeEFlechasInformadasToString() {
        Elfo elfo1 = new Elfo("Acabaram os nomes", 1000);
        String textoEsperado = "Acabaram os nomes possui 1000 flechas e 0 níveis de experiência.";

        assertEquals(textoEsperado, elfo1.toString());
    }

    @Test
    public void elfoComUmaFlechaInformadaToString() {
        Elfo elfo1 = new Elfo("Monoflecha", 1);
        String textoEsperado = "Monoflecha possui 1 flecha e 0 níveis de experiência.";
        assertEquals(textoEsperado, elfo1.toString());
    }

    @Test
    public void elfoComUmDeExperienciaToString() {
        Elfo elfo1 = new Elfo(null);
        elfo1.atirarFlecha(new Dwarf("Elfo Genérico"));
        String textoEsperado = "null possui 41 flechas e 1 nível de experiência.";
        assertEquals(textoEsperado, elfo1.toString());
    }

    @Test
    public void contadorDoElfoÉ5() {
        Elfo.resetContagem();
        Elfo elfo1 = new Elfo("elfo");
        Elfo elfo2 = new Elfo("elfo");
        Elfo elfo3 = new Elfo("elfo");
        Elfo elfo4 = new Elfo("elfo");
        Elfo elfo5 = new Elfo("elfo");

        int esperado = 5;

        assertEquals(esperado, elfo1.getContagem());
        assertEquals(elfo5.getContagem(), elfo1.getContagem());
    }
}