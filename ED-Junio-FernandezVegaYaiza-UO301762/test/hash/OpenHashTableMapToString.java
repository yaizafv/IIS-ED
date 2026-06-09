package hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class OpenHashTableMapToString {
	
	@Test
	public void putMultipleEntrys() {
		OpenHashTableMap<String, Integer> map = new OpenHashTableMap<String, Integer>(10);
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		assertEquals("[[A=1][B=2][C=3]]", map.toString());
	}
}
