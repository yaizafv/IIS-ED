package tree;

/**
 * Implementacion de un arbol binario de busqueda (BST)
 *
 * @author Yaiza
 * @version 03-12-2025
 */
public class BSTree<T extends Comparable<T>> implements Tree<T> {

	private BSTNode<T> root;

	/**
	 * Construye un arbol binario vacio
	 * 
	 * El arbol se inicializa sin nodos y la raiz se pone como null
	 */
	public BSTree() {
		this.root = null;
	}

	/**
	 * @return the raiz
	 */
	public BSTNode<T> getRoot() {
		return root;
	}

	/**
	 * @param raiz the raiz to set
	 */
	public void setRoot(BSTNode<T> raiz) {
		this.root = raiz;
	}

	/**
	 * Inserta un elemento en el arbol
	 * 
	 * @throws NullPointerException     si element es null
	 * @throws IllegalArgumentException si el elemento ya existe
	 * @param element elemento a insertar
	 */
	public void add(T element) {
		if (element == null) {
			throw new NullPointerException("element no puede ser null");
		}
		root = add(root, element);
	}

	/**
	 * Inserta un elemento recursivamente comenzando desde currentNode
	 *
	 * @param currentNode nodo actual del árbol
	 * @param element     elemento a insertar
	 * @return el nodo actualizado tras la inserción
	 */
	BSTNode<T> add(BSTNode<T> currentNode, T element) {
		if (currentNode == null) {
			return new BSTNode<T>(element);
		}
		int cmp = element.compareTo(currentNode.getElement());
		if (cmp == 0) {
			throw new IllegalArgumentException("No se aceptan duplicados");
		}
		if (cmp < 0) {
			currentNode.setLeft(add(currentNode.getLeft(), element));
		} else if (cmp > 0) {
			currentNode.setRight(add(currentNode.getRight(), element));
		}
		currentNode.updateHeight();
		return currentNode;
	}

	/**
	 * Busca un elemento en el árbol usando búsqueda binaria
	 * 
	 * @throws NullPointerException si element es null
	 * @param element elemento a buscar
	 * @return true si se encuentra, false en caso contrario
	 */
	public boolean search(T element) {
		if (element == null) {
			throw new NullPointerException("element no puede ser null");
		}
		return search(root, element);
	}

	/**
	 * Busca un elemento de forma recursiva
	 *
	 * @param currentNode nodo actual
	 * @param element     elemento que se esta buscando
	 * @return true si se encuentra el elemento, false en caso contrario
	 */
	private boolean search(BSTNode<T> currentNode, T element) {
		if (currentNode == null) {
			return false;
		}
		int cmp = element.compareTo(currentNode.getElement());
		if (cmp == 0) {
			return true;
		}
		if (cmp < 0) {
			return search(currentNode.getLeft(), element);
		} else {
			return search(currentNode.getRight(), element);
		}
	}

	/**
	 * Recorre el arbol en preorden mostrando solo los elementos
	 *
	 * @return cadena con el recorrido en preorden
	 */
	public String preorderTraversal() {
		return preorderTraversal(NodeFormat.BASIC);
	}

	/**
	 * Recorre el arbol en preorden mostrando elemento y altura
	 *
	 * @return cadena con el recorrido formateado
	 */
	public String preorderTraversalHeight() {
		return preorderTraversal(NodeFormat.HEIGHT);
	}

	/**
	 * Recorre el arbol en preorden mostrando elemento, altura y factor de
	 * equilibrio
	 *
	 * @return cadena con el recorrido formateado
	 */
	public String preorderTraversalHeightAndBalanceFactor() {
		return preorderTraversal(NodeFormat.HEIGHT_AND_BALANCE_FACTOR);
	}

	/**
	 * Recorre el arbol en preorden aplicando el formato pasado como parametro
	 *
	 * @param nodeFormat formato para mostrar cada nodo
	 * @return cadena con el recorrido
	 */
	public String preorderTraversal(NodeFormat nodeFormat) {
		if (root == null) {
			return nodeFormat.formatNullNode();
		}
		return preorderTraversal(root, nodeFormat);
	}

	/**
	 * Recorre en preorden de forma recursiva
	 *
	 * @param currentNode nodo actual
	 * @param nodeFormat  formato para mostrar el nodo
	 * @return cadena con formato nodo - subarbol izquierdo - derecho
	 */
	private String preorderTraversal(BSTNode<T> currentNode, NodeFormat nodeFormat) {
		if (currentNode == null) {
			return nodeFormat.formatNullNode();
		}
		String currentNodeString = nodeFormat.format(currentNode);
		String leftSubtree = preorderTraversal(currentNode.getLeft(), nodeFormat);
		String rightSubtree = preorderTraversal(currentNode.getRight(), nodeFormat);
		return currentNodeString + leftSubtree + rightSubtree;
	}

	/**
	 * Devuelve el nodo con el valor maximo de un subárbol.
	 *
	 * @throws NullPointerException si currentNode es null
	 * @param currentNode nodo desde el que empezar
	 * @return nodo con el valor máximo
	 */
	public BSTNode<T> getMaxNode(BSTNode<T> currentNode) {
		if (currentNode == null) {
			throw new NullPointerException("nodo no puede ser null");
		}

		if (currentNode.getRight() == null) {
			return currentNode;
		}

		return getMaxNode(currentNode.getRight());
	}

	/**
	 * Elimina un elemento del arbol
	 * 
	 * @throws NullPointerException si el elemento es null
	 * @param element
	 */
	public void remove(T element) {
		if (element == null) {
			throw new NullPointerException("element no puede ser null");
		}
		root = remove(root, element);

	}

	/**
	 * Elimina un elemento recursivamente desde currentNode.
	 *
	 * @throws IllegalArgumentException si currentNode es null
	 * @param currentNode nodo actual
	 * @param element     elemento a eliminar
	 * @return nodo actualizado tras la eliminación
	 */
	BSTNode<T> remove(BSTNode<T> currentNode, T element) {
		if (currentNode == null) {
			throw new IllegalArgumentException("nodo actual no puede ser null");
		}
		int cmp = element.compareTo(currentNode.getElement());
		if (cmp < 0) {
			currentNode.setLeft(remove(currentNode.getLeft(), element));
		} else if (cmp > 0) {
			currentNode.setRight(remove(currentNode.getRight(), element));
		} else {
			if (currentNode.getLeft() == null) {
				return currentNode.getRight();
			} else if (currentNode.getRight() == null) {
				return currentNode.getLeft();
			} else {
				T maxLeftElement = getMaxNode(currentNode.getLeft()).getElement();
				currentNode.setElement(maxLeftElement);
				BSTNode<T> updatedLeftChild = remove(currentNode.getLeft(), maxLeftElement);
				currentNode.setLeft(updatedLeftChild);
			}
		}
		currentNode.updateHeight();
		return currentNode;
	}

}
