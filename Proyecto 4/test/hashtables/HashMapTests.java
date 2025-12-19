package hashtables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTests {

	HashMap<String, Integer> map;

	@BeforeEach
	void setUp() {
		map = new HashMap<>(5, HashStrategy.LINEAR_PROBING);
	}

	@Test
	void testPutAndGet() {
		assertTrue(map.put("A", 1));
		assertTrue(map.put("B", 2));

		assertEquals(1, map.get("A"));
		assertEquals(2, map.get("B"));
		assertNull(map.get("C"));
	}

	@Test
	void testUpdateValue() {
		map.put("A", 1);
		assertFalse(map.put("A", 10));
		assertEquals(10, map.get("A"));
	}

	@Test
	void testRemoveKey() {
		map.put("X", 100);
		map.put("Y", 200);

		assertTrue(map.removeKey("X"));
		assertNull(map.get("X"));
		assertFalse(map.removeKey("Z"));
	}

	@Test
	void testPutNullKeyThrows() {
		assertThrows(NullPointerException.class, () -> map.put(null, 5));
	}

	@Test
	void testGetNullKeyThrows() {
		assertThrows(NullPointerException.class, () -> map.get(null));
	}

	@Test
	void testRemoveNullKeyThrows() {
		assertThrows(NullPointerException.class, () -> map.removeKey(null));
	}

	@Test
	void testElementCount() {
		assertEquals(0, map.getElementNumber());
		map.put("A", 1);
		map.put("B", 2);
		assertEquals(2, map.getElementNumber());
		map.removeKey("A");
		assertEquals(1, map.getElementNumber());
	}
}
