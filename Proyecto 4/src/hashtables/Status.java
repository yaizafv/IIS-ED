package hashtables;

/**
 * Implementacion de una clase enumerada que representa el estado de un nodo en
 * una tabla hash cerrada.
 * 
 * @author Yaiza
 * @version 19-12-2025
 */
public enum Status {
	/**
	 * Nodo vacio
	 */
	EMPTY,

	/**
	 * Nodo valido
	 */
	VALID,

	/**
	 * Nodo eliminado
	 */
	DELETED;

	/**
	 * Devuelve un unico caracter que representa el estatus.
	 * 
	 * @return "E" para EMPTY, "V" para VALID, "D" para DELETED, o "?" para
	 *         desconocido
	 */
	public String getStatusInitial() {
		switch (this) {
		case EMPTY:
			return "E";
		case VALID:
			return "V";
		case DELETED:
			return "D";
		default:
			return "?";
		}
	}
}
