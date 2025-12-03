package tree;

/**
 * Representa un nodo de un arbol binario de busqueda
 * 
 * @author Yaiza
 * @version 03-12-2025
 */

public class BSTNode<T extends Comparable<T>> {

	private T element;
	private BSTNode<T> left;
	private BSTNode<T> right;

	private int height;

	/**
	 * Construye un nuevo BST node con un elemento especifico
	 * 
	 * Inicializa el nodo sin hijos
	 * 
	 * @param element elemento a guardar en el nodo
	 */
	public BSTNode(T element) {
		this.element = element;
		this.left = null;
		this.right = null;
		this.height = 0;
	}

	/**
	 * @return the element
	 */
	public T getElement() {
		return element;

	}

	/**
	 * @param element the element to set
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * @return the left
	 */
	public BSTNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public BSTNode<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(BSTNode<T> right) {
		this.right = right;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Actualiza la altura dle nodo en funcion de sus hijos Si no tiene hijos sera
	 * 0, si solo uno sera la altura del hijo + 1 y si tiene ambos sera el maximo
	 * entre las alturas de los dos hijos + 1
	 */
	public void updateHeight() {
		if (getLeft() == null && getRight() == null) {
			height = 0;
		} else if (getLeft() == null) {
			height = getRight().getHeight() + 1;
		} else if (getRight() == null) {
			height = getLeft().getHeight() + 1;
		} else {
			height = Math.max(getLeft().getHeight(), getRight().getHeight()) + 1;
		}
	}

	/**
	 * Devuelve el factor de equilibrio. Factor de equilibrio = altura derecha -
	 * altura izquierda
	 * 
	 * @return factor de equilibrio
	 */
	public int getBalanceFactor() {
		int leftHeight;
		int rightHeight;

		if (getLeft() != null) {
			leftHeight = getLeft().getHeight();
		} else {
			leftHeight = -1;
		}

		if (getRight() != null) {
			rightHeight = getRight().getHeight();
		} else {
			rightHeight = -1;
		}

		return rightHeight - leftHeight;
	}

	/**
	 * Compara los elementos del nodo con otro nodo
	 * 
	 * @param otherNode
	 * @return -1, 0, o 1 en funcion de si el elemento es menor, mayor o igual que
	 *         el elemento del otro nodo
	 */
	public int compareTo(BSTNode<T> otherNode) {
		return this.element.compareTo(otherNode.element);
	}

	/**
	 * Returns a visual string representation of the tree structure.
	 * 
	 * Creates a graphical representation with proper indentation and branch
	 * symbols, displaying the tree rotated 90 degrees counterclockwise.
	 *
	 * @param nodeFormat the format to use for displaying node information
	 * @return visual string representation of the tree
	 */
	private String toStringVisual(NodeFormat nodeFormat) {
		StringBuilder stringBuilder = new StringBuilder();
		toStringVisualHelper(stringBuilder, this, nodeFormat, 0, 0);
		return stringBuilder.toString();
	}

	/**
	 * Recursively updates a StringBuilder with a visual string representation of
	 * the tree.
	 * 
	 * Helper method that constructs the tree visualization with proper indentation
	 * and branch symbols (┌, └, ─) to represent the tree structure clearly.
	 *
	 * @param stringBuilder the StringBuilder collecting the output
	 * @param node          the current node being processed
	 * @param nodeFormat    the format to use for displaying node information
	 * @param indent        the current indentation level (number of spaces)
	 * @param position      position indicator: -1 for left child, 0 for root, 1 for
	 *                      right child
	 */
	private void toStringVisualHelper(StringBuilder stringBuilder, BSTNode<T> node, NodeFormat nodeFormat, int indent,
			int position) {

		if (node == null)
			return;

		// Process right subtree first (so it appears on top)
		toStringVisualHelper(stringBuilder, node.getRight(), nodeFormat, indent + 4, 1);

		// Process current node (with proper indentation and branch symbols)
		for (int i = 0; i < indent; i++) {
			if (i >= indent - 2) {
				stringBuilder.append("─"); // Horizontal line
			} else if (i == indent - 3) {
				if (position == 1)
					stringBuilder.append("┌"); // Upper corner for right child
				else if (position == -1)
					stringBuilder.append("└"); // Lower corner for left child
			} else {
				stringBuilder.append(" "); // Space for indentation
			}
		}
		stringBuilder.append(nodeFormat.format(node)).append("\n");

		// Process left subtree
		toStringVisualHelper(stringBuilder, node.getLeft(), nodeFormat, indent + 4, -1);

	}

	/**
	 * Returns a comprehensive string representation of the tree.
	 * 
	 * Provides a visual representation showing each node's element, height, and
	 * balance factor in the format {element|height|balance factor}.
	 *
	 * @return visual string representation of the tree with complete node
	 *         information
	 */
	@Override
	public String toString() {
		String string = "{element|height|balance factor}\n\n\n";
		return string + toStringVisual(NodeFormat.HEIGHT_AND_BALANCE_FACTOR); // Delegate to visual representation
	}

}
