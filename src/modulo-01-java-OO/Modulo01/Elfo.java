/**
 * Representa objetos do tipo Elfo.
 */
public class Elfo {

    private String nome;
    private int flechas, experiencia;
   
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

    public void atirarFlecha(Dwarf alvo) {
        flechas--;
        experiencia++;
        alvo.receberFlechada();
    }
    
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
    
    public int getFlechas() {
        return this.flechas;
    }
    
}
