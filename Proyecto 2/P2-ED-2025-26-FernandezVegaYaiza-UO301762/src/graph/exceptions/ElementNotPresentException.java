package graph.exceptions;

public class ElementNotPresentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ElementNotPresentException() {
		super("The element does not exist.");
	}

	public ElementNotPresentException(String message) {
		super(message);
	}

	public ElementNotPresentException(Object element) {
		super("The element " + element + " does not exist.");
	}
}
