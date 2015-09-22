

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
}
