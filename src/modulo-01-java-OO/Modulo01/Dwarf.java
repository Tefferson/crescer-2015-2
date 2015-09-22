/**
 * Escreva a descrição da classe Dwarf aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Dwarf
{
    protected String nome;
    protected int vida, experiencia;
    protected Status status;
    protected DataTerceiraEra dataNascimento;
    protected Inventario inventario;

    public Dwarf(String nome){
        this.vida = 110;
        this.status = Status.VIVO;
        this.experiencia = 0;
        this.dataNascimento = new DataTerceiraEra(1,1,1);
        this.nome = nome;
        this.inventario = new Inventario();
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

    public void tentarSorte(){
        if(this.getNumeroSorte()==-3333.0){
            this.inventario.adicionarMilDeCadaItem();
        }
    }

    public Inventario getInventario(){
        return this.inventario;
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

    public void receberDano(Orc orc){
        this.receberDano(orc.agirNoAtaque());
    }
}    
