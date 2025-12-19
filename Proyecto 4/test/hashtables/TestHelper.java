package hashtables;

public class TestHelper {

	public static <T> String expectedHashTableNodes(Status[] statuses, T[] elements) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < statuses.length; i++) {
			String statusInitial = statuses[i].getStatusInitial();
			T element = elements[i];
			String elementString = element != null ? element.toString() : HashNode.NULL_ELEMENT_STRING;
			sb.append(i).append(ClosedHashTable.INDEX_SEPARATOR_STRING).append(HashNode.OPEN_NODE_STRING)
					.append(statusInitial).append(HashNode.NODE_SEPARATOR_STRING).append(elementString)
					.append(HashNode.CLOSE_NODE_STRING).append(ClosedHashTable.NODE_SPACING_STRING);
		}

		// Remove last space
		return sb.substring(0, sb.length() - ClosedHashTable.NODE_SPACING_STRING.length());
	}

	public static <T> void setHashTableNodes(ClosedHashTable<T> hashTable, Status[] statuses, T[] elements) {
		// Reset element number and capacity
		hashTable.capacityB = statuses.length;
		hashTable.elementNumber = 0;
		for (int i = 0; i < hashTable.associativeArray.length; i++) {
			hashTable.associativeArray[i].setStatus(statuses[i]);
			hashTable.associativeArray[i].setElement(elements[i]);

			// If the status is VALID, increment the element number
			hashTable.elementNumber += statuses[i] == Status.VALID ? 1 : 0;
		}
	}

	public static <T> void addElements(HashTable<T> hashTable, T[] elements) {
		for (T element : elements) {
			hashTable.add(element);
		}
	}

	public static <T> void setAsDeletedHelper(ClosedHashTable<T> table, int index, T element) {
		// Set the node at the specified index as DELETED with the given element.
		HashNode<T> node = table.associativeArray[index];
		node.setElement(element);
		node.setStatus(Status.DELETED);
	}
}
