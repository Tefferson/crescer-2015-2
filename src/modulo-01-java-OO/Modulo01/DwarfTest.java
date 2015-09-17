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

    @Test
    public void dwarfNasceSemNomeNaDataDefault(){
        Dwarf dwarf = new Dwarf();
        DataTerceiraEra dataNascimento = dwarf.getDataNascimento();
        assertEquals(1, dataNascimento.getDia());
        assertEquals(1, dataNascimento.getMes());
        assertEquals(1, dataNascimento.getAno());
    }

    @Test
    public void dwarfNasceComNomeDia2Do6DeMenos23(){
        Dwarf dwarf = new Dwarf("Anão Genérico", new DataTerceiraEra(2,6,-23));
        DataTerceiraEra dataNascimento = dwarf.getDataNascimento();
        assertEquals(2, dataNascimento.getDia());
        assertEquals(6, dataNascimento.getMes());
        assertEquals(-23, dataNascimento.getAno());
    }

    @Test
    public void dwarfNasceEmAnoNaoBissextoGetNumeroSorte(){
        Dwarf dwarf = new Dwarf("Dwarf");
        assertEquals(101.0, dwarf.getNumeroSorte(), 0.5);
    }

}
