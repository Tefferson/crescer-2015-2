
public class Elfo {

    private String nome;
    private int flechas;
    private int experiencia;

    public Elfo(String nome, int flechas) {
        this.nome = nome;
        this.flechas = flechas;
        this.experiencia = 0;
    }

    public Elfo(String nome) {
        this(nome, 42);
    }

    public void atirarFlechaRefactory() {
        if(acertarFlecha() == true) {
            experiencia++;
        }
        flechas--;
    }

    private boolean acertarFlecha() {
        return ((int)(Math.random() * 100 % 2)) == 0;
    }
}
