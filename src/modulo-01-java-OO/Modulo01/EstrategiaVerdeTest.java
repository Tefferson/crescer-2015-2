import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EstrategiaVerdeTest
{
    @Test
    public void elfosVerdesAtacamPrimeiro() {
        ExercitoElfico exercito = new ExercitoElfico();   
        exercito.trocarEstrategia(new EstrategiaVerde());
        int qtdElfosNoturnos = 2;
        int qtdElfosVerdes = 15;
        int qtdDwarves = 45;

        for(int i=0;i<qtdElfosVerdes;i++){
            exercito.alistar(new ElfoVerde("elfoVerde"+i, 1000));
        }
        for(int i=0;i<qtdElfosNoturnos;i++){
            exercito.alistar(new ElfoNoturno("elfoNoturno"+i, 1000));
        }
        exercito.agruparPorStatus();
        ArrayList<Elfo> antes = exercito.buscar(Status.VIVO);
        ArrayList<Dwarf> hordaDeDwarves = new ArrayList<>();

        for(int i=0;i<qtdDwarves;i++){
            hordaDeDwarves.add(new Dwarf("dwarf"+i));
        }

        exercito.atacar(hordaDeDwarves);

        assertFalse(antes.get(0) instanceof ElfoVerde == exercito.getOrdemDoUltimoAtaque().get(0) instanceof ElfoNoturno);
    }
}
