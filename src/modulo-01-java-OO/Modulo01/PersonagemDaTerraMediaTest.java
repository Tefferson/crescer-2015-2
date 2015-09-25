
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PersonagemDaTerraMediaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PersonagemDaTerraMediaTest
{
    @Test
    public void dwarfAtacaOrc(){
        PersonagemDaTerraMedia personagem = new Elfo("Personagem");
        Orc orc = new Orc();

        personagem.atacar(orc);

        assertEquals(15, orc.getVida(), 0);
    }

    @Test
    public void elfoAtacaOrc(){
        PersonagemDaTerraMedia personagem = new Dwarf("Personagem");
        Orc orc = new Orc();

        personagem.atacar(orc);

        assertEquals(15, orc.getVida(), 0);
    }
}
