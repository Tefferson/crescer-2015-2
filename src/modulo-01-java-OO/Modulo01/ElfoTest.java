
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElfoTest
{
    @Test
    public void elfoNasceComFlechasENomeNaoNulo(){
        int flechas = 23;
        String nome = "elfo";
        Elfo elfo = new Elfo(nome, flechas);

        assertEquals(nome, elfo.getNome());
        assertNotNull(elfo.getNome());
        assertEquals(flechas, elfo.getFlechas());
        assertFalse(flechas != elfo.getFlechas());
        assertEquals(0, elfo.getExperiencia());
        assertFalse(0 != elfo.getExperiencia());

    }

    @Test
    public void elfoNasceApenasComNome(){
        String nome = "elfo";
        Elfo elfo = new Elfo(nome);

        assertEquals(nome, elfo.getNome());
        assertNotNull(elfo.getNome());
        assertEquals(42, elfo.getFlechas());
        assertFalse(42 != elfo.getFlechas());
        assertNotNull(elfo.getFlechas());
        assertEquals(0, elfo.getExperiencia());
        assertFalse(0 != elfo.getExperiencia());

    }

    @Test
    public void elfoAtiraFlechaNoDwarfPerdeFlechasEGanhaExperiencia(){
        int flechas = 23;
        String nome = "elfo";
        Elfo elfo = new Elfo(nome, flechas);        
        Dwarf dwarf = new Dwarf();
        elfo.atirarFlecha(dwarf);

        assertEquals(flechas-1, elfo.getFlechas());
        assertFalse(flechas-1 != elfo.getFlechas());
        assertEquals(1, elfo.getExperiencia());
        assertFalse(1 != elfo.getExperiencia());

    }

    @Test
    public void toStringDoElfoInformaNomeFlechasEExperiencia(){
        int flechas = 23;
        String nome = "elfo";
        Elfo elfo = new Elfo(nome, flechas);    

        String testeToString = nome+" possui "+flechas+
            " flechas e "+0+
            " níveis de experiência.";

        assertEquals(testeToString, elfo.toString());
        assertNotNull(elfo.toString());

    }

}
