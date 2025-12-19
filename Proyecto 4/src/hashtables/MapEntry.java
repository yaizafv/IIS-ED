
package hashtables;

import java.util.Objects;

/**
 * Implementacion de un par clave-valor para usar en estructuras tipo HashMap.
 * 
 * @author Yaiza
 * @version 19-12-2025
 */
public class MapEntry<K, V> {

	private K key;
	private V value;

	/**
	 * Crea un nuevo par clave-valor.
	 *
	 * @param key   clave del par.
	 * @param value valor asociado a la clave.
	 */
	public MapEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * hashCode basado en la clave (key)
	 * 
	 * @return codigo hash de la clave, 0 si la clave es null.
	 */
	@Override
	public int hashCode() {
		return (key == null) ? 0 : key.hashCode();
	}

	/**
	 * Comprueba si otro objeto es igual a este MapEntry. Dos MapEntry son iguales
	 * si sus claves son iguales.
	 *
	 * @param obj objeto a comparar.
	 * @return true si el objeto es un MapEntry con la misma clave, false en caso
	 *         contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		MapEntry<?, ?> other = (MapEntry<?, ?>) obj;
		return Objects.equals(this.key, other.key);
	}

}
