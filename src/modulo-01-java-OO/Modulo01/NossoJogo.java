
public class NossoJogo
{
    public final static String NOME = "Bahhh of the rings";
    private static int CONTADOR_DE_ELFOS = 0;
    public static void main(String[] args){
        System.out.println("Olá, bem-vindo ao "+NOME+".");
    }

    public static void incrementarContadorDeElfos(){
        CONTADOR_DE_ELFOS++;    
    }    

    public static int getContagemDeElfos(){
        return CONTADOR_DE_ELFOS;
    }
}