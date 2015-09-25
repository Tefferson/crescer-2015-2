import java.util.*;
public class EstrategiaNormal implements EstrategiaDeAtaque{
    private ArrayList<Elfo> atacantes;
    public void atacar(ExercitoElfico exercito, ArrayList<Dwarf> dwarves){
        exercito.agruparPorStatus();
        atacantes = exercito.buscar(Status.VIVO);
        double intencoesDeAtaque = atacantes.size() * dwarves.size();
        int limiteDeAtaqueNoturno = (int)(intencoesDeAtaque * .3);
        int ataques = 0;
        for(Elfo elfo : atacantes){
            for(Dwarf dwarf : dwarves){
                if(!(elfo instanceof ElfoNoturno && ataques>limiteDeAtaqueNoturno)){
                    elfo.atirarFlecha(dwarf);
                }else{                    
                    ataques++;                
                }
            }
        }
    }

    public ArrayList<Elfo> getOrdemDoUltimoAtaque(){
        return atacantes;
    }
}
