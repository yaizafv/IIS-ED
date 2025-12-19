package hashtables;

/**
 * Implementacion de un nodo de la tabla hash que almacena un elemento y su
 * estado.
 * 
 * @author Yaiza
 * @version 19-12-2025
 */
public class HashNode<T> {

	T element;
	Status status;

	final static String OPEN_NODE_STRING = "{";
	final static String CLOSE_NODE_STRING = "}";
	final static String NODE_SEPARATOR_STRING = "|";
	final static String NULL_ELEMENT_STRING = "-";

	/**
	 * Constructor que crea un nodo vacio. Inicializa el elemento a null y el estado
	 * a EMPTY.
	 */
	public HashNode() {
		element = null;
		status = Status.EMPTY;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the element
	 */
	public T getElement() {
		return element;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Devuelve una representación en cadena del nodo. Formato:
	 * {inicialEstado|elemento} Si el elemento es null, se muestra un guion "-".
	 *
	 * @return representacion en cadena del nodo.
	 */
	@Override
	public String toString() {
		String elementString = (element == null) ? NULL_ELEMENT_STRING : element.toString();
		return OPEN_NODE_STRING + status.getStatusInitial() + NODE_SEPARATOR_STRING + elementString + CLOSE_NODE_STRING;
	}

}
