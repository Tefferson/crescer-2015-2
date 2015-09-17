
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataTerceiraEraTest
{
    @Test
    public void dia2deSetembroDe1994(){
        DataTerceiraEra bday= new DataTerceiraEra(2,9,1994);
        assertEquals(2, bday.getDia());
        assertEquals(9, bday.getMes());
        assertEquals(1994, bday.getAno());
    }

    @Test
    public void ano2000ÉBissexto(){
        boolean ehBissexto = new DataTerceiraEra(1,1,2000).ehBissexto();
        assertTrue(ehBissexto);
    }

    @Test
    public void ano2014NãoÉBissexto(){
        boolean ehBissexto = new DataTerceiraEra(1,1,2014).ehBissexto();
        assertFalse(ehBissexto);
    }
}
