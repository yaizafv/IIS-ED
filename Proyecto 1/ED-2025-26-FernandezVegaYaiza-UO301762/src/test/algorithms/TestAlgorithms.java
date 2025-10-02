package test.algorithms;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import p1Algoritmia.Algorithms;

class TestAlgorithms {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@Test
	void testEsParIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.esPar(-1));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.esPar(-2));
	}

	@Test
	void testEsPar() {
		assertEquals(Algorithms.esPar(0), true);
		assertEquals(Algorithms.esPar(2), true);
		assertEquals(Algorithms.esPar(1), false);
		assertEquals(Algorithms.esPar(13), false);
	}

	@Test
	void testFactorialIterativeIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.factorialIterative(-2));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.factorialIterative(-1));
	}

	@Test
	void testFactorialIterative() {
		assertEquals(Algorithms.factorialIterative(2), 2);
		assertEquals(Algorithms.factorialIterative(3), 6);
		assertEquals(Algorithms.factorialIterative(4), 24);
		assertEquals(Algorithms.factorialIterative(5), 120);
	}

	@Test
	void testFactorialRecIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.factorialRec(-3));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.factorialRec(-1));
	}

	@Test
	void testFactorialRec() {
		assertEquals(Algorithms.factorialRec(2), 2);
		assertEquals(Algorithms.factorialRec(3), 6);
		assertEquals(Algorithms.factorialRec(4), 24);
		assertEquals(Algorithms.factorialRec(5), 120);
	}

	@Test
	void testFibonacciIterIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.fibonacciIter(-5));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.fibonacciIter(-1));
	}

	@Test
	void testFibonacciIter() {
		assertEquals(Algorithms.fibonacciIter(0), 0);
		assertEquals(Algorithms.fibonacciIter(1), 1);
		assertEquals(Algorithms.fibonacciIter(2), 1);
		assertEquals(Algorithms.fibonacciIter(3), 2);
	}

	@Test
	void testFibonacciRecIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.fibonacciRec(-10));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.fibonacciRec(-3));
	}

	@Test
	void testFibonacciRec() {
		assertEquals(Algorithms.fibonacciRec(0), 0);
		assertEquals(Algorithms.fibonacciRec(1), 1);
		assertEquals(Algorithms.fibonacciRec(2), 1);
		assertEquals(Algorithms.fibonacciRec(3), 2);
	}

	@Test
	void testEsSimetricoIllegalArguments() {
		assertThrows(NullPointerException.class, () -> Algorithms.esSimetrico(null));
	}

	@Test
	void testEsSimetrico() {
		int[][] simetrica = { { 1, 2, 3 }, { 2, 4, 5 }, { 3, 5, 6 } };
		int[][] noSimetrica = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
		assertEquals(Algorithms.esSimetrico(simetrica), true);
		assertEquals(Algorithms.esSimetrico(noSimetrica), false);
	}

	@Test
	void testRestoIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.resto(0, 0));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.resto(-1, 1));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.resto(1, -2));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.resto(-5, -7));
	}

	@Test
	void testResto() {
		assertEquals(Algorithms.resto(1, 1), 0);
		assertEquals(Algorithms.resto(1, 2), 1);
		assertEquals(Algorithms.resto(2, 3), 2);
		assertEquals(Algorithms.resto(4, 2), 0);
	}

	@Test
	void testDivisionIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.division(0, 0));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.division(-8, 1));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.division(1, -9));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.division(-3, -4));
	}

	@Test
	void testDivision() {
		assertEquals(Algorithms.division(3, 3), 1);
		assertEquals(Algorithms.division(5, 3), 1);
		assertEquals(Algorithms.division(2, 3), 0);
		assertEquals(Algorithms.division(4, 2), 2);
	}

	@Test
	void testPowIterIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powIter(-1));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powIter(-4));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powIter(-9));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powIter(-77));
	}

	@Test
	void testPowIter() {
		assertEquals(Algorithms.powIter(0), 1);
		assertEquals(Algorithms.powIter(1), 2);
		assertEquals(Algorithms.powIter(2), 4);
		assertEquals(Algorithms.powIter(3), 8);
	}

	@Test
	void testPowRec1IllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec1(-2));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec1(-3));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec1(-10));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec1(-100));
	}

	@Test
	void testPowRec1() {
		assertEquals(Algorithms.powRec1(0), 1);
		assertEquals(Algorithms.powRec1(1), 2);
		assertEquals(Algorithms.powRec1(2), 4);
		assertEquals(Algorithms.powRec1(3), 8);
	}

	@Test
	void testPowRec2IllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec2(-123));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec2(-5));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec2(-4523));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec2(-24));
	}

	@Test
	void testPowRec2() {
		assertEquals(Algorithms.powRec2(0), 1);
		assertEquals(Algorithms.powRec2(10), 1024);
		assertEquals(Algorithms.powRec2(11), 2048);
		assertEquals(Algorithms.powRec2(12), 4096);
	}

	@Test
	void testPowRec3IllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec3(-54));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec3(-244));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec3(-45));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec3(-79));
	}

	@Test
	void testPowRec3() {
		assertEquals(Algorithms.powRec3(0), 1);
		assertEquals(Algorithms.powRec3(4), 16);
		assertEquals(Algorithms.powRec3(5), 32);
		assertEquals(Algorithms.powRec3(6), 64);
	}

	@Test
	void testPowRec4IllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec4(-457));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec4(-23));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec4(-65));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.powRec4(-21));
	}

	@Test
	void testPowRec4() {
		assertEquals(Algorithms.powRec4(0), 1);
		assertEquals(Algorithms.powRec4(7), 128);
		assertEquals(Algorithms.powRec4(8), 256);
		assertEquals(Algorithms.powRec4(9), 512);
	}

	@Test
	void testLinealIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.lineal(-457));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.lineal(-23));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.lineal(-65));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.lineal(-21));
	}

	@Test
	void testCuadraticaIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.cuadratica(-2));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.cuadratica(-3));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.cuadratica(-10));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.cuadratica(-100));
	}

	@Test
	void testCubicaIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.cubica(-54));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.cubica(-244));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.cubica(-45));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.cubica(-79));
	}

	@Test
	void testLogaritmicaIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> Algorithms.logaritmica(-1));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.logaritmica(-4));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.logaritmica(-9));
		assertThrows(IllegalArgumentException.class, () -> Algorithms.logaritmica(-77));
	}
}
