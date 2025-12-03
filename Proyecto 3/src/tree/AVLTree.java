package tree;

/**
 * Implementación de un árbol binario de búsqueda equilibrado (AVL)
 *
 * @author Yaiza
 * @version 03-12-2025
 */

public class AVLTree<T extends Comparable<T>> extends BSTree<T> implements Tree<T> {

	/**
	 * Construye un arbol AVL vacio
	 */
	public AVLTree() {
		super();
	}

	/**
	 * Inserta un elemento en el arbol manteniendo la estructura del AVL. La
	 * insercion se hace como en un BST pero se llama al metodo de equilibrado para
	 * corregir posibles desbalances
	 *
	 * @param currentNode nodo actual durante la recursion
	 * @param element     elemento a insertar
	 * @return nodo actualizado
	 */
	@Override
	BSTNode<T> add(BSTNode<T> currentNode, T element) {
		currentNode = super.add(currentNode, element);
		return updateBalanceFactor(currentNode);
	}

	/**
	 * Elimina un elemento del arbol manteniendo la estructura del AVL. La
	 * eliminación se realiza como en un BST pero se actualiza la altura y se
	 * corrigen posibles desbalances
	 *
	 * @param currentNode nodo actual durante la recursión
	 * @param element     elemento a eliminar
	 * @return nodo actualizado
	 */
	@Override
	BSTNode<T> remove(BSTNode<T> currentNode, T element) {
		currentNode = super.remove(currentNode, element);
		return updateBalanceFactor(currentNode);
	}

	/**
	 * Revisa la altura y el factor de equilibrio del nodo, y si esta desbalanceado
	 * aplica la rotación correspondiente. - +2 → desbalance hacia la derecha
	 * (rotacion simple o doble derecha) - -2 → desbalance hacia la izquierda
	 * (rotacion simple o doble izquierda)
	 *
	 * @param currentNode nodo a revisar
	 * @return nodo tras actualizar altura y aplicar rotación si era necesario
	 */
	public BSTNode<T> updateBalanceFactor(BSTNode<T> currentNode) {
		if (currentNode == null)
			return null;
		currentNode.updateHeight();
		if (currentNode.getBalanceFactor() == 2) {
			if (currentNode.getRight().getBalanceFactor() >= 0) {
				currentNode = singleRightRotation(currentNode);
			} else {
				currentNode = doubleRightRotation(currentNode);
			}
		} else if (currentNode.getBalanceFactor() == -2) {
			if (currentNode.getLeft().getBalanceFactor() <= 0) {
				currentNode = singleLeftRotation(currentNode);
			} else {
				currentNode = doubleLeftRotation(currentNode);
			}
		}
		return currentNode;
	}

	/**
	 * Rotacion simple a la izquierda
	 *
	 * @param currentNode nodo desbalanceado
	 * @return nuevo nodo raíz del subárbol rotado
	 */
	public BSTNode<T> singleLeftRotation(BSTNode<T> currentNode) {
		BSTNode<T> node = currentNode.getLeft();
		currentNode.setLeft(node.getRight());
		node.setRight(currentNode);
		updateBalanceFactor(currentNode);
		return updateBalanceFactor(node);
	}

	/**
	 * Rotacion simple a la derecha
	 *
	 * @param currentNode nodo desbalanceado
	 * @return nuevo nodo raíz del subárbol rotado
	 */
	public BSTNode<T> singleRightRotation(BSTNode<T> currentNode) {
		BSTNode<T> node = currentNode.getRight();
		currentNode.setRight(node.getLeft());
		node.setLeft(currentNode);
		updateBalanceFactor(currentNode);
		return updateBalanceFactor(node);
	}

	/**
	 * Rotacion doble a la izquierda
	 *
	 * @param currentNode nodo desbalanceado
	 * @return nuevo nodo raíz del subárbol rotado
	 */
	public BSTNode<T> doubleLeftRotation(BSTNode<T> currentNode) {
		currentNode.setLeft(singleRightRotation(currentNode.getLeft()));
		return singleLeftRotation(currentNode);

	}

	/**
	 * Rotacion doble a la derecha
	 *
	 * @param currentNode nodo desbalanceado
	 * @return nuevo nodo raíz del subárbol rotado
	 */
	public BSTNode<T> doubleRightRotation(BSTNode<T> currentNode) {
		currentNode.setRight(singleLeftRotation(currentNode.getRight()));
		return singleRightRotation(currentNode);
	}
}
