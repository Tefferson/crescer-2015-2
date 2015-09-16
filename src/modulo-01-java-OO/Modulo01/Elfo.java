<<<<<<< HEAD

=======
/**
 * Representa objetos do tipo Elfo.
 */
>>>>>>> ad6150a3d6a02652fc4cf34571dd0f5bd709fd29
public class Elfo {

    private String nome;
<<<<<<< HEAD
    private int flechas;
    private int experiencia;

    public Elfo(String nome, int flechas) {
        this.nome = nome;
        this.flechas = flechas;
        this.experiencia = 0;
    }

=======
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
    
>>>>>>> ad6150a3d6a02652fc4cf34571dd0f5bd709fd29
    public Elfo(String nome) {
        this(nome, 42);
    }

<<<<<<< HEAD
    public void atirarFlechaRefactory() {
        if(acertarFlecha()) {
=======
    /* PascalCase (C#, VB.NET)
     *      public void AtirarFlechaDeFogo
     * camelCase (Java, JavaScript)
     *      public void atirarFlechaDeFogo
    */
   
    public void atirarFlecha() {
        flechas--;
        experiencia++;
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
>>>>>>> ad6150a3d6a02652fc4cf34571dd0f5bd709fd29
            experiencia++;
        }
        flechas--;
    }
<<<<<<< HEAD

    private boolean acertarFlecha() {
        return ((int)(Math.random() * 100 % 2)) == 0;
    }
=======
>>>>>>> ad6150a3d6a02652fc4cf34571dd0f5bd709fd29
}
