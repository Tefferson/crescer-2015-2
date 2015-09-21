
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ItemTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ItemTest
{
    @Test
    public void itemTemDescricaoPocaoEQuantidade5(){
        Item itemEsperado = new Item("poção",5);
        Item pocao = new Item("poção",5);
        assertEquals(itemEsperado, pocao);
    }
    
    @Test
    public void shimbalaieCorrigido(){
        Item item = new Item("gravata", 2);
        
        item.shimbalaie();
        
        assertEquals(new Item("gravata", 3002), item);
    }
}
