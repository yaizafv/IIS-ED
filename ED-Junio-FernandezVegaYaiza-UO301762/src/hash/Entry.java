package hash;

public class Entry<K, V> {
    private final K key;
    private V value;
    public Entry<K,V> next;

    public Entry(K key) {
        this.key = key;
        next = null;
    }

    public Entry(K key, V value) {
        this(key);
        this.value = value;
        next = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entry<?, ?> other)
            return key.equals(other.key);
        return key.equals(obj);
    }
}
