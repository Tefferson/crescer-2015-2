import java.util.*;
public class EstrategiaVerde implements EstrategiaDeAtaque {
    private ArrayList<Elfo> atacantes;
    public void atacar(ExercitoElfico exercito, ArrayList<Dwarf> dwarves){
        exercito.agruparPorStatus();
        atacantes = new ArrayList<>();
        ArrayList<Elfo> atacarao = exercito.buscar(Status.VIVO);
        for(Elfo elfo : atacantes){
            if(elfo instanceof ElfoVerde){
                atacantes.add(elfo);
                atacarao.remove(elfo);
                for(Dwarf dwarf : dwarves){
                    elfo.atirarFlecha(dwarf);
                }
            }
        }

        for(Elfo elfo : atacantes){
            if(elfo instanceof ElfoNoturno){
                atacantes.add(elfo);
                atacarao.remove(elfo);
                for(Dwarf dwarf : dwarves){
                    elfo.atirarFlecha(dwarf);
                }
            }
        }
    }

    public ArrayList<Elfo> getOrdemDoUltimoAtaque(){
        return atacantes;
    }
}
