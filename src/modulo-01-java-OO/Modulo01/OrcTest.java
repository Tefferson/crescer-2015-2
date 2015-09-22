import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class OrcTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class OrcTest
{
    @Test
    public void orcNasceVivoEComVidaPositiva(){
        Orc orc = new Orc();

        assertEquals(Status.VIVO, orc.getStatus());
        assertTrue(orc.getVida()>0);
    }

    @Test
    public void orcRecebe1FlechadaDeDwarf(){
        Orc orc = new Orc();
        int esperado = orc.getVida()-10;

        orc.receberFlechada(new Dwarf("dwarf"));

        assertEquals(Status.VIVO, orc.getStatus());
        assertEquals(esperado, orc.getVida());
    }

    @Test
    public void orcRecebe13FlechadasDeDwarf(){
        Orc orc = new Orc();

        for(int i=0;i<13;i++){orc.receberFlechada(new Dwarf("dwarf"));}

        assertEquals(Status.MORTO, orc.getStatus());
        assertEquals(0, orc.getVida());
    }

    @Test
    public void orcComEscudoRecebe2FlechadasDeDwarf(){
        Orc orc = new Orc();
        int esperado = orc.getVida()-10;
        orc.adicionarItem(new Item("Escudo Uruk-Hai", 1));

        orc.receberFlechada(new Dwarf("dwarf"));
        orc.receberFlechada(new Dwarf("dwarf"));

        assertEquals(Status.VIVO, orc.getStatus());
        assertEquals(esperado, orc.getVida());
    }

    @Test
    public void orcRecebe2FlechadasDeElfo(){
        Orc orc = new Orc();
        int esperado = orc.getVida()-16;

        orc.receberFlechada(new Elfo("dwarf"));
        orc.receberFlechada(new Elfo("dwarf"));

        assertEquals(Status.VIVO, orc.getStatus());
        assertEquals(esperado, orc.getVida());
    }

    @Test
    public void danoDeOrcÉ12ComEspada(){
        Orc orc = new Orc();
        orc.adicionarItem(new Item("Espada", 1));

        assertEquals(12, orc.getDano());
    }

    @Test
    public void danoDeOrcÉ8ComArcoEFlecha(){
        Orc orc = new Orc();
        orc.adicionarItem(new Item("Arco", 1));
        orc.adicionarItem(new Item("Flecha", 2));

        assertEquals(8, orc.getDano());
    }
    
    @Test
    public void orcSemDano(){
        Orc orc = new Orc();
        orc.adicionarItem(new Item("Gravata", 1));
        orc.adicionarItem(new Item("Chapéu", 2));

        assertEquals(0, orc.getDano());
    }
    
    @Test
    public void orcSemDanoAtaca3VezesDwarf(){
        Orc orc = new Orc();
        Dwarf dwarf = new Dwarf("dwarf");
        orc.adicionarItem(new Item("Gravata", 1));
        orc.adicionarItem(new Item("Chapéu", 2));

        orc.atacar(dwarf);
        orc.atacar(dwarf);
        orc.atacar(dwarf);
        
        assertEquals(110, dwarf.getVida());
    }
    
    @Test
    public void orcComEspadaAtaca3VezesDwarf(){
        Orc orc = new Orc();
        Dwarf dwarf = new Dwarf("dwarf");
        orc.adicionarItem(new Item("Espada", 1));
        orc.adicionarItem(new Item("Chapéu", 2));

        orc.atacar(dwarf);
        orc.atacar(dwarf);
        orc.atacar(dwarf);
        
        assertEquals(74, dwarf.getVida());
    }
    
    @Test
    public void orcComArcoE4FlechasAtaca3VezesDwarf(){
        Orc orc = new Orc();
        Dwarf dwarf = new Dwarf("dwarf");
        orc.adicionarItem(new Item("Arco", 1));
        orc.adicionarItem(new Item("Flecha", 4));

        orc.atacar(dwarf);
        orc.atacar(dwarf);
        orc.atacar(dwarf);
        
        assertEquals(86, dwarf.getVida());
    }
    
    @Test
    public void orcComArcoE2FlechasAtaca4VezesElfo(){
        Orc orc = new Orc();
        Elfo elfo = new Elfo("elfo");
        orc.adicionarItem(new Item("Arco", 1));
        orc.adicionarItem(new Item("Flecha", 2));

        orc.atacar(elfo);
        orc.atacar(elfo);
        orc.atacar(elfo);
        orc.atacar(elfo);
        
        assertEquals(64, elfo.getVida());
    }
    
    @Test
    public void orcComArcoE4FlechasAtaca4VezesElfoEDwarf(){
        Orc orc = new Orc();
        Dwarf dwarf = new Dwarf("dwarf");
        Elfo elfo = new Elfo("elfo");
        orc.adicionarItem(new Item("Arco", 1));
        orc.adicionarItem(new Item("Flecha", 2));
        orc.adicionarItem(new Item("Flecha", 2));

        orc.atacar(elfo);
        orc.atacar(dwarf);
        orc.atacar(dwarf);
        orc.atacar(elfo);
        
        assertEquals(64, elfo.getVida());
        assertEquals(94, dwarf.getVida());
    }
    
    @Test
    public void orcComEspadaEArcoAtaca4VezesElfoEDwarf(){
        Orc orc = new Orc();
        Dwarf dwarf = new Dwarf("dwarf");
        Elfo elfo = new Elfo("elfo");
        orc.adicionarItem(new Item("Arco", 1));
        orc.adicionarItem(new Item("Flecha", 2));
        orc.adicionarItem(new Item("Flecha", 2));
        orc.adicionarItem(new Item("Espada", 1));

        orc.atacar(elfo);
        orc.atacar(dwarf);
        orc.atacar(dwarf);
        orc.atacar(elfo);
        
        assertEquals(56, elfo.getVida());
        assertEquals(86, dwarf.getVida());
    }
}
