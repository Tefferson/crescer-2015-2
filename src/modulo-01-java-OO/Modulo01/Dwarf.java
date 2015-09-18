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

    public Dwarf(String nome){
        this.vida = 110;
        this.status = Status.VIVO;
        this.experiencia = 0;
        this.dataNascimento = new DataTerceiraEra(1,1,1);
        this.nome = nome;
    }

    public Dwarf(String nome, DataTerceiraEra dataNascimento){
        this(nome);
        this.dataNascimento = dataNascimento;
    }

    public void receberFlechada(){
        if(this.status==Status.VIVO){
            double numeroSorte = this.getNumeroSorte();
            if(numeroSorte<0){
                this.experiencia += 2;
            }else if(!(numeroSorte>=0 && numeroSorte <= 100)){
                vida -= 10;
                if(vida==0){
                    this.status = Status.MORTO;
                }
            }
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

    public DataTerceiraEra getDataNascimento(){
        return this.dataNascimento;
    }

    public double getNumeroSorte(){
        double valorInicial = 101.0;

        if(this.dataNascimento.ehBissexto()){
            if(this.vida >= 80 && this.vida <=90){
                return valorInicial*-33;
            }
        }else{
            if(this.nome=="Seixas" || this.nome=="Meireles"){
                return (valorInicial*33)%100; 
            }
        }

        return valorInicial;
    }
}    
