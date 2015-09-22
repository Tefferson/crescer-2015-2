/**
 * Representa objetos do tipo Elfo.
 */
public class Elfo {
    private String nome;
    private int flechas, experiencia;
    private Status status;
    private int vida;

    public Elfo(String nome, int flechas) {
        this.vida = 80;
        this.nome = nome;
        this.flechas = flechas;
        this.status = Status.VIVO;
    }

    public Elfo(String nome) {
        this(nome, 42);
    }

    public void atirarFlecha(Dwarf dwarf) {
        flechas--;
        experiencia++;
        dwarf.receberFlechada();
    }

    public String getNome() {
        return nome;
    }

    public int getFlechas() {
        return this.flechas;
    }

    public int getExperiencia() {
        return this.experiencia;
    }

    public int getVida(){
        return this.vida;
    }
    
    public String toString() {

        boolean flechaNoSingular = Math.abs(this.flechas) == 1;
        boolean nivelNoSingular = Math.abs(this.experiencia) == 1;

        return String.format("%s possui %d %s e %d %s de experiência.",
            this.nome,
            this.flechas,
            flechaNoSingular ? "flecha" : "flechas",
            this.experiencia,
            nivelNoSingular ? "nível" : "níveis");
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
