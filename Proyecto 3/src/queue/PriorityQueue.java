package queue;

public interface PriorityQueue<T extends Comparable<T>> {
	
	/**
	 * Inserta un elemento en el heap
	 * 
	 * @throws NullPointerException  si element es null
	 * @throws IllegalStateException si heap esta lleno
	 * @param element elemento a insertar
	 * @return true si se inserto correctamente
	 */
	boolean insert(T element);
	
	/**
	 * Comprueba si hay elementos en el monticulo. Devuelve true si no los hay,
	 * false en caso contrario
	 * 
	 * @return true si esta vacio, false en caso contrario
	 */
	boolean isEmpty();
	
	/**
	 * Devuelve el tamaño de heap
	 * 
	 * @return the size
	 */
	int getSize();
	
	/**
	 * Extrae, devuelve el elemento raiz del heap y la reordena
	 * 
	 * @throws IllegalStateException si heap esta vacio
	 * @return el valor minimo
	 */
	T extract();
	
}
