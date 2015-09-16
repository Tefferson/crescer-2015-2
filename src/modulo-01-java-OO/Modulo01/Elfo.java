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

<<<<<<< HEAD
    public void atirarFlecha(Dwarf alvo) {
        flechas--;
        experiencia++;
        alvo.receberFlechada();
=======
    /* PascalCase (C#, VB.NET)
     *      public void AtirarFlechaDeFogo
     * camelCase (Java, JavaScript)
     *      public void atirarFlechaDeFogo
    */
   
    public void atirarFlecha(Dwarf dwarf) {
        flechas--;
        experiencia++;
        dwarf.receberFlechada();
>>>>>>> 365f58ceeb9aa13772ec7cca7564d807fa7efa6e
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
<<<<<<< HEAD
    /*
    public void setNome(String novoNome){
        this.nome = novoNome;
    }
    */
=======
    
>>>>>>> 365f58ceeb9aa13772ec7cca7564d807fa7efa6e
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
