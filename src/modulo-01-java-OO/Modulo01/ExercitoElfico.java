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
    private HashMap<Status, ArrayList<Elfo>> exercitoAgrupado;
    private EstrategiaDeAtaque estrategia;

    public ExercitoElfico(){
        exercito = new HashMap<>();
        exercitoAgrupado = new HashMap<>();
        estrategia = new EstrategiaNormal();
    }

    public void atacar(ArrayList<Dwarf> hordaDeDwarves){
        estrategia.atacar(this, hordaDeDwarves);
    }

    public void alistar(Elfo elfo){
        boolean podeAlistar = elfo instanceof ElfoVerde || elfo instanceof ElfoNoturno; 
        if(podeAlistar){
            exercito.put(elfo.getNome(), elfo);
        }
    }

    public Elfo getElfo(String nome){
        return exercito.get(nome);
    }

    public void agruparPorStatus(){
        this.exercitoAgrupado.clear();
        for(Map.Entry<String, Elfo> entry : exercito.entrySet()){
            Elfo elfo = entry.getValue();
            Status status = elfo.getStatus();
            ArrayList<Elfo> innerList = exercitoAgrupado.get(status);
            if(innerList==null){
                innerList = new ArrayList<>();
                exercitoAgrupado.put(status, innerList);
            }
            innerList.add(elfo);
        }
    }

    public ArrayList<Elfo> buscar(Status status){     
        return exercitoAgrupado.get(status);
    }

    public ArrayList<Elfo> getOrdemDoUltimoAtaque(){
        return estrategia.getOrdemDoUltimoAtaque();
    }

    public void trocarEstrategia(EstrategiaDeAtaque estragia){
        this.estrategia = estrategia;
    }
}
