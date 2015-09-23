
/**
 * Write a description of class ElfoNoturno here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ElfoNoturno extends Elfo {
    public ElfoNoturno(String nome) {
        super(nome);
    }

    public ElfoNoturno(String nome, int flechas) {
        super(nome, flechas);
    }

    public void atirarFlecha(Dwarf dwarf) {
        super.atirarFlecha(dwarf);
        super.experiencia += 2;
        super.receberDano(super.vida*.05);
    }
}
