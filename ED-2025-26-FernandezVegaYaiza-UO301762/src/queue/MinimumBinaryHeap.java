package queue;

import java.util.Arrays;

/**
 * Implementacion de un monticulo binario
 * 
 * @author Yaiza
 * @version 03-12-2025
 */
public class MinimumBinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {

	private T[] heap;
	private int size;
	private int capacity;

	static int NO_PARENT = -1;
	static int NO_CHILD = -1;

	/**
	 * Construye un nuevo MinimumBinaryHeap con una capacidad especifica Inicializa
	 * el MinimumBinaryHeap con size 0
	 * 
	 * @throws IllegalArgumentException
	 * @param capacity capacidad *
	 */
	@SuppressWarnings("unchecked")
	public MinimumBinaryHeap(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity no puede ser < 0");
		}
		this.capacity = capacity;
		size = 0;
		this.heap = (T[]) new Comparable[capacity];
	}

	/**
	 * Devuelve el indice del padre del indice pasado como parametro. Si no tiene
	 * padre, devuelve NO_PARENT
	 * 
	 * @throws IllegalArgumentException si elementIndex < 0 o >= size
	 * @param elementIndex indice del elemento a buscar padre
	 * @return NO_PARENT si no lo encuentra, indice del padre si hay
	 */
	int getParentIndex(int elementIndex) {
		if (elementIndex < 0 || elementIndex >= size) {
			throw new IllegalArgumentException("elementIndex no puede ser < 0 o >= size");
		}
		if (elementIndex == 0) {
			return NO_PARENT;
		}
		return (elementIndex - 1) / 2;
	}

	/**
	 * Devuelve el indice del hijo izquierdo del indice pasado como parametro. Si no
	 * tiene hijo, devuelve NO_CHILD
	 * 
	 * @throws IllegalArgumentException si elementIndex < 0 o elementIndex >= size
	 * @param elementIndex indice del elemento a buscar el hijo izquierdo
	 * @return NO_CHILD si no lo encuentra, indice del hijo izquierdo si hay
	 */
	int getLeftChildIndex(int elementIndex) {
		if (elementIndex < 0 || elementIndex >= size) {
			throw new IllegalArgumentException("elementIndex no puede ser < 0 o >= size");
		}
		int left = 2 * elementIndex + 1;
		return (left >= size) ? NO_CHILD : left;
	}

	/**
	 * Devuelve el indice del hijo derecho del indice pasado como parametro. Si no
	 * tiene hijo, devuelve NO_CHILD
	 * 
	 * @throws IllegalArgumentException si elementIndex < 0 o elementIndex >= size
	 * @param elementIndex indice del elemento a buscar el hijo derecho
	 * @return NO_CHILD si no lo encuentra, indice del hijo derecho si lo hay
	 */
	int getRightChildIndex(int elementIndex) {
		if (elementIndex < 0 || elementIndex >= size) {
			throw new IllegalArgumentException("elementIndex no puede ser < 0 o >= size");
		}
		int right = 2 * elementIndex + 2;
		return (right >= size) ? NO_CHILD : right;
	}

	/**
	 * Comprueba si un valor es mayor que el de referencia
	 * 
	 * @throws IllegalArgumentException si comparedIndex o referenceIndex < 0, o si
	 *                                  comparedIndex o referenceIndex >= size
	 * @param comparedIndex  indice a comparar
	 * @param referenceIndex indice de referencia
	 * @return true si heap[comparedIndex] > heap[referenceIndex]
	 */
	boolean hasGreaterValueThan(int comparedIndex, int referenceIndex) {
		if (comparedIndex < 0 || comparedIndex >= size || referenceIndex < 0 || referenceIndex >= size) {
			throw new IllegalArgumentException("index fuera de rango");
		}
		return heap[comparedIndex].compareTo(heap[referenceIndex]) > 0;
	}

	/**
	 * Devuelve el menor de los indices de los hijos. Si no hay hijos, devuelve
	 * NO_CHILD, si izquierda no tiene hijos devuelve derecha.
	 * 
	 * @throws IllegalArgumentException si elementIndex < 0 o >= size
	 * @param elementIndex indice del elemento padre
	 * @return indice del hijo menor o NO_CHILD si no tiene hijos
	 */
	int getChildIndexWithLowestValue(int elementIndex) {
		if (elementIndex < 0 || elementIndex >= size) {
			throw new IllegalArgumentException("elementIndex no puede ser < 0 o >= size");
		}
		int left = getLeftChildIndex(elementIndex);
		int right = getRightChildIndex(elementIndex);
		if (left == NO_CHILD && right == NO_CHILD)
			return NO_CHILD;
		if (right == NO_CHILD)
			return left;
		return (heap[left].compareTo(heap[right]) <= 0) ? left : right;
	}

	/**
	 * Representa el heap en forma de array
	 * 
	 * @return cadena del heap en forma de array
	 */
	public String toString() {
		return Arrays.toString(Arrays.copyOf(heap, size));
	}

	/**
	 * Comprueba si hay elementos en el monticulo. Devuelve true si no los hay,
	 * false en caso contrario
	 * 
	 * @return true si esta vacio, false en caso contrario
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Reemplaza heap por newHeap
	 * 
	 * @throws NullPointerException si heap es null
	 * @param newHeap nuevo heap
	 */
	void setHeap(T[] newHeap) {
		if (newHeap == null) {
			throw new NullPointerException("heap no puede ser null");
		}
		this.heap = Arrays.copyOf(newHeap, newHeap.length);
		this.capacity = newHeap.length;
		this.size = newHeap.length;
	}

	/**
	 * Intercambia los elementos situados en dos indices
	 * 
	 * @throws IllegalArgumentException si algun indice esta fuera de rango
	 * @param index1 indice 1
	 * @param index2 indice 2
	 */
	void swapElements(int index1, int index2) {
		if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
			throw new IllegalArgumentException("index fuera de rango");
		}
		T temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}

	/**
	 * Reordena hacia arriba desde elementIndex filtrando
	 * 
	 * @throws IllegalArgumentException si elementIndex esta fuera de rango
	 * @param elementIndex indice
	 */
	void filterUp(int elementIndex) {
		if (elementIndex < 0 || elementIndex >= size) {
			throw new IllegalArgumentException("elementIndex no puede ser < 0 o >= size");
		}
		int parentIndex = getParentIndex(elementIndex);
		if (parentIndex == NO_PARENT) {
			return;
		}
		if (heap[elementIndex].compareTo(heap[parentIndex]) < 0) {
			swapElements(elementIndex, parentIndex);
			filterUp(parentIndex);
		}

	}

	/**
	 * Inserta un elemento en el heap
	 * 
	 * @throws NullPointerException  si element es null
	 * @throws IllegalStateException si heap esta lleno
	 * @param element elemento a insertar
	 * @return true si se inserto correctamente
	 */
	public boolean insert(T element) {
		if (element == null) {
			throw new NullPointerException("element no puede ser null");
		}
		if (size == capacity) {
			throw new IllegalStateException("heap lleno");
		}
		heap[size] = element;
		size++;
		filterUp(size - 1);
		return true;
	}

	/**
	 * Devuelve el tamaño de heap
	 * 
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Reordena hacia abajo desde elementIndex filtrando
	 * 
	 * @throws IllegalArgumentException si elementIndex esta fuera de rango
	 * @param elementIndex indice
	 */
	void filterDown(int elementIndex) {
		if (elementIndex < 0 || elementIndex >= size) {
			throw new IllegalArgumentException("elementIndex no puede ser < 0 o >= size");
		}
		int childIndex = getChildIndexWithLowestValue(elementIndex);
		if (childIndex != NO_CHILD && heap[elementIndex].compareTo(heap[childIndex]) > 0) {
			swapElements(elementIndex, childIndex);
			filterDown(childIndex);
		}

	}

	/**
	 * Extrae, devuelve el elemento raiz del heap y la reordena
	 * 
	 * @throws IllegalStateException si heap esta vacio
	 * @return el valor minimo
	 */
	public T extract() {
		if (isEmpty()) {
			throw new IllegalStateException("heap vacio");
		}
		T min = heap[0];
		heap[0] = heap[size - 1];
		heap[size - 1] = null;
		size--;
		if (!isEmpty())
			filterDown(0);
		return min;
	}

}
