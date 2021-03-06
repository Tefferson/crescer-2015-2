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

        orc.receberDano(new Dwarf("dwarf"));

        assertEquals(Status.FERIDO, orc.getStatus());
        assertEquals(15, orc.getVida(), 0);
    }

    @Test
    public void orcRecebe13FlechadasDeDwarf(){
        Orc orc = new Orc();

        for(int i=0;i<13;i++){orc.receberDano(new Dwarf("dwarf"));}

        assertEquals(Status.MORTO, orc.getStatus());
        assertEquals(0, orc.getVida(), 0);
    }

    @Test
    public void orcComEscudoRecebe2FlechadasDeDwarf(){
        Orc orc = new Orc();
        orc.adicionarItem(new Item("Escudo Uruk-Hai", 1));

        orc.receberDano(new Dwarf("dwarf"));
        orc.receberDano(new Dwarf("dwarf"));

        assertEquals(Status.FERIDO, orc.getStatus());
        assertEquals(13, orc.getVida(), 0);
    }

    @Test
    public void orcRecebe2FlechadasDeElfo(){
        Orc orc = new Orc();

        orc.receberDano(new Elfo("dwarf"));
        orc.receberDano(new Elfo("dwarf"));

        assertEquals(Status.FERIDO, orc.getStatus());
        assertEquals(5, orc.getVida(), 0);
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

        assertEquals(110, dwarf.getVida(), 0);
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

        assertEquals(74, dwarf.getVida(), 0);
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

        assertEquals(86, dwarf.getVida(), 0);
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

        assertEquals(84, elfo.getVida(), 0);
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

        assertEquals(84, elfo.getVida(), 0);
        assertEquals(94, dwarf.getVida(), 0);
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

        assertEquals(76, elfo.getVida(), 0);
        assertEquals(86, dwarf.getVida(), 0);
    }

    @Test
    public void orcFoge(){
        Orc orc = new Orc();
        orc.adicionarItem(new Item("Arcos", 1));
        orc.adicionarItem(new Item("Adaga", 2));

        orc.atacar(new Elfo("elfo"));

        assertEquals(Status.FUGITIVO, orc.getStatus());
    }

    @Test
    public void orcAtacaOrc(){
        Orc orc = new Orc();
        Orc orc2 = new Orc();
        orc.adicionarItem(new Item("Espada", 1));
        orc2.adicionarItem(new Item("Espada", 1));

        orc.atacar(orc2);

        assertEquals(15, orc2.getVida(), 0);
    }

    @Test
    public void snagaAtacaUrukHai(){
        Orc snaga = new Snaga();
        Orc urukhai = new UrukHai();

        snaga.atacar(urukhai);

        assertEquals(144, urukhai.getVida(), 0);
    }

}
