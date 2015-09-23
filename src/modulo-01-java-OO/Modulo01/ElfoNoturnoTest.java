
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ElfoNoturnoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ElfoNoturnoTest
{
    @Test
    public void elfoGanha3NíveisDexperiência(){
        Elfo elfo = new ElfoNoturno("elfo noturno");
        Dwarf dwarf = new IrishDwarf("Anão Irlandês");
        int xpEsperada = 3;

        elfo.atirarFlecha(dwarf);

        assertEquals(xpEsperada, elfo.getExperiencia());
    }

    @Test
    public void elfoMorrePorAtirarMuitasFlechas(){
        Elfo elfo = new ElfoNoturno("elfo noturno", 1000);
        Dwarf dwarf = new IrishDwarf("Anão Irlandês");
        Status statusEsperado = Status.MORTO;

        for(int i=0;i<90;i++){elfo.atirarFlecha(dwarf);}

        assertEquals(statusEsperado, elfo.getStatus());
    }

    @Test
    public void elfoFicaFeridoPorAtirarMuitasFlechas(){
        Elfo elfo = new ElfoNoturno("elfo noturno", 1000);
        Dwarf dwarf = new IrishDwarf("Anão Irlandês");
        Status statusEsperado = Status.FERIDO;

        for(int i=0;i<89;i++){elfo.atirarFlecha(dwarf);}

        assertEquals(statusEsperado, elfo.getStatus());
    }
}
