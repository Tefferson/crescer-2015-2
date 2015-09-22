public class UrukHai extends Orc
{
    public UrukHai(){
        super();
        this.vida = 150;
        this.adicionarItem(new Item("Espada", 1));
    }

    public void receberFlechada(Dwarf dwarf) {
        gerarDano(5);
    }
}
