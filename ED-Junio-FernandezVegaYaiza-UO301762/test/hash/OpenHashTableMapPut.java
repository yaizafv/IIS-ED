package hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class OpenHashTableMapPut {
	
	@Test
	public void putKeyNull() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertThrows(IllegalArgumentException.class, () -> map.put(null, 2));
	}
	
	@Test
	public void putValueNull() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertThrows(IllegalArgumentException.class, () -> map.put("A", null));
	}
	
	@Test
	public void putBothNull() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertThrows(IllegalArgumentException.class, () -> map.put(null, null));
	}
	
	@Test
	public void putEntry() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertEquals(0, map.size());
		map.put("A", 1);
		assertEquals(1, map.size());
	}
	
	@Test
	public void putMultipleEntrys() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertEquals(0, map.size());
		map.put("A", 1);
		assertEquals(1, map.size());
		map.put("B", 2);
		assertEquals(2, map.size());
		map.put("C", 3);
		assertEquals(3, map.size());
	}
}
