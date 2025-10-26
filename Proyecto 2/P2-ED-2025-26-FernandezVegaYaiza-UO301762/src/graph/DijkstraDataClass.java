package graph;

/**
 * @author Nestor
 * @version 2022-23
 */
public class DijkstraDataClass {

	final static double INFINITY = Double.POSITIVE_INFINITY;
	final static int NO_PREDECESSOR = -1;
	final static int NO_MORE_PIVOTS = -1;

	int startingNodeIndex;
	double[] dijkstraCostsD;// Dijkstra's D Cost Vector
	int[] dijkstraPathsP; // Dijkstra's P Paths Vector

	/**
	 * Constructor de DijkstraDataClass que recibe nodeNumber, weights y startingNodeIndex como parametros
	 * 
	 * @param nodeNumber numero de nodos
	 * @param weights matriz de pesos
	 * @param startingNodeIndex indice del nodo inicial
	 */
	public DijkstraDataClass(int nodeNumber, double[][] weights, int startingNodeIndex) {
		this.startingNodeIndex = startingNodeIndex;
		this.dijkstraCostsD = new double[nodeNumber]; // Dijkstra's D Cost Vector
		this.dijkstraPathsP = new int[nodeNumber]; // Dijkstra's P Paths Vector

		for (int i = 0; i < nodeNumber; i++) {
			if (i == startingNodeIndex) {
                dijkstraCostsD[i] = 0;
                dijkstraPathsP[i] = NO_PREDECESSOR;
			} else if (weights[startingNodeIndex][i] == 0) {
                dijkstraCostsD[i] = INFINITY;
                dijkstraPathsP[i] = NO_PREDECESSOR;
			} else if (weights[startingNodeIndex][i] > 0){
				dijkstraCostsD[i] = weights[startingNodeIndex][i];
                dijkstraPathsP[i] = startingNodeIndex;
			}
	    }
	}
	
	/**
	 * Devuelve el pivote
	 * 
	 * @param visitedNodes	nodos visitados
	 * @param dijkstraCostsD	array de costes
	 * @return	pivote
	 */
	public int getPivot(boolean[] visitedNodes, double[] dijkstraCostsD) {
		double lowestCost = INFINITY;
		int lowestIndex = NO_MORE_PIVOTS;
		for (int i = 0; i < dijkstraCostsD.length; i++) {
			if (!visitedNodes[i] && dijkstraCostsD[i] < lowestCost) {
				lowestCost = dijkstraCostsD[i];
				lowestIndex = i;				
			}
		}
		return lowestIndex;
	}

	/**
	 * Devuelve el indice del nodo inicial
	 * 
	 * @return indice del nodo inicial
	 */
	public int getStartingNodeIndex() {
		return startingNodeIndex;
	}

	/**
	 * Establece el indice del nodo inicial
	 * 
	 * @param nodeIndex	indice del nodo 
	 */
	public void setStartingNodeIndex(int nodeIndex) {
		this.startingNodeIndex = nodeIndex;
	}

	/**
	 * Devuelve el array de costes
	 * 
	 * @return array de costes
	 */
	public double[] getDijkstraCostsD() {
		return dijkstraCostsD;
	}

	/**
	 * Devuelve el array de caminos
	 * 
	 * @return	array de caminos
	 */
	public int[] getDijkstraPathsP() {
		return dijkstraPathsP;
	}

	/**
	 * Establece el arrat de caminos
	 * 
	 * @param dijkstraPathsP	array de caminos
	 */
	public void setDijkstraPathsP(int[] dijkstraPathsP) {
		this.dijkstraPathsP = dijkstraPathsP;
	}

	/**
	 * Actualiza el array de costes
	 * 
	 * @param index	indice
	 * @param cost	coste
	 */
	public void updateDijkstraCostsD(int index, double cost) {
		dijkstraCostsD[index] = cost;
	}

	/**
	 * Actualiza el array de caminos
	 * 
	 * @param index	indice
	 * @param predecessor	predecesor
	 */
	public void updateDijkstraPathsP(int index, int predecessor) {
		dijkstraPathsP[index] = predecessor;
	}

	/**
	 * Devuelve el coste de un elemento del array de costes
	 * @param index	indice
	 * @return	coste de un elemento del array de costes
	 */
	public double getDijkstraCostsDValue(int index) {
		return dijkstraCostsD[index];
	}

	/**
	 * Devuelve el camino de un elemento del array de caminos
	 * @param index	indice
	 * @return	camino de un elemento del array de caminos
	 */
	public int getDijkstraPathsPValue(int index) {
		return dijkstraPathsP[index];
	}

	/**
	 * Devuelve el toString la clase.
	 * 
	 * @return cadena
	 */
	@Override
	public String toString() {

		String result = "From " + startingNodeIndex;

		result += "\nTarget node indexes:\t";
		for (int nodeIndex = 0; nodeIndex < dijkstraCostsD.length; nodeIndex++) {
			result += nodeIndex + "\t";
		}

		result += "\nDijkstra Costs D:\t";
		for (double cost : dijkstraCostsD) {
			if (cost == INFINITY) {
				result += "INF\t";
			} else {
				result += cost + "\t";
			}
		}

		result += "\nDijkstra Paths P:\t";
		for (int path : dijkstraPathsP) {
			if (path == NO_PREDECESSOR) {
				result += "-\t";
			} else {
				result += path + "\t";
			}

		}

		return result;
	}

}
