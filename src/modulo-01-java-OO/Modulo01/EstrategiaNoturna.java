import java.util.*;
public class EstrategiaNoturna implements EstrategiaDeAtaque{
    private ArrayList<Elfo> atacantes = new ArrayList<Elfo>();
    public void atacar(ExercitoElfico exercito, ArrayList<Dwarf> dwarves){
        atacantes.clear();
        exercito.agruparPorStatus();
        ArrayList<Elfo> vivos = exercito.buscar(Status.VIVO);
        double intencoesDeAtaque = vivos.size() * dwarves.size();
        int limiteDeAtaqueNoturno = (int)(intencoesDeAtaque * .3);
        int ataques = 0;
        for(Elfo elfo : vivos){
            if(!(elfo instanceof ElfoNoturno && ataques>=limiteDeAtaqueNoturno)){
                atacantes.add(elfo);
            }
            for(Dwarf dwarf : dwarves){
                if(!(elfo instanceof ElfoNoturno && ataques>=limiteDeAtaqueNoturno)){
                }else{                
                    ataques++;  
                }
            }
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
