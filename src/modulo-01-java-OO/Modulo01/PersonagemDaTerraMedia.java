
/**
 * Write a description of class PersonagemDaTerraMedia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PersonagemDaTerraMedia
{
    protected String nome;
    protected double vida;
    protected int experiencia;
    protected Status status;
    protected Inventario inventario;

    public PersonagemDaTerraMedia(){
        this.inventario = new Inventario();
        this.status = Status.VIVO;
        this.experiencia = 0;
    }

    public PersonagemDaTerraMedia(String nome){
        this();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getExperiencia() {
        return this.experiencia;
    }

    public double getVida(){
        return this.vida;
    }

    public void receberDano(Orc orc){
        this.receberDano(orc.getDano());
    }

    public Inventario getInventario(){
        return this.inventario;
    }

    public Status getStatus(){
        return this.status;
    }

    protected void receberDano(double dano){
        if(this.status!=Status.MORTO){
            if(dano<this.vida && vida-dano > 1){
                this.status = Status.FERIDO;
                this.vida-=dano;
            }else{
                this.status = Status.MORTO;
                this.vida=0;
            }
        }
    }

    public void adicionarItem(Item item){
        this.inventario.adicionarItem(item);
    }

    public void perderItem(Item item){
        this.inventario.perderItem(item);
    }

    public void atacar(Orc orc){
        orc.receberDano(this);
    }
}
