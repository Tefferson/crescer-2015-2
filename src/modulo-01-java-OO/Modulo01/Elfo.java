
public class Elfo {

    private String nome;
    private int flechas;
    private int experiencia;

    public Elfo(String n, int flech) {
        inicializarElfo(n, flech);
    }

    public Elfo(String n) {
        inicializarElfo(n, 42);
    }

    private void inicializarElfo(String n, int flech) {
        nome = n;
        flechas = flech;
        experiencia = 0;
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
