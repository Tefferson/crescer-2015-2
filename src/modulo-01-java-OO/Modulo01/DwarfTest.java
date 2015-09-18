import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DwarfTest
{
    @Test
    public void dwarfNasceCom110DeVida() {
        Dwarf dwarf = new Dwarf("Dwarf");
        assertEquals(110, dwarf.getVida());
        assertNotSame(120, dwarf.getVida());
    }

    @Test
    public void dwarfRecebeFlechadaEPerde10DeVida() {
        Dwarf dwarf = new Dwarf("Dwarf");
        dwarf.receberFlechada();
        assertEquals(100, dwarf.getVida());
        assertNotSame(110, dwarf.getVida());
    }

    @Test
    public void dwarfRecebeFlechaEPerde10Vida() {
        // AAA
        // Arrange
        Dwarf gimli = new Dwarf("Dwarf");
        int vidaEsperada = 100;
        // Act
        gimli.receberFlechada();
        // Assert
        assertEquals(vidaEsperada, gimli.getVida());
    }

    @Test
    public void dwarfRecebeFlecha11VezesEVidaÉ0() {
        Dwarf gimli = new Dwarf("Dwarf");
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
        Dwarf gimli = new Dwarf("Gimli");
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
        Dwarf dwarf = new Dwarf("Dwarf");
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
        Dwarf dwarf = new Dwarf("Dwarf");
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
        Dwarf dwarf = new Dwarf("Dwarf");
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
    public void dwarfNasceComNomeEmAnoNaoBissextoGetNumeroSorte(){
        Dwarf dwarf = new Dwarf("Dwarf");
        assertEquals(101.0, dwarf.getNumeroSorte(), 0.5);
    }

    @Test
    public void dwarfNasceComNomeSeixasEmAnoNaoBissextoGetNumeroSorte(){
        Dwarf dwarf = new Dwarf("Seixas");
        assertEquals(33.0, dwarf.getNumeroSorte(), 0.5);
    }

    @Test
    public void dwarfNasceComNomeEmAnoBissextoGetNumeroSorte(){
        Dwarf dwarf = new Dwarf("Dwarf");
        assertEquals(101.0, dwarf.getNumeroSorte(), 0.5);
    }

    @Test
    public void dwarfNasceComNomeEmAnoBissextoERecebe3FlechadasGetNumeroSorte(){
        Dwarf dwarf = new Dwarf("Dwarf", new DataTerceiraEra(1,1,2000));
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        assertEquals(-3333.0, dwarf.getNumeroSorte(), 0.5);
    }

    @Test
    public void dwarfNasceComNomeEmAnoBissextoERecebe7FlechadasGetNumeroSorte(){
        Dwarf dwarf = new Dwarf("Dwarf", new DataTerceiraEra(1,1,2000));
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        assertEquals(-3333, dwarf.getNumeroSorte(), 0.5);
    }

    @Test
    public void dwarfMortoNãoDeveVida(){
        Dwarf dwarf = new Dwarf("Dwarf");
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
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        assertEquals(0, dwarf.getVida());
    }

    @Test
    public void dwarfNasceEmAnoBissextoRecebe2FlechadasEDepoisNãoPerdeMaisVidaPorFlechada(){
        Dwarf dwarf = new Dwarf("Dwarf", new DataTerceiraEra(1,1,2000));
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        assertEquals(90, dwarf.getVida());        
    }

    @Test
    public void dwarfNasceEmAnoBissextoEGanha8DeExperiência(){
        Dwarf dwarf = new Dwarf("Dwarf", new DataTerceiraEra(1,1,2000));
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        dwarf.receberFlechada();
        assertEquals(8, dwarf.getExperiencia());        
    }
}
