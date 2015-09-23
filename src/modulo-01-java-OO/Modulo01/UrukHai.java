public class UrukHai extends Orc
{
    public UrukHai(String nome){
        super(nome);
        this.vida = 150;
        this.adicionarItem(new Item("Espada", 1));
        this.adicionarItem(new Item("Escudo Uruk-Hai", 1));
    }
}
