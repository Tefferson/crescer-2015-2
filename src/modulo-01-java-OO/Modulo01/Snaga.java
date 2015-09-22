public class Snaga extends Orc
{
    public Snaga(){
        super();
        this.vida = 70;
        this.adicionarItem(new Item("Arco", 1));
        this.adicionarItem(new Item("Flecha", 5));
    }
}
