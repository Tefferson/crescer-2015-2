
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
}
