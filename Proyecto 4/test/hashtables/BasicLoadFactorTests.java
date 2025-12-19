package hashtables;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BasicLoadFactorTests {
	
	final static Status E = Status.EMPTY;
	final static Status V = Status.VALID;
	final static Status D = Status.DELETED;
	final static Integer N = null;
	final double DELTA = 0.001;

	@Test
	void balanceFactorTest() {
		int capacity = 7; // A prime number capacity
		ClosedHashTable<Integer> table = new ClosedHashTable<Integer>(capacity, HashStrategy.LINEAR_PROBING);

		assertEquals(0.0, table.getLoadFactor(), DELTA);
		
		Status[] statuses = {V, V, V, V, V, V, V};
		Integer[] elements = {0, 1, 2, 3, 4, 5, 6};
		TestHelper.setHashTableNodes(table, statuses, elements);

		assertEquals(1.0, table.getLoadFactor(), DELTA);
		
		
		statuses = new Status[]{V, D, D, D, V, V, V};
		elements = new Integer[] {0, 1, 2, 3, 4, 5, 6};
		TestHelper.setHashTableNodes(table, statuses, elements);
		
		assertEquals(4.0/7, table.getLoadFactor());
		
		
		statuses = new Status[]{V, E, E, E, E, V, V};
		elements = new Integer[] {0, N, N, N, N, 5, 6};
		TestHelper.setHashTableNodes(table, statuses, elements);
		
		assertEquals(3.0/7, table.getLoadFactor());

	}
}
