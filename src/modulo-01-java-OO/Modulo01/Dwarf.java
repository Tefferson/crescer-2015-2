/**
 * Escreva a descrição da classe Dwarf aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Dwarf extends PersonagemDaTerraMedia
{
    protected DataTerceiraEra dataNascimento;

    public Dwarf(String nome){
        super(nome);
        this.vida = 110;
        this.dataNascimento = new DataTerceiraEra(1,1,1);
        this.nome = nome;
    }

    public Dwarf(String nome, DataTerceiraEra dataNascimento){
        this(nome);
        this.dataNascimento = dataNascimento;
    }

    public void receberFlechada(){
        double numeroSorte = this.getNumeroSorte();
        if(numeroSorte<0){
            this.experiencia += 2;
        }else if(!(numeroSorte>=0 && numeroSorte <= 100) && vida > 0){
            vida -= 10;
            if(vida==0){
                this.status = Status.MORTO;
            }
        }
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

    public void tentarSorte(){
        if(this.getNumeroSorte()==-3333.0){
            this.inventario.adicionarMilDeCadaItem();
        }
    }

    private void receberDano(int dano){
        if(this.status==Status.VIVO){
            if(dano<this.vida){
                this.vida-=dano;
            }else{
                this.status = Status.MORTO;
                this.vida=0;
            }
        }
    }
}    
