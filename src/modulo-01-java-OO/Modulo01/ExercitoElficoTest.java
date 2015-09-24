
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ExercitoElficoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ExercitoElficoTest
{
    @Test
    public void alistaElfoVerde(){    
        ExercitoElfico exercito = new ExercitoElfico();
        ElfoVerde esperado = new ElfoVerde("elfo");
        exercito.alistarElfo(esperado);
                
        assertEquals(esperado, exercito.getElfo("elfo"));
    }
}
