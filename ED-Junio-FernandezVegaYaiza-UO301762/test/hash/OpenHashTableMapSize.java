package hash;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OpenHashTableMapSize {
	
	@Test
	public void size0() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertEquals(0, map.size());
	}
	
	@Test
	public void size1() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		map.put("A", 2);
		assertEquals(1, map.size());
	}

}
