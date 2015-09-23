

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SnagaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SnagaTest
{
   @Test
    public void snagaNasceCom70DeVidaArcoEFlechas(){
        Orc orc = new Snaga();
        
        assertEquals(70, orc.getVida(), 0);
        assertEquals(8, orc.getDano(), 0);
    }
}
