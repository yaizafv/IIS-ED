package graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import graph.exceptions.ElementNotPresentException;

class GraphIsSourceNodeTests {

	@Test
	void testIsSourceNodeNullElement() {
		MatrixGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
		assertThrows(NullPointerException.class, () -> graph.isSourceNode(null));
		assertThrows(NullPointerException.class, () -> graph.isSourceNode(null));
	}
	
	@Test
	void testIsSourceNodeNonExistentNodes() {
		MatrixGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
		assertThrows(ElementNotPresentException.class, () -> graph.isSourceNode('z'));
		assertThrows(ElementNotPresentException.class, () -> graph.isSourceNode('k'));
	}
	
	@Test
	void testIsSourceNodeTrue() {
		MatrixGraph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
		assertTrue(graph.isSourceNode('A'));
	}
	
	@Test
	void testIsSourceNodeFalse() {
		MatrixGraph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
		assertFalse(graph.isSourceNode('B'));
	}


}
