

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class UrukHaiTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UrukHaiTest
{
    @Test
    public void urukHaiNasceCom150DeVidaEUmaEspada(){
        Orc orc = new UrukHai();
        
        assertEquals(150, orc.getVida());
        assertEquals(12, orc.agirNoAtaque());
    }
}
