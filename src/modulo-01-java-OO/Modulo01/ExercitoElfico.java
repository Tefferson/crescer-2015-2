import java.util.HashMap;
/**
 * Write a description of class ExercitoElfico here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExercitoElfico
{
    private HashMap<String, Elfo> exercito;
    public ExercitoElfico(){
        exercito = new HashMap<>();
    }

    public void alistarElfo(ElfoNoturno elfo){
        alistar(elfo);
    }

    public void alistarElfo(ElfoVerde elfo){
        alistar(elfo);
    }

    private void alistar(Elfo elfo){
        exercito.put(elfo.getNome(), elfo);
    }

    public Elfo getElfo(String nome){
        return exercito.get(nome);
    }
}
