
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class EstrategiaBalanceadaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EstrategiaBalanceadaTest
{
    @Test
    public void exercitoElficoAtacaHordaDeDwarvesComEstrategiaBalanceada(){
        ExercitoElfico exercito = new ExercitoElfico();      
        exercito.trocarEstrategia(new EstrategiaBalanceada());
        ArrayList<Dwarf> hordaDeDwarves = new ArrayList<>();
        int qtdElfosNoturnos = 15;
        int qtdElfosVerdes = 150;
        int qtdDwarves = 45;
        int esperado = 2 * (qtdElfosNoturnos<qtdElfosVerdes?qtdElfosNoturnos:qtdElfosVerdes);

        for(int i=0;i<qtdElfosVerdes;i++){
            exercito.alistar(new ElfoVerde("elfoVerde"+i, 1000));
        }
        for(int i=0;i<qtdElfosNoturnos;i++){
            exercito.alistar(new ElfoNoturno("elfoNoturno"+i, 1000));
        }

        for(int i=0;i<qtdDwarves;i++){
            hordaDeDwarves.add(new Dwarf("dwarf"+i));
        }

        exercito.atacar(hordaDeDwarves);

        int elfosQueAtacaram = exercito.getOrdemDoUltimoAtaque().size();
        assertEquals(esperado, elfosQueAtacaram);
    }

    @Test
    public void confereUltimaOrdemBalanceada(){
        ExercitoElfico exercito = new ExercitoElfico();      
        exercito.trocarEstrategia(new EstrategiaBalanceada());
        ArrayList<Dwarf> hordaDeDwarves = new ArrayList<>();
        int qtdElfosNoturnos = 15;
        int qtdElfosVerdes = 150;
        int qtdDwarves = 45;
        int menorQtd = qtdElfosNoturnos<qtdElfosVerdes?qtdElfosNoturnos:qtdElfosVerdes;
        int esperado = 2 * menorQtd;

        for(int i=0;i<qtdElfosNoturnos;i++){
            exercito.alistar(new ElfoNoturno("elfoNoturno"+i, 1000));
        }
        for(int i=0;i<qtdElfosVerdes;i++){
            exercito.alistar(new ElfoVerde("elfoVerde"+i, 1000));
        }

        for(int i=0;i<qtdDwarves;i++){
            hordaDeDwarves.add(new Dwarf("dwarf"+i));
        }

        exercito.atacar(hordaDeDwarves);

        int elfosVerdesAtacantes=0, elfosNoturnosAtacantes=0;
        ArrayList<Elfo> atacantes = exercito.getOrdemDoUltimoAtaque();
        for(Elfo elfo : atacantes){
            if(elfo instanceof ElfoVerde){
                elfosVerdesAtacantes++;
            }else if(elfo instanceof ElfoNoturno){
                elfosNoturnosAtacantes++;
            }
        }

        assertEquals(menorQtd, elfosNoturnosAtacantes);
        assertEquals(elfosVerdesAtacantes, elfosNoturnosAtacantes);
    }
}
