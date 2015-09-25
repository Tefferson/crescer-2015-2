import java.util.*;
public class EstrategiaNormal implements EstrategiaDeAtaque{
    public void atacar(ExercitoElfico exercito, ArrayList<Dwarf> dwarves){
        exercito.agruparPorStatus();
        ArrayList<Elfo> elfos = exercito.buscar(Status.VIVO);
        double intencoesDeAtaque = elfos.size() * dwarves.size();
        int limiteDeAtaqueNoturno = (int)(intencoesDeAtaque * .3);
        int ataques = 0;
        for(Elfo elfo : elfos){
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
