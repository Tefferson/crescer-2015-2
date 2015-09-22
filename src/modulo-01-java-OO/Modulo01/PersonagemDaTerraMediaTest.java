
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
    public void personagemNasceNomeVivoSemExperienciaEUmInventarioVazio(){
        PersonagemDaTerraMedia personagem = new PersonagemDaTerraMedia("Personagem");

        assertEquals(Status.VIVO, personagem.getStatus());
        assertEquals("Personagem", personagem.getNome());
        assertEquals(0, personagem.getExperiencia());
        assertEquals(0, personagem.getInventario().getItens().size());
    }

    @Test
    public void dwarfAtacaOrc(){
        PersonagemDaTerraMedia personagem = new Elfo("Personagem");
        Orc orc = new Orc();

        personagem.atacar(orc);

        assertEquals(17, orc.getVida());
    }

    @Test
    public void elfoAtacaOrc(){
        PersonagemDaTerraMedia personagem = new Dwarf("Personagem");
        Orc orc = new Orc();

        personagem.atacar(orc);

        assertEquals(15, orc.getVida());
    }
}
