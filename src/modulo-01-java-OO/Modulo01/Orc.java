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
    private Inventario inventario;
    private Status status;

    public Orc() {
        this.vida = (int)(100 / 10 * 2.5);;
        this.inventario = new Inventario();
        this.status = Status.VIVO;
    }

    public void gerarDano(int dano) {
        if(this.status!=Status.MORTO){
            if(dano<vida){
                this.vida -= dano;
            }else{
                this.vida=0;
                this.status = Status.MORTO;
            }
        }
    }

    public void receberFlechada(Elfo elfo) {
        this.gerarDano(8);
    }

    public void receberFlechada(Dwarf dwarf) {
        if(this.inventario.existeDescricaoItem("Escudo Uruk-Hai")){
            gerarDano(5);
        }else{            
            gerarDano(10);
        }
    }

    public void adicionarItem(Item novoItem) {
        this.inventario.adicionarItem(novoItem);
    }

    public void atacar(Dwarf dwarf) {
        dwarf.receberDano(this);
    }

    public void atacar(Elfo elfo) {
        elfo.receberDano(this);
    }

    public int agirNoAtaque(){
        if(inventario.existeDescricaoItem("Espada")){
            this.status = Status.VIVO;
            return 12;
        }else if(inventario.existeDescricaoItem("Arco")){
            int id = inventario.getIdByDescricao("Flecha");
            if(id>-1){
                if(this.inventario.decrementarQuantidade(id)){
                    this.status = Status.VIVO;
                    return 8;
                }
            }
        }
        this.status = Status.FUGITIVO;
        return 0;
    }

    public Status getStatus(){
        return this.status;
    }

    public int getVida(){
        return this.vida;
    }
}