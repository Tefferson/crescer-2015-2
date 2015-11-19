package br.com.cwi.crescer.maven;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatematicaTest {

	@Test
	public void somaEhCinco() {
		
		final int resultado = new Matematica().somar(2, 3);

		assertEquals(5, resultado);

	}

}
