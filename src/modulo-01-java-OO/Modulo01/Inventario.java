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
        String nomes = "";
        for(Item item : this.itens){
            nomes += ","+item.getDescricao();
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
            itens.get(i).adicionarMilVezesMaisASomaDaQuantidadeDeCadaItem();
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

    public boolean equals(Object obj){
        return ((Inventario)obj).getItens().equals(this.itens);
    }

    public Item getItem(String descricao){
        for(Item item : this.itens){
            if(item.getDescricao()!=null){
                if(item.getDescricao().equals(descricao)){
                    return item;
                }
            }else{
                if(descricao==null){            
                    return item;
                }
            }
        }
        return null;
    }

    public void debitarQuantidadeDoItem(String descricao){
        Item item = this.getItem(descricao);
        if(item.getQuantidade()==1){
            item.decrementarQuantidade();
            this.itens.remove(item);
        }else if(item.getQuantidade()>0){
            item.decrementarQuantidade();
        }
    }

}
