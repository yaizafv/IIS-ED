package hashtables.openhashtables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenHashTableTests {

	OpenHashTable<String> table;

	@BeforeEach
	void setUp() {
		table = new OpenHashTable<>(5);
	}

	@Test
	void testAddAndSearch() {
		assertTrue(table.add("A"));
		assertTrue(table.add("B"));
		assertFalse(table.add("A"));

		assertTrue(table.search("A"), "'A' debería existir");
		assertTrue(table.search("B"), "'B' debería existir");
		assertFalse(table.search("C"), "'C' no debería existir");
	}

	@Test
	void testRemove() {
		table.add("X");
		table.add("Y");
		assertTrue(table.remove("X"));
		assertFalse(table.search("X"));
		assertFalse(table.remove("Z"));
	}

	@Test
	void testAddNullThrows() {
		assertThrows(NullPointerException.class, () -> table.add(null));
	}

	@Test
	void testSearchNullThrows() {
		assertThrows(NullPointerException.class, () -> table.search(null));
	}

	@Test
	void testRemoveNullThrows() {
		assertThrows(NullPointerException.class, () -> table.remove(null));
	}

	@Test
	void testElementCount() {
		assertEquals(0, table.getElementNumber());
		table.add("A");
		table.add("B");
		assertEquals(2, table.getElementNumber());
		table.remove("A");
		assertEquals(1, table.getElementNumber());
	}
}
