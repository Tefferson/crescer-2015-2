
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElfoTest
{
    @Test
    public void elfoNasce(){
        int flechas = 23;
        String nome = "elfo";
        Elfo elfo = new Elfo(nome, flechas);

        assertEquals(nome, elfo.getNome());
        assertEquals(flechas, elfo.getFlechas());
        assertEquals(0, elfo.getExperiencia());

    }

    @Test
    public void elfoNasceComFlechasInteger(){
        Integer flechas = 23;
        String nome = "elfo";
        Elfo elfo = new Elfo(nome, flechas);

        assertEquals(nome, elfo.getNome());
        assertEquals(flechas, new Integer(elfo.getFlechas()));
        assertEquals(0, elfo.getExperiencia());

    }

    @Test
    public void elfoNasceComFlechasNulas(){
        Integer flechas = null;
        String nome = "elfo";
        Elfo elfo = new Elfo(nome, flechas);

        assertEquals(nome, elfo.getNome());
        assertEquals(42, elfo.getFlechas());
        assertEquals(0, elfo.getExperiencia());

    }

    @Test
    public void elfoNasceApenasComNome(){
        String nome = "elfo";
        Elfo elfo = new Elfo(nome);

        assertEquals(nome, elfo.getNome());
        assertEquals(42, elfo.getFlechas());
        assertEquals(0, elfo.getExperiencia());

    }


    @Test
    public void elfoAtiraFlechaNoDwarf(){
        int flechas = 23;
        String nome = "elfo";
        Elfo elfo = new Elfo(nome, flechas);        
        Dwarf dwarf = new Dwarf();
        elfo.atirarFlecha(dwarf);

        assertEquals(flechas-1, elfo.getFlechas());
        assertEquals(1, elfo.getExperiencia());

    }

}
