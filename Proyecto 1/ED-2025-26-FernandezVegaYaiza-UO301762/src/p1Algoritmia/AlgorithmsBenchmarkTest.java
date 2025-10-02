package p1Algoritmia;

import org.junit.jupiter.api.Test;

/**
 * @version 2024-25
 */

class AlgorithmsBenchmarkTest {

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el
	 * metodo "lineal" de la clase "Algorithms"
	 */
	@Test
	void testLinearBenchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("lineal.txt", 0, 20, 5, "p1Algoritmia.Algorithms", "lineal");
	}

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el
	 * metodo "cuadratica" de la clase "Algorithms"
	 */
	@Test
	void testCuadraticaBenchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("cuadratica.txt", 0, 20, 5, "p1Algoritmia.Algorithms", "cuadratica");
	}

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el
	 * metodo "cubica" de la clase "Algorithms"
	 */
	@Test
	void testCubicaBenchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("cubica.txt", 0, 20, 5, "p1Algoritmia.Algorithms", "cubica");
	}

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el
	 * metodo "logaritmica" de la clase "Algorithms"
	 */
	@Test
	void testLogaritmicaBenchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("logaritmica.txt", 0, 20, 5, "p1Algoritmia.Algorithms", "logaritmica");
	}

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el
	 * metodo "powIter" de la clase "Algorithms"
	 */
	@Test
	void testPowIterBenchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("powIter.txt", 0, 20, 5, "p1Algoritmia.Algorithms", "powIter");
	}

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el
	 * metodo "powRec1" de la clase "Algorithms"
	 */
	@Test
	void testPowRec1Benchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("powRec1.txt", 0, 20, 5, "p1Algoritmia.Algorithms", "powRec1");
	}

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el
	 * metodo "powRec2" de la clase "Algorithms"
	 */
	@Test
	void testPowRec2Benchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("powRec2.txt", 0, 20, 5, "p1Algoritmia.Algorithms", "powRec2");
	}

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el
	 * metodo "powRec3" de la clase "Algorithms"
	 */
	@Test
	void testPowRec3Benchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("powRec3.txt", 0, 15, 5, "p1Algoritmia.Algorithms", "powRec3");
	}

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el
	 * metodo "powRec4" de la clase "Algorithms"
	 */
	@Test
	void testPowRec4Benchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("powRec4.txt", 0, 20, 5, "p1Algoritmia.Algorithms", "powRec4");
	}
}
