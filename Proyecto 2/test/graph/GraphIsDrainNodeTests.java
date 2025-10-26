package graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import graph.exceptions.ElementNotPresentException;

class GraphIsDrainNodeTests {

	@Test
	void testIsDrainNodeNullElement() {
		MatrixGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
		assertThrows(NullPointerException.class, () -> graph.isDrainNode(null));
		assertThrows(NullPointerException.class, () -> graph.isDrainNode(null));
	}
	
	@Test
	void testIsDrainNodeNonExistentNodes() {
		MatrixGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
		assertThrows(ElementNotPresentException.class, () -> graph.isDrainNode('z'));
		assertThrows(ElementNotPresentException.class, () -> graph.isDrainNode('k'));
	}
	
	@Test
	void testIsDrainNodeTrue() {
		MatrixGraph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
		assertTrue(graph.isDrainNode('D'));
	}
	
	@Test
	void testIsDrainNodeFalse() {
		MatrixGraph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
		assertFalse(graph.isDrainNode('B'));
	}


}
