
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class NossoJogoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class NossoJogoTest {
    @Test
    public void contadorDoElfoÉ5() {
        int count = NossoJogo.getContagemDeElfos();
        Elfo elfo1 = new Elfo("elfo");
        Elfo elfo2 = new Elfo("elfo");
        Elfo elfo3 = new Elfo("elfo");
        Elfo elfo4 = new Elfo("elfo");
        Elfo elfo5 = new Elfo("elfo");

        int esperado = 5;

        assertEquals(esperado, NossoJogo.getContagemDeElfos()-count);
    }
}
