/**
 * Representa objetos do tipo Elfo.
 */
public class Elfo extends PersonagemDaTerraMedia{
    private int flechas;

    public Elfo(String nome, int flechas) {
        super(nome);
        this.vida = 100;
        this.nome = nome;
        this.flechas = flechas;
    }

    public Elfo(String nome) {
        this(nome, 42);
    }

    public void atirarFlecha(Dwarf dwarf) {
        flechas--;
        experiencia++;
        dwarf.receberFlechada();
    }

    public int getFlechas() {
        return this.flechas;
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
}
