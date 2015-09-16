/**
 * Representa objetos do tipo Elfo.
 */
public class Elfo {

    private String nome;
    private int flechas, experiencia;

    /* Type initializer
     * Executa antes de cada construtor
    {
    flechas = 42;
    }
     */
    public Elfo(String nome, int flechas) {
        this.nome = nome;
        this.flechas = flechas;
    }

    public Elfo(String nome, Integer flechas) {
        this(nome);
        if (flechas != null) {
            this.flechas = flechas;
        }
    }

    public Elfo(String nome) {
        this(nome, 42);
    }

    public void atirarFlecha() {
        flechas--;
        experiencia++;
        //experiencia += 1;
        //experiencia = experiencia + 1;
    }

    public void atirarFlecha(Dwarf alvo) {
        flechas--;
        experiencia++;
        alvo.receberFlechada();
        //experiencia += 1;
        //experiencia = experiencia + 1;
    }

    
    /*
     * 
     * public atirarFlechaRefactory(this.flechas, this.experiencia){
     *     if(boolean acertar == true){
     *         flechas--;
     *         experiencia++;
     *      }else{
     *          flechas--;
     *      }
     *  }
     */
    public void atirarFlechaRefactory(){
        boolean acertar = true;
        if (acertar) {
            experiencia++;
        }
        flechas--;
    }

    public String getNome() {
        return this.nome;
    }
    /*
    public void setNome(String novoNome){
        this.nome = novoNome;
    }
    */
    public int getFlechas() {
        return this.flechas;
    }
    /*
    public void setFlechas(int flechas){
        if(flechas>this.flechas)
            this.flechas = flechas;
    }
    */
}
