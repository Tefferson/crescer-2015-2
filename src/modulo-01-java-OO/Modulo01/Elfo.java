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

    public String getNome() {
        return this.nome;
    }

    public int getFlechas() {
        return this.flechas;
    }

    public int getExperiencia(){
        return this.experiencia;
    }

    public String toString() {
        return "Legolas possui "+this.flechas+
        " flechas e "+this.experiencia+
        " níveis de experiência.";
    }
}
