
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
    public void elfoNasceComFlechasENomeNulo(){
        int flechas = 23;
        String nome = null;
        Elfo elfo = new Elfo(nome, flechas);

        assertEquals(nome, elfo.getNome());
        assertNull(elfo.getNome());
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
        String nome = "elfo", nome2 = "Ç~~'/*;><==$%¨$¨&#@!-_=+-*()";
        Elfo elfo = new Elfo(nome, flechas);    
        Elfo elfo2 = new Elfo(nome2);     
        Elfo elfo3 = new Elfo(null);    
        Dwarf dwarf = new Dwarf();
        Dwarf dwarf2 = new Dwarf();

        String testeToString = nome+" possui "+flechas+
            " flechas e "+0+
            " níveis de experiência.";

        assertNotNull(elfo.toString());
        assertEquals(testeToString, elfo.toString());
        
        testeToString = nome2+" possui "+42+
            " flechas e "+0+
            " níveis de experiência.";
            
        assertNotNull(elfo2.toString());
        assertEquals(testeToString, elfo2.toString());
        
        testeToString = null+" possui "+42+
            " flechas e "+0+
            " níveis de experiência.";
            
        assertNotNull(elfo3.toString());
        assertEquals(testeToString, elfo3.toString());
        
        elfo.atirarFlecha(dwarf);
        
        testeToString = nome+" possui "+22+
            " flechas e "+1+
            " níveis de experiência.";
            
        assertNotNull(elfo.toString());
        assertEquals(testeToString, elfo.toString());
        
        elfo.atirarFlecha(dwarf);
        elfo2.atirarFlecha(dwarf);
        elfo.atirarFlecha(dwarf2);
        
        testeToString = nome+" possui "+20+
            " flechas e "+3+
            " níveis de experiência.";
            
        assertNotNull(elfo.toString());
        assertEquals(testeToString, elfo.toString());
        
        testeToString = nome2+" possui "+41+
            " flechas e "+1+
            " níveis de experiência.";
            
        assertNotNull(elfo2.toString());
        assertEquals(testeToString, elfo2.toString());
        
        elfo3.atirarFlecha(dwarf2);
        
        testeToString = null+" possui "+41+
            " flechas e "+1+
            " níveis de experiência.";
            
        assertNotNull(elfo3.toString());
        assertEquals(testeToString, elfo3.toString());

    }

    @Test
    public void elfoNasceSemReceberFlechasENomeNulo(){
        String nome = null;
        Elfo elfo = new Elfo(nome);

        assertEquals(null, elfo.getNome());
        assertNull(elfo.getNome());
        assertEquals(42, elfo.getFlechas());
        assertFalse(42 != elfo.getFlechas());
        assertEquals(0, elfo.getExperiencia());
        assertFalse(0 != elfo.getExperiencia());

    }
}
