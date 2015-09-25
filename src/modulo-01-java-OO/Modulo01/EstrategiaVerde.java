import java.util.*;
public class EstrategiaVerde implements EstrategiaDeAtaque {
    private ArrayList<Elfo> atacantes;
    public void atacar(ExercitoElfico exercito, ArrayList<Dwarf> dwarves){
        exercito.agruparPorStatus();
        atacantes = new ArrayList<>();
        ArrayList<Elfo> atacarao = exercito.buscar(Status.VIVO);
        double intencoesDeAtaque = atacantes.size() * dwarves.size();
        int limiteDeAtaqueNoturno = (int)(intencoesDeAtaque * .3);
        int ataques = 0;
        for(Elfo elfo : atacantes){
            if(elfo instanceof ElfoVerde){
                atacantes.add(elfo);
                atacarao.remove(elfo);
                for(Dwarf dwarf : dwarves){
                    if(!(elfo instanceof ElfoNoturno && ataques>limiteDeAtaqueNoturno)){
                        elfo.atirarFlecha(dwarf);
                    }else{                    
                        ataques++;                
                    }
                }
            }
        }

        for(Elfo elfo : atacantes){
            if(elfo instanceof ElfoNoturno){
                atacantes.add(elfo);
                atacarao.remove(elfo);
                for(Dwarf dwarf : dwarves){
                    if(!(elfo instanceof ElfoNoturno && ataques>limiteDeAtaqueNoturno)){
                        elfo.atirarFlecha(dwarf);
                    }else{                    
                        ataques++;                
                    }
                }
            }
        }
    }

    public ArrayList<Elfo> getOrdemDoUltimoAtaque(){
        return atacantes;
    }
}
