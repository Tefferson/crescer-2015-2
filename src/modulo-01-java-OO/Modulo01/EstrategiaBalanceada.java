import java.util.*;

public class EstrategiaBalanceada implements EstrategiaDeAtaque
{
    private ArrayList<Elfo> atacantes = new ArrayList<Elfo>();
    public void atacar(ExercitoElfico exercito, ArrayList<Dwarf> dwarves){
        atacantes.clear();
        exercito.agruparPorStatus();
        ArrayList<Elfo> vivos = exercito.buscar(Status.VIVO);
        HashMap<String, Elfo> map = new HashMap<>();
        int noturnos=0, verdes=0;
        for(Elfo elfo : vivos){
            if(elfo instanceof ElfoVerde){
                map.put(elfo.getClass().getSimpleName()+verdes, elfo);
                verdes++;
            }else if(elfo instanceof ElfoNoturno){
                map.put(elfo.getClass().getSimpleName()+noturnos, elfo);
                noturnos++;
            }
        }

        int menorQtd = noturnos<verdes?noturnos:verdes;
        for(int i=0;i<menorQtd;i++){
            atacantes.add(map.get("ElfoVerde"+i));
            atacantes.add(map.get("ElfoNoturno"+i));
        }

        for(Elfo elfo : atacantes){
            for(Dwarf dwarf : dwarves){
                elfo.atirarFlecha(dwarf);
            }
        }
    }

    public ArrayList<Elfo> getOrdemDoUltimoAtaque(){
        return atacantes;
    }
}
