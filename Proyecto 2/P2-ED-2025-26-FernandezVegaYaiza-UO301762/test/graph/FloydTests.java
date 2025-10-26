package graph;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import graph.exceptions.ElementNotPresentException;

class FloydTests {

	private final double INF = MatrixGraph.INFINITY;
	private final int EMP = MatrixGraph.EMPTY;
	private static final double DELTA = 0.0001;

	@Test
	void testEmptyGraph() {
		// A graph with no nodes
		MatrixGraph<Character> graph = new MatrixGraph<>(10);

		// Floyd should return false and the matrices should be initialized but empty.
		assertFalse(graph.floyd());
		assertNotNull(graph.getFloydCostsA());
		assertNotNull(graph.getFloydPathsP());

		assertEquals(graph.getEdges().length, graph.getFloydCostsA().length);
		assertEquals(graph.getEdges()[0].length, graph.getFloydCostsA()[0].length);
		assertEquals(graph.getEdges().length, graph.getFloydPathsP().length);
		assertEquals(graph.getEdges()[0].length, graph.getFloydPathsP()[0].length);
	}

	@Test
	void testIsolatedNodesGraph() {
		// Graph with isolated nodes A, B, C, D
		MatrixGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();

		// The Floyd matrices should show infinite costs between all nodes and no paths.
		// The last rows and columns should remain zero (for unused capacity).
		assertTrue(graph.floyd());

		double[][] expectedFloydCostsA = { //
				{ 0.0, INF, INF, INF, 0.0, 0.0 }, // A
				{ INF, 0.0, INF, INF, 0.0, 0.0 }, // B
				{ INF, INF, 0.0, INF, 0.0, 0.0 }, // C
				{ INF, INF, INF, 0.0, 0.0, 0.0 }, // D
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, // -
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } // -
		};// ABCD--

		int[][] expectedFloydPathsP = { //
				{ EMP, EMP, EMP, EMP, 0, 0 }, // A
				{ EMP, EMP, EMP, EMP, 0, 0 }, // B
				{ EMP, EMP, EMP, EMP, 0, 0 }, // C
				{ EMP, EMP, EMP, EMP, 0, 0 }, // D
				{ 0, 0, 0, 0, 0, 0 }, // -
				{ 0, 0, 0, 0, 0, 0 } // -
		};// ABCD--

		assertArrayEquals(expectedFloydCostsA, graph.getFloydCostsA());
		assertArrayEquals(expectedFloydPathsP, graph.getFloydPathsP());

	}

	@Test
	void testBasicTraversalGraph() {
		// Graph with connected nodes A, B, C, D
		MatrixGraph<Character> graph = GraphTestHelper.createBasicTraversalCharacterGraph();

		// The Floyd matrices should show the minimum costs and paths between all nodes.
		// The unused rows and columns should remain at 0.
		assertTrue(graph.floyd());

		double[][] expectedFloydCostsA = { //
				{ 0.0, INF, 1.0, INF, 0.0, 0.0 }, // A
				{ 1.0, 0.0, 2.0, INF, 0.0, 0.0 }, // B
				{ INF, INF, 0.0, INF, 0.0, 0.0 }, // C
				{ 31.0, 30.0, 32.0, 0.0, 0.0, 0.0 }, // D
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, // -
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } // -
		};// ABCD--

		int[][] expectedFloydPathsP = { //
				{ EMP, EMP, EMP, EMP, 0, 0 }, // A
				{ EMP, EMP, 0, EMP, 0, 0 }, // B
				{ EMP, EMP, EMP, EMP, 0, 0 }, // C
				{ 1, EMP, 1, EMP, 0, 0 }, // D
				{ 0, 0, 0, 0, 0, 0 }, // -
				{ 0, 0, 0, 0, 0, 0 } // -
		};// ABCD--

		assertArrayEquals(expectedFloydCostsA, graph.getFloydCostsA());
		assertArrayEquals(expectedFloydPathsP, graph.getFloydPathsP());
	}

	@Test
	void testComplexTraversalGraph() {
		// Graph with complex connections A, B, C, D, E
		MatrixGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

		// The Floyd matrices should show the minimum costs and paths between all nodes.
		// The unused rows and columns should remain at 0.
		assertTrue(graph.floyd());

		double[][] expectedFloydCostsA = { //
				{ 0.0, 1.0, 3.0, 6.0, 9.0, 0.0 }, // A
				{ 10.0, 0.0, 2.0, 5.0, 8.0, 0.0 }, // B
				{ 8.0, 9.0, 0.0, 3.0, 6.0, 0.0 }, // C
				{ 5.0, 6.0, 8.0, 0.0, 4.0, 0.0 }, // D
				{ INF, INF, INF, INF, 0.0, 0.0 }, // E
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } // -
		};// ABCDE-

		int[][] expectedFloydPathsP = { //
				{ EMP, EMP, 1, 2, 2, 0 }, // A
				{ 3, EMP, EMP, 2, 2, 0 }, // B
				{ 3, 3, EMP, EMP, EMP, 0 }, // C
				{ EMP, 0, EMP, EMP, EMP, 0 }, // D
				{ EMP, EMP, EMP, EMP, EMP, 0 }, // E
				{ 0, 0, 0, 0, 0, 0 } // -
		};// ABCDE-

		assertArrayEquals(expectedFloydCostsA, graph.getFloydCostsA());
		assertArrayEquals(expectedFloydPathsP, graph.getFloydPathsP());
	}

	@Test
	void testPrintFloydPathExceptions() {
		MatrixGraph<Character> graph = GraphTestHelper.createBasicTraversalCharacterGraph();

		// It should not be needed to call floyd() before checking exceptions,
		// printFloydPath does it automatically if not done yet.
		assertThrows(NullPointerException.class, () -> graph.printFloydPath(null, 'A'));
		assertThrows(NullPointerException.class, () -> graph.printFloydPath('A', null));
		assertThrows(NullPointerException.class, () -> graph.printFloydPath(null, null));

		// Node not in graph
		assertThrows(ElementNotPresentException.class, () -> graph.printFloydPath('X', 'A'));
		assertThrows(ElementNotPresentException.class, () -> graph.printFloydPath('A', 'X'));
		assertThrows(ElementNotPresentException.class, () -> graph.printFloydPath('X', 'Y'));

	}

	@Test
	void testFloydPathBasicTraversalGraph() {
		// Graph with connected nodes A, B, C, D
		MatrixGraph<Character> graph = GraphTestHelper.createBasicTraversalCharacterGraph();

		// It should not be needed to call floyd() before checking exceptions,
		// printFloydPath does it automatically if not done yet.

		String pathAtoC = graph.printFloydPath('A', 'C');
		assertEquals("AC", pathAtoC);

		String pathBtoC = graph.printFloydPath('B', 'C');
		assertEquals("BAC", pathBtoC);

		String pathDtoA = graph.printFloydPath('D', 'A');
		assertEquals("DBA", pathDtoA);

		String pathDtoC = graph.printFloydPath('D', 'C');
		assertEquals("DBAC", pathDtoC);

		String pathCtoA = graph.printFloydPath('C', 'A');
		assertEquals("C_NO_PATH_FOUND_TO_A", pathCtoA);
	}

	@Test
	void testFloydPathComplexTraversalGraph() {
		// Graph with complex connections A, B, C, D, E
		MatrixGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

		// It should not be needed to call floyd() before checking exceptions,
		// printFloydPath does it automatically if not done yet.

		String pathAtoA = graph.printFloydPath('A', 'A');
		assertEquals("AA", pathAtoA);

		String pathAtoE = graph.printFloydPath('A', 'E');
		assertEquals("ABCE", pathAtoE);

		String pathBtoE = graph.printFloydPath('B', 'E');
		assertEquals("BCE", pathBtoE);

		String pathBtoA = graph.printFloydPath('B', 'A');
		assertEquals("BCDA", pathBtoA);

		String pathCtoE = graph.printFloydPath('C', 'E');
		assertEquals("CE", pathCtoE);

		String pathDtoA = graph.printFloydPath('D', 'A');
		assertEquals("DA", pathDtoA);

		String pathEtoA = graph.printFloydPath('E', 'A');
		assertEquals("E_NO_PATH_FOUND_TO_A", pathEtoA);
	}

	@Test
	void testFloydRemoveEdge() {

		MatrixGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

		// It should not be needed to call floyd() before checking exceptions,
		// printFloydPath does it automatically if not done yet.

		String pathAtoA = graph.printFloydPath('A', 'A');
		assertEquals("AA", pathAtoA);

		String pathAtoE = graph.printFloydPath('A', 'E');
		assertEquals("ABCE", pathAtoE);

		graph.removeEdge('B', 'C');

		// After the removal we shouldn't need to explicitly call floyd() again.
		// printFloydPath should detect that floyd is not up to date and call it.

		pathAtoE = graph.printFloydPath('A', 'E');
		assertEquals("ABE", pathAtoE);

		String pathBtoE = graph.printFloydPath('B', 'E');
		assertEquals("BE", pathBtoE);

		String pathBtoA = graph.printFloydPath('B', 'A');
		assertEquals("B_NO_PATH_FOUND_TO_A", pathBtoA);

		String pathCtoE = graph.printFloydPath('C', 'E');
		assertEquals("CE", pathCtoE);

		String pathDtoA = graph.printFloydPath('D', 'A');
		assertEquals("DA", pathDtoA);

		String pathEtoA = graph.printFloydPath('E', 'A');
		assertEquals("E_NO_PATH_FOUND_TO_A", pathEtoA);

	}

	@Test
	void testFloydRemoveNode() {
		MatrixGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

		assertTrue(graph.floyd());

		double[][] expectedFloydCostsA = { //
				{ 0.0, 1.0, 3.0, 6.0, 9.0, 0.0 }, // A
				{ 10.0, 0.0, 2.0, 5.0, 8.0, 0.0 }, // B
				{ 8.0, 9.0, 0.0, 3.0, 6.0, 0.0 }, // C
				{ 5.0, 6.0, 8.0, 0.0, 4.0, 0.0 }, // D
				{ INF, INF, INF, INF, 0.0, 0.0 }, // E
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } // -
		};// ABCDE-

		int[][] expectedFloydPathsP = { //
				{ EMP, EMP, 1, 2, 2, 0 }, // A
				{ 3, EMP, EMP, 2, 2, 0 }, // B
				{ 3, 3, EMP, EMP, EMP, 0 }, // C
				{ EMP, 0, EMP, EMP, EMP, 0 }, // D
				{ EMP, EMP, EMP, EMP, EMP, 0 }, // E
				{ 0, 0, 0, 0, 0, 0 } // -
		};// ABCDE-

		assertArrayEquals(expectedFloydCostsA, graph.getFloydCostsA());
		assertArrayEquals(expectedFloydPathsP, graph.getFloydPathsP());

		graph.removeNode('C');
		graph.floyd();

		// The new matrices should be updated
		// The last rows and columns should remain the same after removal.
		double[][] newExpectedFloydCostsA = { //
				{ 0.0, 1.0, 11.0, INF, 9.0, 0.0 }, // A
				{ INF, 0.0, 10.0, INF, 8.0, 0.0 }, // B
				{ INF, INF, 0.0, INF, 6.0, 0.0 }, // E
				{ 5.0, 6.0, 4.0, 0.0, 4.0, 0.0 }, // D
				{ INF, INF, INF, INF, 0.0, 0.0 }, // -
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } // -
		};// ABED--

		int[][] newExpectedFloydPathsP = { //
				{ EMP, EMP, 1, EMP, 2, 0 }, // A
				{ EMP, EMP, EMP, EMP, 2, 0 }, // B
				{ EMP, EMP, EMP, EMP, EMP, 0 }, // E
				{ EMP, 0, EMP, EMP, EMP, 0 }, // D
				{ EMP, EMP, EMP, EMP, EMP, 0 }, // -
				{ 0, 0, 0, 0, 0, 0 } // -
		};// ABED--

		assertArrayEquals(newExpectedFloydCostsA, graph.getFloydCostsA());
		assertArrayEquals(newExpectedFloydPathsP, graph.getFloydPathsP());
	}

	@Test
	void testGetMinimumCostPathFloyd() {
		// Graph with complex connections A, B, C, D, E
		MatrixGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

		// It should not be needed to call floyd() explicitly.
		// minimumCostPathFloyd should do it automatically if not done yet.
		assertThrows(ElementNotPresentException.class, () -> graph.minimumCostPathFloyd('X', 'A'));
		assertThrows(ElementNotPresentException.class, () -> graph.minimumCostPathFloyd('A', 'X'));
		assertThrows(ElementNotPresentException.class, () -> graph.minimumCostPathFloyd('X', 'Y'));
		assertThrows(NullPointerException.class, () -> graph.minimumCostPathFloyd(null, 'A'));
		assertThrows(NullPointerException.class, () -> graph.minimumCostPathFloyd('A', null));
		assertThrows(NullPointerException.class, () -> graph.minimumCostPathFloyd(null, null));

		assertEquals(6.0, graph.minimumCostPathFloyd('A', 'D'), DELTA);
		assertEquals(5.0, graph.minimumCostPathFloyd('B', 'D'), DELTA);
		assertEquals(3.0, graph.minimumCostPathFloyd('C', 'D'), DELTA);
		assertEquals(0.0, graph.minimumCostPathFloyd('D', 'D'), DELTA);
	}

}
