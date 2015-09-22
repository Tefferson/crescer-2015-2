
/**
 * Write a description of class ElfoVerde here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ElfoVerde extends Elfo
{
    public ElfoVerde(String nome){
        super(nome);
    }

    public void atirarFlecha(Dwarf dwarf){
        super.atirarFlecha(dwarf);
        experiencia++;
    }

    public void adicionarItem(Item item){
        String desItem = item.getDescricao();
        String espada = "Espada de aço valiriano";
        String arco = "Arco e Flecha de Vidro";
        if(espada.equals(desItem) || arco.equals(desItem)){
            this.inventario.adicionarItem(item);
        }
    }
}
