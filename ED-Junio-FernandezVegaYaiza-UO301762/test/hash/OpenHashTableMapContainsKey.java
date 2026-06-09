package hash;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OpenHashTableMapContainsKey {
	
	@Test
	public void containsKeyNull() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertThrows(IllegalArgumentException.class, () -> map.containsKey(null));
	}
	
	@Test
	public void containsKeyTrue() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		map.put("A", 1);
		assertTrue(map.containsKey("A"));
	}
	
	@Test
	public void containsKeyFalse() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertFalse(map.containsKey("A"));
	}
}
