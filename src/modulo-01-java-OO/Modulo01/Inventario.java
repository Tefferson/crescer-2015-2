import java.util.ArrayList;
public class Inventario
{
    private ArrayList<Item> itens;

    public Inventario(){
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item){
        this.itens.add(item);
    }

    public void perderItem(Item item){
        this.itens.remove(item);
    }

    public ArrayList<Item> getItens(){
        return this.itens;
    }
}
