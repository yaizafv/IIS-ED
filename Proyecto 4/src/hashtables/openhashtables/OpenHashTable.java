
package hashtables.openhashtables;

import hashtables.AbstractHashTable;
import hashtables.HashTable;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Implementación de una tabla hash abierta
 * 
 * @author Yaiza
 * @version 19-12-2025
 */
public class OpenHashTable<T> extends AbstractHashTable<T> implements HashTable<T> {

	protected Collection<T>[] associativeArray;

	/**
	 * Constructor que crea una tabla hash abierta con la capacidad pasada como
	 * parametro. La capacidad se ajusta al siguiente numero primo si no lo es.
	 *
	 * @throws IllegalArgumentException si capacityB < 3.
	 * @param capacityB Capacidad inicial de la tabla hash (>= 3).
	 */
	@SuppressWarnings("unchecked")
	public OpenHashTable(int capacityB) {
		if (capacityB < 3)
			throw new IllegalArgumentException("capacityB no puede ser < 3");
		if (!isPrimeNumber(capacityB)) {
			capacityB = getNextPrimeNumber(capacityB);
		}
		this.capacityB = capacityB;
		this.elementNumber = 0;
		associativeArray = (Collection<T>[]) new Collection[capacityB];
		for (int i = 0; i < capacityB; i++) {
			associativeArray[i] = new LinkedList<>();
		}
	}

	/**
	 * Funcion hash que calcula el indice de un elemento. Para tablas abiertas, el
	 * numero de intentos no afecta al indice.
	 *
	 * @throws NullPointerException     si element es null.
	 * @throws IllegalArgumentException si attempts < 0 o attempts > capacityB.
	 * @param element  Elemento cuyo indice se desea calcular.
	 * @param attempts numero de intentos de sondeo
	 * @return indice en el array donde se almacena el elemento.
	 */
	@Override
	protected int hashFunction(T element, int attempts) {
		if (element == null)
			throw new NullPointerException("element no puede ser null");
		if (attempts < 0 || attempts > capacityB)
			throw new IllegalArgumentException("attempts no puede ser negativo ni mayor que la capacidad");
		int hashCode = Math.abs(element.hashCode());
		return hashCode % capacityB;
	}

	/**
	 * Añade un elemento a la tabla hash.
	 *
	 * @throws NullPointerException si element es null.
	 * @param element elemento a insertar.
	 * @return true si el elemento fue añadido, false si ya existia.
	 */
	@Override
	public boolean add(T element) {
		if (element == null)
			throw new NullPointerException("element no puede ser null");
		int index = hashFunction(element, 0);
		Collection<T> collection = associativeArray[index];
		if (collection.contains(element)) {
			return false;
		}
		boolean added = collection.add(element);
		if (added)
			elementNumber++;
		return added;
	}

	/**
	 * Comprueba si un elemento existe en la tabla hash.
	 *
	 * @throws NullPointerException si element es null.
	 * @param element elemento a buscar.
	 * @return true si el elemento existe, false en caso contrario.
	 */
	@Override
	public boolean search(T element) {
		if (element == null)
			throw new NullPointerException("element no puede ser null");
		int index = hashFunction(element, 0);
		return associativeArray[index].contains(element);
	}

	/**
	 * Elimina un elemento de la tabla hash.
	 *
	 * @throws NullPointerException si element es null.
	 * @param element elemento a eliminar.
	 * @return true si el elemento existía y fue eliminado, false si no existía.
	 */
	@Override
	public boolean remove(T element) {
		if (element == null)
			throw new NullPointerException("element no puede ser null");
		int index = hashFunction(element, 0);
		boolean removed = associativeArray[index].remove(element);
		if (removed) {
			elementNumber--;
		}
		return removed;
	}
}
