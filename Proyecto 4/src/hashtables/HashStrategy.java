package hashtables;

/**
 * Implementacion de una clase enumerada que determina la forma de resolver
 * colisiones
 * 
 * @author Yaiza
 * @version 19-12-2025
 */
public enum HashStrategy {
	/**
	 * Resolucion de colisiones mediante sondeo lineal
	 */
	LINEAR_PROBING,

	/**
	 * Resolucion de colisiones mediante sondeo cuadrático.
	 */
	QUADRATIC_PROBING,

	/**
	 * Resolucion de colisiones mediante doble hash.
	 */
	DOUBLE_HASHING;
}
