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
        exercito.alistar(esperado);

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
        exercito.alistar(elfo1);
        exercito.alistar(elfo2);
        exercito.alistar(elfo3);
        exercito.alistar(elfo4);
        exercito.alistar(elfo5);
        exercito.alistar(elfo6);
        exercito.alistar(elfo7);
        exercito.alistar(elfo8);
        exercito.alistar(elfo9);
        exercito.alistar(elfox);  
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

    @Test
    public void doisElfosMortosEDoisVivosAgrupadosPorStatus(){
        ExercitoElfico exercitoDeElfos = new ExercitoElfico();
        ElfoVerde green = new ElfoVerde("Fandango1");
        ElfoVerde green2 = new ElfoVerde("Fandango2");
        ElfoNoturno noturno = new ElfoNoturno("Noturno", 100);
        ElfoNoturno noturno2 = new ElfoNoturno("Noturno II", 100);
        for(int i = 0; i < 99; i++){
            noturno.atirarFlecha(new Dwarf(""));
            noturno2.atirarFlecha(new Dwarf(""));
        }
        exercitoDeElfos.alistar(green);
        exercitoDeElfos.alistar(green2);
        exercitoDeElfos.alistar(noturno);
        exercitoDeElfos.alistar(noturno2);
        exercitoDeElfos.agruparPorStatus();

        assertEquals(green, exercitoDeElfos.buscar(Status.VIVO).get(1));
        assertEquals(green2, exercitoDeElfos.buscar(Status.VIVO).get(0));
        assertEquals(noturno, exercitoDeElfos.buscar(Status.MORTO).get(0));
        assertEquals(noturno2, exercitoDeElfos.buscar(Status.MORTO).get(1));
    }

}
