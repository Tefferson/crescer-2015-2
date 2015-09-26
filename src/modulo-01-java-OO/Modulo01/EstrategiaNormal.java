import java.util.*;
public class EstrategiaNormal implements EstrategiaDeAtaque
{
    private ArrayList<Elfo> atacantes = new ArrayList<Elfo>();
    public void atacar(ExercitoElfico exercito, ArrayList<Dwarf> dwarves){
        atacantes.clear();
        exercito.agruparPorStatus();
        ArrayList<Elfo> vivos = exercito.buscar(Status.VIVO);
        for(Elfo elfo : vivos){
            atacantes.add(elfo);
            for(Dwarf dwarf : dwarves){
                elfo.atirarFlecha(dwarf);
            }
        }
    }

    public ArrayList<Elfo> getOrdemDoUltimoAtaque(){
        return atacantes;
    }
}
