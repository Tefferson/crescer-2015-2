import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ExercitoElficoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ExercitoElficoTest
{
    @Test
    public void alistaElfoVerde(){    
        ExercitoElfico exercito = new ExercitoElfico();
        ElfoVerde esperado = new ElfoVerde("elfo");
        exercito.alistarElfo(esperado);

        assertEquals(esperado, exercito.getElfo("elfo"));
    }

    @Test
    public void agrupaExercitoPorStatus(){    
        ExercitoElfico exercito = new ExercitoElfico();
        ElfoVerde elfo1 = new ElfoVerde("elfo1");
        ElfoVerde elfo2 = new ElfoVerde("elfo2");
        ElfoVerde elfo3 = new ElfoVerde("elfo3");
        ElfoVerde elfo4 = new ElfoVerde("elfo4");
        ElfoVerde elfo5 = new ElfoVerde("elfo5");
        ElfoVerde elfo6 = new ElfoVerde("elfo6");
        ElfoVerde elfo7 = new ElfoVerde("elfo7");
        ElfoVerde elfo8 = new ElfoVerde("elfo8");
        ElfoVerde elfo9 = new ElfoVerde("elfo9");
        ElfoVerde elfox = new ElfoVerde("elfox");
        exercito.alistarElfo(elfo1);
        exercito.alistarElfo(elfo2);
        exercito.alistarElfo(elfo3);
        exercito.alistarElfo(elfo4);
        exercito.alistarElfo(elfo5);
        exercito.alistarElfo(elfo6);
        exercito.alistarElfo(elfo7);
        exercito.alistarElfo(elfo8);
        exercito.alistarElfo(elfo9);
        exercito.alistarElfo(elfox);  
        Orc orc = new UrukHai();
        elfo3.receberDano(orc);
        for(int i=0;i<90;i++){        
            elfo6.receberDano(orc);
        }

        exercito.agruparPorStatus();
        
        List feridos = exercito.buscar(Status.FERIDO);
        List mortos = exercito.buscar(Status.MORTO);
        
        assertEquals(elfo3, feridos.get(0));
        assertEquals(elfo6, mortos.get(0));
    }

}
