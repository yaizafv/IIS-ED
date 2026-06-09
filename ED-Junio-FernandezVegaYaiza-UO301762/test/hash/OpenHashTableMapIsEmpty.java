package hash;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OpenHashTableMapIsEmpty {
	
	@Test
	public void isEmpty() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void isEmptyFalse() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		map.put("A", 2);
		assertFalse(map.isEmpty());
	}

}
