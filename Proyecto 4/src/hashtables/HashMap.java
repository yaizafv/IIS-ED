
package hashtables;

/**
 * Implementación de un hashmap basado en ClosedHashTable.
 * 
 * @author Yaiza
 * @version 19-12-2025
 */
public class HashMap<K, V> extends ClosedHashTable<MapEntry<K, V>> {

	/**
	 * Constructor con capacidad inicial y estrategia de hash.
	 *
	 * @param capacityB capacidad inicial de la tabla hash.
	 * @param strategy  estrategia de hashing a usar.
	 */
	public HashMap(int capacityB, HashStrategy strategy) {
		super(capacityB, strategy);
	}

	/**
	 * Constructor con capacidad inicial, estrategia de hash y factor de carga
	 * maximo.
	 *
	 * @param capacityB     capacidad inicial de la tabla hash.
	 * @param strategy      estrategia de hashing a usar.
	 * @param maxLoadFactor factor de carga máximo antes de redimensionar.
	 */
	public HashMap(int capacityB, HashStrategy strategy, double maxLoadFactor) {
		super(capacityB, strategy, maxLoadFactor);
	}

	/**
	 * Constructor completo con capacidad inicial, estrategia de hash, factor de
	 * carga maximo y minimo.
	 *
	 * @param capacityB     capacidad inicial de la tabla hash.
	 * @param strategy      estrategia de hashing a usar.
	 * @param maxLoadFactor factor de carga maximo antes de redimensionar.
	 * @param minLoadFactor factor de carga minimo antes de reducir la tabla.
	 */
	public HashMap(int capacityB, HashStrategy strategy, double maxLoadFactor, double minLoadFactor) {
		super(capacityB, strategy, maxLoadFactor, minLoadFactor);
	}

	/**
	 * Devuelve el valor asociado a la clave key, o null si no existe.
	 * 
	 * @throws NullPointerException si key es null
	 * @param key clave
	 * @return key o null si no existe
	 */
	public V get(K key) {
		if (key == null)
			throw new NullPointerException("key no puede ser null");
		MapEntry<K, V> probe = new MapEntry<>(key, null);
		HashNode<MapEntry<K, V>> node = findMatchingNode(probe);
		if (node != null && node.getStatus() == Status.VALID) {
			MapEntry<K, V> entry = node.getElement();
			if (entry == null) {
				return null;
			}
			return entry.getValue();
		}
		return null;
	}

	/**
	 * Inserta key y value. Si la clave ya existe, se actualiza value. Devuelve true
	 * si se ha insertado un nuevo par, false si se ha actualizado.
	 * 
	 * @throws NullPointerException si key es null
	 * @param key   clave
	 * @param value valor
	 * @return nuevo par de valores o false
	 */
	public boolean put(K key, V value) {
		if (key == null)
			throw new NullPointerException("key no puede ser null");
		MapEntry<K, V> map = new MapEntry<>(key, null);
		HashNode<MapEntry<K, V>> node = findMatchingNode(map);
		if (node != null && node.getStatus() == Status.VALID) {
			node.getElement().setValue(value);
			return false;
		} else {
			return add(new MapEntry<>(key, value));
		}
	}

	/**
	 * Elimina por clave. Devuelve true si existía y ha sido eliminado.
	 * 
	 * @throws NullPointerException si key es null
	 */
	public boolean removeKey(K key) {
		if (key == null)
			throw new NullPointerException("key no puede ser null");
		return remove(new MapEntry<>(key, null));
	}
}
