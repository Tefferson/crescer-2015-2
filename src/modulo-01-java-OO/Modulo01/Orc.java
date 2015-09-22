/**
 * Escreva a descrição da classe orc aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Orc
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    protected int vida;
    protected Inventario inventario;
    private Status status;

    public Orc() {
        this.vida = (int)(100 / 10 * 2.5);
        this.inventario = new Inventario();
        this.status = Status.VIVO;
    }

    public void gerarDano(int dano) {
        if(this.status!=Status.MORTO){
            if(dano<vida){
                this.status = Status.FERIDO;
                this.vida -= dano;
            }else{
                this.vida=0;
                this.status = Status.MORTO;
            }
        }
    }

    public void receberFlechada(PersonagemDaTerraMedia personagem) {
        if(personagem instanceof Dwarf){
            if(this.inventario.getItem("Escudo Uruk-Hai")!=null){
                gerarDano(5);
            }else{            
                gerarDano(10);
            }
        } else if(personagem instanceof Elfo){
            this.gerarDano(8);
        }
    }

    public void adicionarItem(Item novoItem) {
        this.inventario.adicionarItem(novoItem);
    }

    private boolean possuiEspada(){
        return this.inventario.getItem("Espada") != null;
    }

    private boolean possuiArcoEFlecha(){
        return this.inventario.getItem("Arco") != null &&
        this.inventario.getItem("Flecha") != null;
    }

    private boolean possuiItensDeAtaque(){
        return this.possuiEspada() || this.possuiArcoEFlecha();
    }

    public void atacar(PersonagemDaTerraMedia personagem) {
        if(possuiItensDeAtaque()){
            personagem.receberDano(this);
        }else{            
            this.status = Status.FUGITIVO;
        }
    }

    public int getDano(){
        if(this.possuiEspada()){
            return 12;
        }else if(this.possuiArcoEFlecha()){
            this.inventario.debitarQuantidadeDoItem("Flecha");
            return 8;
        }
        return 0;
    }

    public Status getStatus(){
        return this.status;
    }

    public int getVida(){
        return this.vida;
    }
}