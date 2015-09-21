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

    public void adicionarMilDeCadaItem(){
        int size = this.itens.size();
        for(int i=0;i<size;i++){
            itens.get(i).adicionar1000();
        }
    }
    
    public void adicionarMilVezesMaisASomaDeCadaItem(){
        int size = this.itens.size();
        for(int i=0;i<size;i++){
            itens.get(i).adicionarMilVezesMaisASomaDeCadaItem();
        }
    }

    public Item getItemComMaiorQuantidade(){
        int maiorQuantidade=Integer.MIN_VALUE, indice=0, size = itens.size();
        for(int i=0;i<size;i++){
            if(itens.get(i).getQuantidade()>maiorQuantidade){
                maiorQuantidade = itens.get(i).getQuantidade();
                indice=i;
            }
        }
        return itens.get(indice);
    }

    public void ordenarItens(){
        ArrayList<Item> listaOrdenada = new ArrayList<>();
        int size = this.itens.size();
        for(int i=0;i<size;i++){
            Item item = this.getItemComMaiorQuantidade();
            listaOrdenada.add(0,item);
            this.itens.remove(item);
        }
        this.itens = listaOrdenada;
    }
}
