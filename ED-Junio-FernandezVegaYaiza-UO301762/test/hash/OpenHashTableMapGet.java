package hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class OpenHashTableMapGet {
	
	@Test
	public void getNull() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertThrows(IllegalArgumentException.class, () -> map.get(null));
	}
	
	@Test
	public void getItem() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		int result = map.get("B");
		assertEquals(2, result);
	}
	
	@Test
	public void getFirstItem() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		int result = map.get("A");
		assertEquals(1, result);
	}
	
	@Test
	public void getLastItem() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		int result = map.get("C");
		assertEquals(3, result);
	}

}
