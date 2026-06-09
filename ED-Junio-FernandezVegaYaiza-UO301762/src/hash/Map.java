package hash;

/**
 * Generic map interface based on key-value associations.
 *
 * <p>This interface defines the basic operations supported
 * by a hash table implementation.</p>
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface Map<K, V> {

    /**
     * Associates the specified value with the specified key.
     *
     * <p>If the key already exists in the map, its value
     * is replaced with the new value.</p>
     *
     * @param key the key with which the value is associated
     * @param value the value to associate with the key
     *
     * @throws IllegalArgumentException if key or value is null
     */
    void put(K key, V value);

    /**
     * Returns the value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     *
     * @return the value associated with the key,
     * or {@code null} if the key is not present
     *
     * @throws IllegalArgumentException if key is null
     */
    V get(K key);

    /**
     * Removes the mapping associated with the specified key.
     *
     * @param key the key to remove
     *
     * @throws IllegalArgumentException if key is null
     */
    void remove(K key);

    /**
     * Checks whether the map contains the specified key.
     *
     * @param key the key to search for
     *
     * @return {@code true} if the key exists,
     * {@code false} otherwise
     *
     * @throws IllegalArgumentException if key is null
     */
    boolean containsKey(K key);

    /**
     * Checks whether the map contains no elements.
     *
     * @return {@code true} if the map is empty,
     * {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of key-value mappings stored in the map.
     *
     * @return the number of elements in the map
     */
    int size();

    /**
     * Returns the current load factor of the hash table.
     *
     * <p>The load factor is defined as the ratio between the
     * number of stored elements and the number of buckets
     * in the table.</p>
     *
     * <p>A higher load factor generally increases the number
     * of collisions and may reduce performance.</p>
     *
     * @return the current load factor of the hash table
     */
    double getLoadFactor();
}