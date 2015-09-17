import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DwarfTest
{
    @Test
    public void dwarfNasceCom110DeVida() {
        Dwarf dwarf = new Dwarf();
        assertEquals(110, dwarf.getVida());
        assertNotSame(120, dwarf.getVida());
    }

    @Test
    public void dwarfRecebeFlechadaEPerde10DeVida() {
        Dwarf dwarf = new Dwarf();
        dwarf.receberFlechada();
        assertEquals(100, dwarf.getVida());
        assertNotSame(110, dwarf.getVida());
    }

    @Test
    public void dwarfRecebeFlechaEPerde10Vida() {
        // AAA
        // Arrange
        Dwarf gimli = new Dwarf();
        int vidaEsperada = 100;
        // Act
        gimli.receberFlechada();
        // Assert
        assertEquals(vidaEsperada, gimli.getVida());
    }

    @Test
    public void dwarfRecebeFlecha11VezesEVidaÉ0() {
        Dwarf gimli = new Dwarf();
        int vidaEsperada = 0;
        // Act
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        // Assert
        assertEquals(vidaEsperada, gimli.getVida());
    }

    @Test
    public void dwarfRecebeFlecha7VezesEVidaÉ40() {
        Dwarf gimli = new Dwarf();
        int vidaEsperada = 40;
        // Act
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        gimli.receberFlechada();
        // Assert
        assertEquals(vidaEsperada, gimli.getVida());
    }

    @Test
    public void dwarfMorre(){
        Dwarf dwarf = new Dwarf();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        assertEquals(Status.MORTO, dwarf.getStatus());
    }
    
    @Test
    public void dwarfNasceVivo(){
        Dwarf dwarf = new Dwarf();
        assertEquals(Status.VIVO, dwarf.getStatus());
        
    }
    
    @Test 
    public void dwarfNasceComNomeEExperiencia0(){
        Dwarf dwarf = new Dwarf("Dwarf");
        assertEquals("Dwarf", dwarf.getNome());
        assertEquals(0, dwarf.getExperiencia());
    }
    
    
}
