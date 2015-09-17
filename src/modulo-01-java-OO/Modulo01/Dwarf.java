/**
 * Escreva a descrição da classe Dwarf aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Dwarf
{
    private int vida;
    private Status status;
    public Dwarf(){
        this.vida = 110;
        this.status = Status.MORTO;
    }

    public void receberFlechada(){
        vida -= 10;
        if(vida==0){
            this.status = Status.MORTO;
        }
    }

    public int getVida(){
        return this.vida;
    }
    
    public Status getStatus(){
        return this.status;
    }
}
