/**
 * Escreva a descrição da classe Dwarf aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Dwarf
{
    private String nome;
    private int vida, experiencia;
    private Status status;
    private DataTerceiraEra dataNascimento;
    public Dwarf(){
        this.vida = 110;
        this.status = Status.VIVO;
        this.experiencia = 0;
        this.dataNascimento = new DataTerceiraEra(1,1,1);
    }

    public Dwarf(String nome){
        this();
        this.nome = nome;
    }

    public Dwarf(String nome, DataTerceiraEra dataNascimento){
        this();
        this.nome = nome;
        this.dataNascimento = dataNascimento;
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

    public String getNome(){
        return this.nome;
    }

    public int getExperiencia(){
        return this.experiencia;
    }
}
