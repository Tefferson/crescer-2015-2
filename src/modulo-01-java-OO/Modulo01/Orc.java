/**
 * Escreva a descrição da classe orc aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Orc extends PersonagemDaTerraMedia
{
    public Orc(String nome) {
        super(nome);
        this.vida = (int)(100 / 10 * 2.5);
    }

    public void receberDano(PersonagemDaTerraMedia personagem) {
        if(this.inventario.getItem("Escudo Uruk-Hai")!=null){
            this.receberDano(6);
        }else{            
            this.receberDano(10);
        }
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
}