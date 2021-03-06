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

    public void adicionar1000(){
        this.quantidade += 1000;
    }

    public void adicionarMilVezesMaisASomaDaQuantidadeDeCadaItem(){
        this.quantidade += 1000*(quantidade+(quantidade>0?1:-1))*Math.abs(quantidade)/2;
    }

    public void shimbalaie() {
        int pa = this.quantidade * (this.quantidade + 1) / 2;
        this.quantidade += (1000 * pa);
    }

    public void decrementarQuantidade(){
        this.quantidade--;
    }
}
