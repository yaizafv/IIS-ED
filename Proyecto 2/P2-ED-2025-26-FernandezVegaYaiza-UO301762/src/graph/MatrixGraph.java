package graph;

import java.text.DecimalFormat;

import graph.exceptions.ElementNotPresentException;
import graph.exceptions.FullStructureException;

/**
 * Proyecto 2 de Estructura de Datos
 * 
 * @author Yaiza Fernandez Vega
 */

public class MatrixGraph<T> implements Graph<T> {

	private T[] nodes;
	private boolean[][] edges;
	private double[][] weights;
	private int size;
	private int capacity;

	private double[][] floydCostsA;
	private int[][] floydPathsP;
	private boolean floydCalculated;

	static final int INDEX_NOT_FOUND = -1;
	static final int WEIGHT_NOT_FOUND = -1;
	public static final double INFINITY = Double.POSITIVE_INFINITY;
	public static final int NO_PREDECESSOR = -1;
	public static final int EMPTY = -1;

	/**
	 * Constructor de MatrixGraph que recibe capacity como parametro
	 * 
	 * @param capacity capacidad
	 */
	@SuppressWarnings("unchecked")
	public MatrixGraph(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacidad no puede ser <= 0");
		}
		this.capacity = capacity;
		nodes = (T[]) new Object[capacity];
		weights = new double[capacity][capacity];
		edges = new boolean[capacity][capacity];
		floydCostsA = new double[capacity][capacity];
		floydPathsP = new int[capacity][capacity];
		size = 0;
	}

	/**
	 * Devuelve los nodos
	 * 
	 * @return the nodes
	 */
	public T[] getNodes() {
		return nodes;
	}

	/**
	 * Devuelve la matriz de aristas
	 * 
	 * @return the edges
	 */
	public boolean[][] getEdges() {
		return edges;
	}

	/**
	 * Devuelve la matriz de pesos
	 * 
	 * @return the weights
	 */
	public double[][] getWeights() {
		return weights;
	}

	/**
	 * Devuelve la capacidad
	 * 
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Devuelve floydCostsA
	 * 
	 * @return the floydCostsA
	 */
	public double[][] getFloydCostsA() {
		return floydCostsA;
	}

	/**
	 * Devuelve floydPathsP
	 * 
	 * @return the floydPathsP
	 */
	public int[][] getFloydPathsP() {
		return floydPathsP;
	}

	/**
	 * Devuelve el indice del nodo pasado como parametro Si no encuentra el nodo,
	 * devuelve INDEX_NOT_FOUND
	 * 
	 * @param element nodo
	 * @return indice o INDEX_NOT_FOUND
	 */
	int getNodeIndex(T element) {
		for (int i = 0; i < size; i++) {
			if (nodes[i].equals(element)) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Devuelve el tamaño del grafo.
	 * 
	 * @return size tamaño del grafo
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Comprueba que el nodo pasado como parametro exista.
	 * 
	 * @param element nodo
	 * @return true en caso de que exista, false en caso contrario
	 */
	public boolean existsNode(T element) {
		checkNull(element);
		return getNodeIndex(element) != INDEX_NOT_FOUND;
	}

	/**
	 * Comprueba que el nodo pasado como parametro no sea null. Si lo es lanza
	 * excepcion.
	 * 
	 * @param element nodo
	 */
	private void checkNull(T element) {
		if (element == null) {
			throw new NullPointerException("element no puede ser null");
		}
	}

	/**
	 * Comprueba que exista una arista entre dos nodos.
	 * 
	 * @param originElement      nodo origen
	 * @param destinationElement nodo destino
	 * @return true si existe, false en caso contrario
	 */
	public boolean existsEdge(T originElement, T destinationElement) {
		checkNull(originElement);
		checkNull(destinationElement);
		int originIndex = getNodeIndex(originElement);
		int destinationIndex = getNodeIndex(destinationElement);
		if (originIndex == INDEX_NOT_FOUND) {
			return false;
		}
		if (destinationIndex == INDEX_NOT_FOUND) {
			return false;
		}
		return edges[originIndex][destinationIndex];
	}

	/**
	 * Devuelve el peso entre dos nodos.
	 * 
	 * @param originElement      nodo origen
	 * @param destinationElement nodo destino
	 * @return peso entre dos nodos en caso de que haya, sino WEIGHT_NOT_FOUND
	 */
	public double getWeight(T originElement, T destinationElement) {
		checkNull(originElement);
		checkNull(destinationElement);
		int originIndex = getValidNodeIndex(originElement);
		int destinationIndex = getValidNodeIndex(destinationElement);
		if (!edges[originIndex][destinationIndex]) {
			return WEIGHT_NOT_FOUND;
		}
		return weights[originIndex][destinationIndex];
	}

	/**
	 * Devuelve el indice de un nodo. Si el nodo no existe se lanza excepcion.
	 * 
	 * @param element nodo
	 * @return indice o INDEX_NOT_FOUND
	 */
	private int getValidNodeIndex(T element) {
		checkNull(element);
		if (getNodeIndex(element) == INDEX_NOT_FOUND) {
			throw new ElementNotPresentException();
		}
		return getNodeIndex(element);
	}

	/**
	 * Añade un nodo al array.
	 * 
	 * @param element nodo a añadir
	 * @return true si se puede añadir, false en caso contrario
	 */
	public boolean addNode(T element) {
		checkNull(element);
		checkStructureNotFull();
		if (existsNode(element)) {
			return false;
		}
		int insertPosition = size;
		nodes[insertPosition] = element;
		for (int i = 0; i < size; i++) {
			edges[insertPosition][i] = false;
			edges[i][insertPosition] = false;
			weights[insertPosition][i] = 0.0;
			weights[i][insertPosition] = 0.0;
		}
		size++;
		floydCalculated = false;
		return true;
	}

	/**
	 * Lanza excepcion si el tamaño es mayor que la capacidad.
	 */
	private void checkStructureNotFull() {
		if (size >= capacity) {
			throw new FullStructureException();
		}
	}

	/**
	 * Añade una arista a la matriz de aristas.
	 * 
	 * @param originElement      nodo origen
	 * @param destinationElement nodo destino
	 * @param weight             peso
	 * @return true si se puede añadir, false en caso contrario
	 */
	public boolean addEdge(T originElement, T destinationElement, double weight) {
		int originIndex = getValidNodeIndex(originElement);
		int destinationIndex = getValidNodeIndex(destinationElement);
		if (weight <= 0) {
			throw new IllegalArgumentException("Weight no puede ser <= 0");
		}
		if (existsEdge(originElement, destinationElement)) {
			return false;
		}
		edges[originIndex][destinationIndex] = true;
		weights[originIndex][destinationIndex] = weight;
		floydCalculated = false;
		return true;
	}

	/**
	 * Elimina una arista de la matriz de aristas.
	 * 
	 * @param originElement      nodo origen
	 * @param destinationElement nodo destino
	 * @return true si se puede eliminar, false en caso contrario
	 */
	public boolean removeEdge(T originElement, T destinationElement) {
		int originIndex = getValidNodeIndex(originElement);
		int destinationIndex = getValidNodeIndex(destinationElement);
		if (!existsEdge(originElement, destinationElement)) {
			return false;
		}
		edges[originIndex][destinationIndex] = false;
		weights[originIndex][destinationIndex] = 0.0;
		floydCalculated = false;
		return true;
	}

	/**
	 * Elimina un nodo.
	 * 
	 * @param element nodo a eliminar
	 * @return true si se puede eliminar, false en caso contrario
	 */
	public boolean removeNode(T element) {
		checkNull(element);
		int pos = getNodeIndex(element);
		if (pos < 0) {
			return false;
		}
		size--;
		if (pos == size) {
			return true;
		}
		nodes[pos] = nodes[size];
		for (int i = 0; i < size; i++) {
			edges[i][pos] = edges[i][size];
			edges[pos][i] = edges[size][i];
			weights[i][pos] = weights[i][size];
			weights[pos][i] = weights[size][i];
		}
		edges[pos][pos] = edges[size][size];
		weights[pos][pos] = weights[size][size];
		floydCalculated = false;

		return true;
	}

	/**
	 * Devuelve true si el nodo pasado como parametro es fuente, false en caso
	 * contrario
	 * 
	 * @param element nodo
	 * @return true si el nodo es fuente, false en caso contrario
	 */
	public boolean isSourceNode(T element) {
		// un nodo fuente tiene salidas (true en su fila) pero ninguna entrada (false en
		// su columna)
		int index = getValidNodeIndex(element);
		return hasOutEdge(index) && !hasInEdge(index);
	}

	/**
	 * Verifica si el nodo en la posición dada tiene al menos una arista saliente.
	 * 
	 * @param index posicion
	 * @return true si tiene al menos una arista saliente, false en caso contrario
	 */
	private boolean hasOutEdge(int index) {
		for (int i = 0; i < size; i++) {
			if (edges[index][i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica si el nodo en la posición dada tiene al menos una arista entrante.
	 * 
	 * @param index posicion
	 * @return true si tiene al menos una arista entrante, false en caso contrario
	 */
	private boolean hasInEdge(int index) {
		for (int i = 0; i < size; i++) {
			if (edges[i][index]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devuelve true si el nodo pasado como parametro es sumidero, false en caso
	 * contrario
	 * 
	 * @param element nodo
	 * @return true si el nodo es sumidero, false en caso contrario
	 */
	public boolean isDrainNode(T element) {
		int index = getValidNodeIndex(element);
		return !hasOutEdge(index) && hasInEdge(index);
	}

	/**
	 * Devuelve true si el nodo pasado como parametro esta aislado, false en caso
	 * contrario
	 * 
	 * @param element nodo
	 * @return true si el nodo esta aislado, false en caso contrario
	 */
	public boolean isIsolatedNode(T element) {
		int index = getValidNodeIndex(element);
		return !hasOutEdge(index) && !hasInEdge(index);
	}

	/**
	 * Returns a string representation of the graph. It includes the nodes and
	 * adjacency matrices.
	 * 
	 * @return a string representation of the graph
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String result = "";

		result += "NODES\n";
		for (int column = 0; column < size; column++) {
			result += nodes[column].toString() + "\t";
		}

		result += "\n\nEDGES\n";
		// Add column headers
		result += "\t"; // Empty cell for corner
		for (int column = 0; column < size; column++) {
			result += nodes[column] + "\t";
		}
		result += "\n";

		// Add row labels and edge data
		for (int row = 0; row < size; row++) {
			result += nodes[row] + "\t"; // Row label
			for (int columns = 0; columns < size; columns++) {
				result += edges[row][columns] ? "T\t" : "F\t";
			}
			result += "\n";
		}

		result += "\nWEIGHTS\n";
		// Add column headers
		result += "\t"; // Empty cell for corner
		for (int column = 0; column < size; column++) {
			result += nodes[column] + "\t";
		}
		result += "\n";

		// Add row labels and weight data
		for (int row = 0; row < size; row++) {
			result += nodes[row] + "\t"; // Row label
			for (int column = 0; column < size; column++) {
				result += (edges[row][column] ? df.format(weights[row][column]) : "-") + "\t";
			}
			result += "\n";
		}

		return result;
	}

	/**
	 * Ejecuta el algoritmo de Dijkstra desde un nodo origen.
	 * 
	 * @param startingElement nodo origen
	 * @return DijkstraDataClass dijkstra
	 */
	public DijkstraDataClass dijkstra(T startingElement) {
		int index = getValidNodeIndex(startingElement);
		DijkstraDataClass dijkstra = new DijkstraDataClass(size, getWeights(), index);
		boolean[] visitedPivotsS = new boolean[size];

		int pivot = index;

		while (pivot != DijkstraDataClass.NO_MORE_PIVOTS) {
			visitedPivotsS[pivot] = true;
			double originToPivotCost = dijkstra.getDijkstraCostsDValue(pivot);

			for (int i = 0; i < size; i++) {
				if (weights[pivot][i] > 0 && !visitedPivotsS[i]) {
					double pivotToTargetCost = weights[pivot][i];
					double newOriginToTargetCost = originToPivotCost + pivotToTargetCost;
					double currentCost = dijkstra.getDijkstraCostsDValue(i);

					if (newOriginToTargetCost < currentCost) {
						dijkstra.updateDijkstraCostsD(i, newOriginToTargetCost);
						dijkstra.updateDijkstraPathsP(i, pivot);
					}
				}
			}
			pivot = dijkstra.getPivot(visitedPivotsS, dijkstra.getDijkstraCostsD());
		}
		return dijkstra;
	}

	/**
	 * Devuelve el coste mínimo entre dos nodos usando Dijkstra.
	 * 
	 * @param origin      nodo origen
	 * @param destination nodo destino
	 * @return coste mínimo del camino
	 */
	public double minimumCostPathDijkstra(T origin, T destination) {
		getValidNodeIndex(origin);
		int destIndex = getValidNodeIndex(destination);
		DijkstraDataClass dijkstra = dijkstra(origin);
		return dijkstra.getDijkstraCostsDValue(destIndex);
	}

	/**
	 * Realiza un recorrido en profundidad desde un nodo.
	 * 
	 * @param origin nodo origen
	 * @return cadena con el recorrido
	 */
	public String printDepthFirstTraversal(T origin) {
		int index = getValidNodeIndex(origin);
		boolean[] visitedNodes = new boolean[size];
		return printDepthFirstTraversalRecursive(index, visitedNodes);
	}

	/**
	 * Realiza un recorrido en profundidad desde un nodo de forma recursiva.
	 * 
	 * @param index        indice
	 * @param visitedNodes nodos visitados
	 * @return cadena con el recorrido
	 */
	private String printDepthFirstTraversalRecursive(int index, boolean[] visitedNodes) {
		String path = nodes[index].toString() + "-";
		visitedNodes[index] = true;
		for (int i = 0; i < size; i++) {
			if (edges[index][i] && !visitedNodes[i]) {
				path += printDepthFirstTraversalRecursive(i, visitedNodes);
			}
		}
		return path;
	}

	/**
	 * Inicializa las matrices para el algoritmo de Floyd.
	 */
	private void initializeFloydMatrices() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (row == col) {
					floydCostsA[row][col] = 0;
				} else if (!edges[row][col]) {
					floydCostsA[row][col] = INFINITY;
				} else {
					floydCostsA[row][col] = weights[row][col];
				}
				floydPathsP[row][col] = EMPTY;
			}
		}
	}

	/**
	 * Ejecuta el algoritmo de Floyd.
	 * 
	 * @return true si se ejecuto correctamente, false si el grafo esta vacio
	 */
	public boolean floyd() {
		if (size == 0)
			return false;
		initializeFloydMatrices();
		for (int pivot = 0; pivot < size; pivot++) {
			for (int origin = 0; origin < size; origin++) {
				for (int destination = 0; destination < size; destination++) {
					double originDestination = floydCostsA[origin][destination];
					double originPivot = floydCostsA[origin][pivot];
					double pivotDestination = floydCostsA[pivot][destination];
					double newCost = originPivot + pivotDestination;
					if (newCost < originDestination) {
						floydCostsA[origin][destination] = newCost;
						floydPathsP[origin][destination] = pivot;
					}
				}
			}
		}
		floydCalculated = true;
		return true;
	}

	/**
	 * Imprime el camino obtenido al hacer Floyd.
	 * 
	 * @param origin      nodo origen
	 * @param destination nodo destino
	 * @return cadena con el camino obtenido
	 */
	public String printFloydPath(T origin, T destination) {
		int originIndex = getValidNodeIndex(origin);
		int destinationIndex = getValidNodeIndex(destination);
		if (!floydCalculated) {
			floyd();
		}
		if (floydCostsA[originIndex][destinationIndex] == INFINITY) {
			return origin + "_NO_PATH_FOUND_TO_" + destination;
		}
		StringBuilder path = new StringBuilder();
		path.append(origin.toString());
		buildFloydPath(originIndex, destinationIndex, path);
		path.append(destination.toString());
		return path.toString();
	}

	/**
	 * Construye la cadena con el camino obtenido al hacer Floyd
	 * 
	 * @param originIndex      nodo origen
	 * @param destinationIndex nodo destino
	 * @param path             camino
	 */
	private void buildFloydPath(int originIndex, int destinationIndex, StringBuilder path) {
		int pivot = floydPathsP[originIndex][destinationIndex];
		if (pivot != EMPTY) {
			buildFloydPath(originIndex, pivot, path);
			path.append(nodes[pivot].toString());
			buildFloydPath(pivot, destinationIndex, path);
		}
	}

	/**
	 * Devuelve el coste mínimo entre dos nodos usando Floyd.
	 * 
	 * @param origin      nodo origen
	 * @param destination nodo destino
	 * @return coste mínimo del camino
	 */
	public double minimumCostPathFloyd(T origin, T destination) {
		int originIndex = getValidNodeIndex(origin);
		int destinationIndex = getValidNodeIndex(destination);
		if (!floydCalculated) {
			floyd();
		}
		double cost = floydCostsA[originIndex][destinationIndex];
		return cost;
	}

}
