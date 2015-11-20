package br.com.cwi.crescer.maven;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Matematica {

	public int somar(int parcelaA, int parcelaB) {
		return parcelaA + parcelaB;
	}

	public final static BigDecimal fiboSum(int n) {

		BigDecimal sqrtOf5 = new BigDecimal("2.23606797749979");

		BigDecimal phi = new BigDecimal("1.618033988749895");

		if (n > 0) {
			n += 2;
			return (phi.pow(n).divide(sqrtOf5, 150, RoundingMode.HALF_DOWN).subtract(BigDecimal.ONE));
		}

		return BigDecimal.ONE;
	}

	public static void main(String[] args) {
		int n = 50;
		String resultado = Matematica.fiboSum(n).toString();

		System.out.println("fibosum de " + n + " = " + resultado);
	}

}
