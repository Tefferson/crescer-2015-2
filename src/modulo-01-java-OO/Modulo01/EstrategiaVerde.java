import java.util.*;
public class EstrategiaVerde implements EstrategiaDeAtaque {
    private ArrayList<Elfo> atacantes = new ArrayList<>();
    public void atacar(ExercitoElfico exercito, ArrayList<Dwarf> dwarves){
        exercito.agruparPorStatus();
        atacantes.clear();
        ArrayList<Elfo> vivos = exercito.buscar(Status.VIVO);
        for(Elfo elfo : vivos){
            if(elfo instanceof ElfoVerde){
                atacantes.add(0, elfo);
            }else if(elfo instanceof ElfoNoturno){            
                atacantes.add(elfo);
            }
        }

        for(Elfo elfo:atacantes){
            for(Dwarf dwarf:dwarves){
                elfo.atirarFlecha(dwarf);
            }
        }
    }

    public ArrayList<Elfo> getOrdemDoUltimoAtaque(){
        return atacantes;
    }
}
