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

    public String getDescricoesItens(){
        int size = this.itens.size();
        String nomes = "";
        for(int i=0;i<size;i++){
            nomes += ","+itens.get(i).getDescricao();
        }
        return nomes.replaceFirst(",","");
    }
}
