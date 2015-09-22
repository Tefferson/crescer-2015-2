
/**
 * Write a description of class PersonagemDaTerraMedia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PersonagemDaTerraMedia
{
    protected String nome;
    protected int vida;
    protected int experiencia;
    protected Status status;
    protected Inventario inventario;

    public PersonagemDaTerraMedia(String nome){
        this.nome = nome;
        this.inventario = new Inventario();
        this.status = Status.VIVO;
        this.experiencia = 0;
    }
    
    public String getNome() {
        return nome;
    }

    public int getExperiencia() {
        return this.experiencia;
    }

    public int getVida(){
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
    
    private void receberDano(int dano){
        if(this.status!=Status.MORTO){
            if(dano<this.vida){
                this.status = Status.FERIDO;
                this.vida-=dano;
            }else{
                this.status = Status.MORTO;
                this.vida=0;
            }
        }
    }
}
