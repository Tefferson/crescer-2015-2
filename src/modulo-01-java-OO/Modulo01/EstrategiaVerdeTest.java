import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EstrategiaVerdeTest
{
    @Test
    public void exercitoAtacaHordaDeDwarves(){
        ExercitoElfico exercito = new ExercitoElfico();   
        exercito.trocarEstrategia(new EstrategiaVerde());
        ArrayList<Dwarf> hordaDeDwarves = new ArrayList<>();
        int qtdElfosNoturnos = 15;
        int qtdElfosVerdes = 15;
        int qtdDwarves = 45;
        int maximoDeElfosNoturnosQueDeveriamAtacar = 15;

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

        exercito.agruparPorStatus();
        int elfosNoturnosQueAtacaram = exercito.buscar(Status.FERIDO).size();
        assertEquals(maximoDeElfosNoturnosQueDeveriamAtacar, elfosNoturnosQueAtacaram);
    }

    @Test
    public void estrategia1Elfo2Dwarves() {
        // Arrange
        ExercitoElfico exercito = new ExercitoElfico();
        exercito.trocarEstrategia(new EstrategiaVerde());
        Dwarf d1 = new Dwarf("dwarf1");
        Dwarf d2 = new Dwarf("dwarf2");
        ArrayList<Dwarf> alvos = new ArrayList<Dwarf>(
                Arrays.asList(d1, d2)
            );
        ElfoVerde soldado = new ElfoVerde("Green Elf 1");

        double vidaEsperada = 100;
        // Act
        exercito.alistar(soldado);
        exercito.atacar(alvos);
        // Assert
        assertEquals(new ArrayList<Elfo>(
                Arrays.asList(soldado)
            ), exercito.getOrdemDoUltimoAtaque());

        assertEquals(vidaEsperada, d1.getVida(), .0);
        assertEquals(vidaEsperada, d2.getVida(), .0);
    }

    @Test
    public void estrategia3Elfos2Dwarves() {
        // Arrange
        Dwarf d1 = new Dwarf("d1");
        Dwarf d2 = new Dwarf("d2");
        ArrayList<Dwarf> alvos = new ArrayList<Dwarf>(
                Arrays.asList(d1, d2)
            );
        Elfo soldado1 = new ElfoVerde("Green Elf 1");
        Elfo soldado2 = new ElfoNoturno("Green Elf 2");
        Elfo soldado3 = new ElfoVerde("Green Elf 3");
        ExercitoElfico exercito = new ExercitoElfico();
        exercito.trocarEstrategia(new EstrategiaVerde());
        double vidaEsperada = 80;
        // Act
        exercito.alistar(soldado1);
        exercito.alistar(soldado2);
        exercito.alistar(soldado3);
        exercito.atacar(alvos);
        // Assert
        assertEquals(new ArrayList<Elfo>(
                Arrays.asList(soldado3, soldado2, soldado1)
            ), exercito.getOrdemDoUltimoAtaque());

        assertEquals(vidaEsperada, d1.getVida(), .0);
        assertEquals(vidaEsperada, d2.getVida(), .0);
    }

    @Test
    public void estrategia3Elfos2Dwarves2OndasDeAtaque() {
        // Arrange
        Dwarf d1 = new Dwarf("d1");
        Dwarf d2 = new Dwarf("d2");
        ArrayList<Dwarf> alvos = new ArrayList<Dwarf>(
                Arrays.asList(d1, d2)
            );
        Elfo soldado1 = new ElfoVerde("Green Elf 1");
        Elfo soldado2 = new ElfoNoturno("Green Elf 2");
        Elfo soldado3 = new ElfoVerde("Green Elf 3");
        ArrayList<Elfo> esperado = new ArrayList<Elfo>(Arrays.asList(soldado3, soldado2, soldado1));
        ExercitoElfico exercito = new ExercitoElfico();
        exercito.trocarEstrategia(new EstrategiaVerde());
        double vidaEsperada = 60;
        // Act
        exercito.alistar(soldado1);
        exercito.alistar(soldado2);
        exercito.alistar(soldado3);
        exercito.atacar(alvos);
        exercito.atacar(alvos);
        ArrayList<Elfo> historicoAtaque = exercito.getOrdemDoUltimoAtaque();
        // Assert
        assertTrue(historicoAtaque.contains(soldado1));            
        assertTrue(historicoAtaque.contains(soldado3));

        assertEquals(vidaEsperada, d1.getVida(), .0);
        assertEquals(vidaEsperada, d2.getVida(), .0);
    }

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
