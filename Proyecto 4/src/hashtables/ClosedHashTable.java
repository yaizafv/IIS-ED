package hashtables;

/**
 * Implementacion de una tabla hash cerrada
 * 
 * @author Yaiza
 * @version 19-12-2025
 */
public class ClosedHashTable<T> extends AbstractHashTable<T> {

	HashNode<T>[] associativeArray;
	HashStrategy hashStrategy;
	int previousPrime;

	double maxLoadFactor;
	double minLoadFactor;

	private static final double NO_MAX_RESIZE = 2.0;
	private static final double NO_MIN_RESIZE = -1.0;

	final static String INDEX_SEPARATOR_STRING = ":";
	final static String NODE_SPACING_STRING = "   ";

	/**
	 * Constructor completo que permite especificar capacidad, estrategia de hash y
	 * factores de carga máximos y mínimos.
	 * 
	 * @param capacityB     capacidad inicial de la tabla.
	 * @param hashStrategy  estrategia de hashing a utilizar.
	 * @param maxLoadFactor factor de carga máximo antes de redimensionar.
	 * @param minLoadFactor factor de carga mínimo antes de reducir la tabla.
	 */
	public ClosedHashTable(int capacityB, HashStrategy hashStrategy, double maxLoadFactor, double minLoadFactor) {
		if (hashStrategy == null) {
			throw new NullPointerException("hashStrategy no puede ser null");
		}
		this.hashStrategy = hashStrategy;
		this.maxLoadFactor = maxLoadFactor;
		this.minLoadFactor = minLoadFactor;
		initializeEmptyAssociativeArray(capacityB);
	}

	/**
	 * Constructor con factor de carga maximo y sin factor mínimo.
	 * 
	 * @param capacityB     capacidad inicial.
	 * @param hashStrategy  estrategia de hashing.
	 * @param maxLoadFactor factor de carga maximo.
	 */
	public ClosedHashTable(int capacityB, HashStrategy hashStrategy, double maxLoadFactor) {
		this(capacityB, hashStrategy, maxLoadFactor, NO_MIN_RESIZE);
	}

	/**
	 * Constructor con capacidad inicial y estrategia de hash.
	 * 
	 * @param capacityB    capacidad inicial.
	 * @param hashStrategy estrategia de hashing.
	 */
	public ClosedHashTable(int capacityB, HashStrategy hashStrategy) {
		this(capacityB, hashStrategy, NO_MAX_RESIZE, NO_MIN_RESIZE);
	}

	/**
	 * Inicializa el array con nodos vacios y ajusta la capacidad a un numero primo.
	 * 
	 * @throws IllegalArgumentException si capacidad < 3
	 * @param newCapacityB Capacidad inicial solicitada.
	 */
	@SuppressWarnings("unchecked")
	protected void initializeEmptyAssociativeArray(int newCapacityB) {
		if (newCapacityB < 3)
			throw new IllegalArgumentException("capacityB no puede ser < 3");
		if (!isPrimeNumber(newCapacityB))
			newCapacityB = getNextPrimeNumber(newCapacityB);
		this.capacityB = newCapacityB;
		this.previousPrime = getPreviousPrimeNumber(this.capacityB);
		this.associativeArray = (HashNode<T>[]) new HashNode[this.capacityB];
		for (int i = 0; i < this.capacityB; i++) {
			this.associativeArray[i] = new HashNode<>();
		}
		this.elementNumber = 0;
	}

	/**
	 * Añade un elemento a la tabla hash. Redimensiona dinamicamente si se supera el
	 * factor de carga máximo.
	 * 
	 * @throws NullPointerException  si element es null
	 * @throws IllegalStateException si la tabla esta llena
	 * @param element Elemento a añadir.
	 * @return true si se añade correctamente, false si ya existía.
	 */
	@Override
	public boolean add(T element) {
		if (element == null)
			throw new NullPointerException("element no puede ser null");
		if (search(element))
			return false;
		HashNode<T> node = findAvailableNodeFor(element);
		if (node == null) {
			if (elementNumber == capacityB)
				throw new IllegalStateException("tabla llena");
			return false;
		}
		addElementToNode(node, element);
		dynamicResize();
		return true;
	}

	/**
	 * Busca un nodo disponible para insertar un elemento.
	 * 
	 * @throws NullPointerException si element es null
	 * @param element Elemento a insertar.
	 * @return Nodo disponible, o null si el elemento ya existe.
	 */
	protected HashNode<T> findAvailableNodeFor(T element) {
		if (element == null)
			throw new NullPointerException("element no puede ser null");
		int attempts = 0;
		HashNode<T> firstDeleted = null;
		while (attempts < capacityB) {
			int index = hashFunction(element, attempts);
			HashNode<T> node = associativeArray[index];
			if (node.getStatus() == Status.VALID) {
				if (element.equals(node.getElement()))
					return null;
			} else if (node.getStatus() == Status.DELETED) {
				if (firstDeleted == null)
					firstDeleted = node;
			} else {
				return (firstDeleted != null) ? firstDeleted : node;
			}
			attempts++;
		}
		return firstDeleted;
	}

	/**
	 * Inserta un elemento en un nodo disponible.
	 * 
	 * @throws IllegalStateException si la tabla esta llena
	 * @throws NullPointerException  si node es null
	 * @throws NullPointerException  si element es null
	 * @param node    nodo donde insertar.
	 * @param element elemento a insertar.
	 */
	protected void addElementToNode(HashNode<T> node, T element) {
		if (elementNumber == capacityB)
			throw new IllegalStateException("tabla llena");
		if (node == null)
			throw new NullPointerException("node no puede ser null");
		if (element == null)
			throw new NullPointerException("element no puede ser null");
		if (node.getStatus() == Status.VALID)
			throw new IllegalArgumentException("nodo ocupado");
		node.setElement(element);
		node.setStatus(Status.VALID);
		elementNumber++;
	}

	/**
	 * Comprueba si un elemento esta presente en la tabla hash.
	 * 
	 * @param element elemento a buscar.
	 * @return true si está presente, false en caso contrario.
	 */
	@Override
	public boolean search(T element) {
		HashNode<T> node = findMatchingNode(element);
		return node != null && node.getStatus() == Status.VALID;
	}

	/**
	 * Busca el nodo que contiene un elemento coincidente.
	 * 
	 * @throws NullPointerException si element es null
	 * @param element elemento a buscar.
	 * @return Nodo que contiene el elemento, o null si no existe.
	 */
	protected HashNode<T> findMatchingNode(T element) {
		if (element == null)
			throw new NullPointerException("element no puede ser null");
		int attempts = 0;
		int index;
		HashNode<T> firstDeletedNode = null;
		while (attempts < capacityB) {
			index = hashFunction(element, attempts);
			HashNode<T> node = associativeArray[index];
			if (node.getStatus() == Status.EMPTY) {
				return (firstDeletedNode != null) ? firstDeletedNode : null;
			} else if (node.getStatus() == Status.DELETED) {
				if (element.equals(node.getElement()) && firstDeletedNode == null) {
					firstDeletedNode = node;
				}
			} else if (node.getStatus() == Status.VALID) {
				if (element.equals(node.getElement())) {
					return node;
				}
			}
			attempts++;
		}
		return firstDeletedNode;
	}

	/**
	 * Elimina un elemento de la tabla hash.
	 * 
	 * @throws IllegalStateException si elementNumber es 0
	 * @throws NullPointerException  si element es null
	 * @param element elemento a eliminar.
	 * @return true si se elimino, false si no existia.
	 */
	@Override
	public boolean remove(T element) {
		if (elementNumber == 0)
			throw new IllegalStateException("elementNumber no puede ser 0");
		if (element == null)
			throw new NullPointerException("element no puede ser null");
		HashNode<T> node = findMatchingNode(element);
		if (node == null || node.getStatus() != Status.VALID)
			return false;
		removeElementFromNode(node);
		inverseDynamicResize();
		return true;
	}

	/**
	 * Marca un nodo como eliminado.
	 * 
	 * @throws IllegalStateException si elementNumber es 0
	 * @throws NullPointerException  si node es null
	 * @throws IllegalStateException si status es valid
	 * @param node nodo a eliminar.
	 */
	protected void removeElementFromNode(HashNode<T> node) {
		if (elementNumber == 0)
			throw new IllegalStateException("elementNumber no puede ser 0");
		if (node == null)
			throw new NullPointerException("node no puede ser null");
		if (node.getStatus() != Status.VALID)
			throw new IllegalArgumentException("status debe ser valido");
		node.setStatus(Status.DELETED);
		elementNumber--;
	}

	/**
	 * Calcula la posicion del nodo usando la estrategia de hash seleccionada.
	 * 
	 * @throws IllegalStateException si no entra en ningun caso
	 * @param element  elemento a insertar/buscar.
	 * @param attempts numero de intentos.
	 * @return indice calculado.
	 */
	@Override
	protected int hashFunction(T element, int attempts) {
		int hashCode = Math.abs(element.hashCode());
		switch (hashStrategy) {
		case LINEAR_PROBING:
			return linearProbing(hashCode % capacityB, attempts);
		case QUADRATIC_PROBING:
			return quadraticProbing(hashCode % capacityB, attempts);
		case DOUBLE_HASHING:
			return doubleHashing(hashCode, attempts);
		default:
			throw new IllegalStateException();
		}
	}

	/**
	 * Calcula el índice usando linear probing (sondeo lineal).
	 *
	 * @throws IllegalArgumentException si hashCode < 0 o attempts < 0 o attempts >
	 *                                  capacityB.
	 * @param hashCode codigo hash inicial del elemento (>= 0).
	 * @param attempts numero de intentos de sondeo
	 * @return indice calculado en el array asociativo.
	 */
	protected int linearProbing(int hashCode, int attempts) {
		if (hashCode < 0)
			throw new IllegalArgumentException("hashCode no puede ser negativo");
		if (attempts < 0 || attempts > capacityB)
			throw new IllegalArgumentException("attempts no puede ser negativo ni mayor que la capacidad");
		return (hashCode + attempts) % capacityB;
	}

	/**
	 * Calcula el indice usando quadratic probing (sondeo cuadrático).
	 *
	 * @throws IllegalArgumentException si hashCode < 0 o attempts < 0 o attempts >
	 *                                  capacityB.
	 * @param hashCode codigo hash inicial del elemento (>= 0).
	 * @param attempts numero de intentos de sondeo
	 * @return indice calculado en el array asociativo.
	 */
	protected int quadraticProbing(int hashCode, int attempts) {
		if (hashCode < 0)
			throw new IllegalArgumentException("hashCode no puede ser negativo");
		if (attempts < 0 || attempts > capacityB)
			throw new IllegalArgumentException("attempts no puede ser negativo ni mayor que la capacidad");
		return (hashCode + attempts * attempts) % capacityB;
	}

	/**
	 * Función de salto auxiliar para double hashing.
	 *
	 * @throws IllegalArgumentException si hashCode < 0.
	 * @param hashCode codigo hash del elemento (>= 0).
	 * @return Valor de salto h2 usado en double hashing.
	 */
	protected int jumpFunctionH(int hashCode) {
		if (hashCode < 0)
			throw new IllegalArgumentException("hashCode no puede ser negativo");
		return previousPrime - (hashCode % previousPrime);
	}

	/**
	 * Calcula el indice usando double hashing.
	 *
	 * @throws IllegalArgumentException si hashCode < 0 o attempts < 0 o attempts >
	 *                                  capacityB.
	 * @param hashCode codigo hash inicial del elemento (>= 0).
	 * @param attempts numero de intentos de sondeo
	 * @return indice calculado en el array asociativo.
	 */
	protected int doubleHashing(int hashCode, int attempts) {
		if (hashCode < 0)
			throw new IllegalArgumentException("hashCode no puede ser negativo");
		if (attempts < 0 || attempts > capacityB)
			throw new IllegalArgumentException("attempts no puede ser negativo ni mayor que la capacidad");
		int h1 = hashCode % capacityB;
		int h2 = jumpFunctionH(hashCode);
		return (h1 + attempts * h2) % capacityB;
	}

	/**
	 * Redimensiona la tabla hash a una nueva capacidad y rehace todos los
	 * elementos.
	 *
	 * @throws IllegalArgumentException si newCapacity < 3 o newCapacity <
	 *                                  elementNumber.
	 * @param newCapacity Nueva capacidad de la tabla (>= 3 y >= elementNumber).
	 */
	protected void updateCapacity(int newCapacity) {
		if (newCapacity < 3)
			throw new IllegalArgumentException("newCapacity no puede ser < 3");
		if (newCapacity < this.elementNumber)
			throw new IllegalArgumentException("newCapacity no puede ser < elementNumber");
		HashNode<T>[] oldArray = this.associativeArray;
		initializeEmptyAssociativeArray(newCapacity);
		for (HashNode<T> node : oldArray) {
			if (node.getStatus() == Status.VALID) {
				this.add(node.getElement());
			}
		}
	}

	/**
	 * Si el factor de carga actual es <= maxLoadFactor, no hace nada. Si lo supera,
	 * calcula el siguiente número primo por encima del doble de la capacidad actual
	 * y llama a updateCapacity(newCapacity).
	 */
	protected void dynamicResize() {
		if (getLoadFactor() <= maxLoadFactor) {
			return;
		}
		int newCapacity = getNextPrimeNumber(this.capacityB * 2);
		updateCapacity(newCapacity);
	}

	/**
	 * Si el factor de carga actual es >= minLoadFactor, no hace nada. Si es menor,
	 * calcula el primo anterior por debajo de la mitad (mínimo 3) y llama a
	 * updateCapacity(target).
	 */
	protected void inverseDynamicResize() {
		if (getLoadFactor() >= minLoadFactor) {
			return;
		}
		int half = Math.max(3, this.capacityB / 2);
		int num = getPreviousPrimeNumber(half);
		if (num < 3)
			num = 3;
		updateCapacity(num);
	}

	/**
	 * Devuelve una representacion en cadena de la tabla hash, mostrando cada indice
	 * y su contenido.
	 *
	 * @return cadena que representa la tabla hash.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < associativeArray.length; i++) {
			sb.append(i).append(INDEX_SEPARATOR_STRING).append(associativeArray[i].toString());
			if (i < associativeArray.length - 1) {
				sb.append(NODE_SPACING_STRING);
			}
		}
		return sb.toString();
	}

}
