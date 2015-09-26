
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class EstrategiaNormalTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EstrategiaNormalTest
{
    @Test
    public void exercitoElficoAtacaHordaDeDwarves(){
        ExercitoElfico exercito = new ExercitoElfico();      
        ArrayList<Dwarf> hordaDeDwarves = new ArrayList<>();
        int qtdElfosNoturnos = 15;
        int qtdElfosVerdes = 15;
        int qtdDwarves = 45;
        int esperado = qtdElfosVerdes+qtdElfosNoturnos;

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
    public void dwarvesSofrem4AtaquesCada(){
        ExercitoElfico exercito = new ExercitoElfico();      
        ArrayList<Dwarf> hordaDeDwarves = new ArrayList<>();
        int qtdElfosNoturnos = 3;
        int qtdElfosVerdes = 1;
        int qtdDwarves = 45;
        int totalElfos = qtdElfosVerdes+qtdElfosNoturnos;
        int vidaEsperada = Math.abs(110 - totalElfos*10)*qtdDwarves;

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

        int vidaTotalRemanescente = 0;
        for(int i=0;i<qtdDwarves;i++){
            vidaTotalRemanescente+=hordaDeDwarves.get(0).getVida();
        }

        assertEquals(vidaEsperada, vidaTotalRemanescente);
    }
}
