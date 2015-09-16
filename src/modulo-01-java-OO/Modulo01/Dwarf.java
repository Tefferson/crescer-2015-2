/**
 * Escreva a descrição da classe Dwarf aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Dwarf
{
    private int vida;
    public Dwarf(){
        this.vida = 110;
    }

    public void receberFlechada(){
        vida -= 10;
    }

    public int getVida(){
        return this.vida;
    }
}
