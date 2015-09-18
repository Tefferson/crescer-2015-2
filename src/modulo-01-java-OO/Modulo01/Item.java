public class Item
{
    private String descricao;
    private int quantidade;
    public Item(String descricao, int quantidade){
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public boolean equals(Object obj){
        Item item = (Item)obj;
        return this.descricao.equals(item.getDescricao()) &&
        this.quantidade == item.getQuantidade();
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
