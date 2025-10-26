package graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import graph.exceptions.ElementNotPresentException;

class GraphIsIsolatedNodeTests {

	@Test
	void testIsIsolatedNodeNullElement() {
		MatrixGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
		assertThrows(NullPointerException.class, () -> graph.isIsolatedNode(null));
		assertThrows(NullPointerException.class, () -> graph.isIsolatedNode(null));
	}
	
	@Test
	void testIsIsolatedNodeNonExistentNodes() {
		MatrixGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
		assertThrows(ElementNotPresentException.class, () -> graph.isIsolatedNode('z'));
		assertThrows(ElementNotPresentException.class, () -> graph.isIsolatedNode('k'));
	}
	
	@Test
	void testIsIsolatedNodeTrue() {
		MatrixGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
		assertTrue(graph.isIsolatedNode('A'));
	}
	
	@Test
	void testIsIsolatedNodeFalse() {
		MatrixGraph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
		assertFalse(graph.isIsolatedNode('B'));
	}


}
