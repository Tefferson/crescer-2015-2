import java.util.*;
/**
 * Write a description of class ExercitoElfico here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExercitoElfico
{
    private HashMap<String, Elfo> exercito;
    private HashMap<Status, HashMap<String, Elfo>> exercitoAgrupado;
    public ExercitoElfico(){
        exercito = new HashMap<>();
        exercitoAgrupado = new HashMap<>();
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

    public void agruparPorStatus(){
        this.exercitoAgrupado.clear();
        for(Map.Entry<String, Elfo> entry : exercito.entrySet()){
            Elfo elfo = entry.getValue();
            Status status = elfo.getStatus();
            HashMap<String, Elfo> innerMap = exercitoAgrupado.get(status);
            if(innerMap==null){
                innerMap = new HashMap<>();
                exercitoAgrupado.put(status, innerMap);
            }
            innerMap.put(elfo.getNome(), elfo);
        }
    }

    public ArrayList<Elfo> buscar(Status status){        
        HashMap<String, Elfo> buscado = exercitoAgrupado.get(status);
        if(buscado == null){
            return null;
        }
        return new ArrayList<Elfo>(buscado.values());
    }
}
