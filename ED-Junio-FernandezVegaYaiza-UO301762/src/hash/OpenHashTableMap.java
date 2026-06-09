package hash;

public class OpenHashTableMap<K, V> implements Map<K, V> {
	
	private Entry<K, V>[] table;
	private double maxLoadFactor;
	private int size;

	@SuppressWarnings("unchecked")
	public OpenHashTableMap(int initialCapacity) {
		if (initialCapacity <= 0) {
			throw new IllegalArgumentException("initialCapacity debe ser > 0");
		}
		table = new Entry[initialCapacity];
		size = 0;
		maxLoadFactor = -1;
	}
	
	@SuppressWarnings("unchecked")
	public OpenHashTableMap(int initialCapacity, double maxLoadFactor) {
		if (initialCapacity <= 0) {
			throw new IllegalArgumentException("initialCapacity debe ser > 0");
		}
		table = new Entry[initialCapacity];
		size = 0;
		this.maxLoadFactor = maxLoadFactor;
	}

	@Override
	public void put(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("key no puede ser null");
		}
		if (value == null) {
			throw new IllegalArgumentException("value no puede ser null");
		}
		int hash = hash(key);
		Entry<K,V> current = table[hash];
		while (current != null) {
			if (current.getKey().equals(key)) {
				current.setValue(value);
				return;
			}
			current = current.next;
		}
		Entry<K,V> newEntry = new Entry<K, V>(key, value);
		newEntry.next = table[hash];
		table[hash] = newEntry;
		size++;		
	}
	
	private int hash(K key) {
		return (key.hashCode()) % table.length;
	}

	@Override
	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key no puede ser null");
		}
		int hash = hash(key);
		Entry<K,V> current = table[hash];
		while (current != null) {
			if (current.getKey().equals(key)) {
				return current.getValue();
			}
			current = current.next;
		}
		return null;
	}

	@Override
	public void remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key no puede ser null");
		}
		int hash = hash(key);
		Entry<K,V> current = table[hash];
		Entry<K,V> previous = null;
		while (current != null) {
			if (current.getKey().equals(key)) {
				if (previous == null) {
					table[hash] = current.next;
				} else {
					previous.next = current.next;
				}
				size--;
				return;
			}
			previous = current;
			current = current.next;
		}
		
	}

	@Override
	public boolean containsKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key no puede ser null");
		}
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public double getLoadFactor() {
		return (double) size / table.length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < table.length; i++) {
			Entry<K,V> current = table[i];
			while (current != null) {
				sb.append("[").append(current.getKey()).append("=").append(current.getValue()).append("]");
				if(current.next != null) {
					sb.append(", ");
				}
				current = current.next;
			}
		}
		sb.append("]");
		return sb.toString();
	}

	private boolean isPrimeNumber(int n) {
		if (n <= 1) {
			return false;
		}
		int limit = (int) Math.sqrt(n);
		for (int i = 2; i <= limit; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	private int getNextPrimeNumber(int n) {
		// Lowest prime number
		if (n < 2)
			return 2;

		// Get next number
		int nextNumber = n + 1;

		// Start from odd number
		if (nextNumber % 2 == 0)
			nextNumber++;

		while (!isPrimeNumber(nextNumber)) {
			nextNumber += 2; // Skip evens
		}
		return nextNumber;
	}
}
