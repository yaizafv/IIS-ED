package graph.exceptions;

public class FullStructureException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FullStructureException() {
		super("Graph structure is at maximum capacity, cannot add more elements.");
	}

	public FullStructureException(String message) {
		super(message);
	}

	public FullStructureException(int capacity) {
		super("Graph structure is at maximum capacity (" + capacity + "), cannot add more elements.");
	}

}