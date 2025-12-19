package hashtables;

/**
 * Abstract base class providing common functionality for hash table
 * implementations.
 * 
 * This class provides: - Basic fields for capacity and element count tracking -
 * Default implementation of load factor calculation - Utility methods for prime
 * number operations - Base hash function implementation
 * 
 * Subclasses must implement the specific hash table behavior (open or closed
 * addressing, collision resolution strategies, etc.).
 * 
 * @param <T> the type of elements stored in the hash table
 */
public abstract class AbstractHashTable<T> implements HashTable<T> {

	/**
	 * The total capacity of the hash table.
	 */
	protected int capacityB;

	/**
	 * The current number of elements stored in the hash table.
	 */
	protected int elementNumber;

	/**
	 * Returns the total capacity of the hash table.
	 * 
	 * @return the maximum number of elements the table can hold
	 */
	@Override
	public int getCapacityB() {
		return capacityB;
	}

	/**
	 * Returns the number of elements currently stored in the hash table.
	 * 
	 * @return the number of elements in the table
	 */
	@Override
	public int getElementNumber() {
		return elementNumber;
	}

	/**
	 * Calculates and returns the current load factor of the hash table.
	 * 
	 * The load factor is defined as the ratio of the number of elements to the
	 * table's capacity: elementNumber / capacity.
	 * 
	 * @return the load factor as a value between 0.0 and 1.0
	 */
	@Override
	public double getLoadFactor() {
		return (double) elementNumber / capacityB;
	}

	/**
	 * Computes the hash index for an element.
	 * 
	 * This base implementation uses the element's hashCode, converts it to a
	 * positive value, and applies modulo with the table capacity. Subclasses may
	 * override this to implement specific collision resolution strategies.
	 * 
	 * @param element  the element to hash
	 * @param attempts the number of collision resolution attempts (may be ignored
	 *                 in base implementation)
	 * @return the computed index in range [0, capacityB)
	 */
	protected abstract int hashFunction(T element, int attempts);

	/**
	 * Checks whether a given number is prime.
	 * 
	 * Uses trial division up to the square root of n for efficiency. Numbers less
	 * than or equal to 1 are not considered prime.
	 * 
	 * @param n the number to check
	 * @return true if n is prime, false otherwise
	 */
	protected static boolean isPrimeNumber(int n) {
		if (n <= 1) {
			return false;
		}

		// If we've checked all numbers up to √n and found no divisors, there can't be
		// any larger divisors either
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Finds the smallest prime number greater than n.
	 * 
	 * If n is less than 2, returns 2 (the smallest prime). Uses an optimized search
	 * that skips even numbers.
	 * 
	 * @param n the number to start searching from
	 * @return the next prime number after n
	 */
	protected static int getNextPrimeNumber(int n) {
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

	/**
	 * Finds the largest prime number less than n.
	 * 
	 * Uses an optimized search that skips even numbers.
	 * 
	 * @param n the number to start searching from
	 * @return the previous prime number before n
	 * @throws IllegalArgumentException if n is less than or equal to 2
	 */
	protected static int getPreviousPrimeNumber(int n) {
		if (n <= 2)
			throw new IllegalArgumentException("There is no prime number lower than 2.");

		// Lowest prime numbers
		if (n == 3)
			return 2;

		// Get previous number
		int previousNumber = n - 1;

		// Start from odd number
		if (previousNumber % 2 == 0)
			previousNumber--;

		while (!isPrimeNumber(previousNumber)) {
			previousNumber -= 2; // Skip evens
		}
		return previousNumber;
	}

}
