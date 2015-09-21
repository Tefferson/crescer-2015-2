/**
 * Escreva a descrição da classe Dwarf aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class IrishDwarf extends Dwarf
{
    public IrishDwarf(String nome){
        super(nome);
    }

    public IrishDwarf(String nome, DataTerceiraEra dataNascimento){
        super(nome, dataNascimento);
    }

    public void tentarSorte(){
        if(this.getNumeroSorte()==-3333.0){
            this.inventario.adicionarMilVezesMaisASomaDeCadaItem();
        }
    }
}    
