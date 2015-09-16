
/**
 * Escreva a descrição da classe Dwarf aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Dwarf
{
    private int vida;
    private String nome;
    public Dwarf(String nome){
        this.vida = 110;
        this.nome = nome;
    }
    
    public void receberFlechada(){
        vida -= 10;
    }
}
