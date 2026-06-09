package hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class OpenHashTableMapRemove {
	
	@Test
	public void removeKeyNull() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertThrows(IllegalArgumentException.class, () -> map.remove(null));
	}
	
	@Test
	public void removeEntry() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		map.put("A", 1);
		assertEquals(1, map.size());
		map.remove("A");
		assertEquals(0, map.size());
	}
	
	@Test
	public void removeMultipleEntrys() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		assertEquals(3, map.size());
		map.remove("C");
		map.remove("B");
		map.remove("A");
		assertEquals(0, map.size());
	}

}
