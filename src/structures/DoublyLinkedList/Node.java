package structures.DoublyLinkedList;

public class Node<T> {
    T value;
    Node<T> next;
    Node<T> previous;

    public Node(T value, Node<T> next, Node<T> previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "structures.DoublyLinkedList.Node{" +
                "value=" + value +
                '}';
    }
}
