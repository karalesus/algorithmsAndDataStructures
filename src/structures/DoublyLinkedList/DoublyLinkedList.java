package structures.DoublyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public void addFirst(T value) {
        if (head == null) {
            Node<T> node = new Node<>(value, null, null);
            head = node;
            tail = node;
        } else {
            Node<T> node = new Node<>(value, this.head, null);
            head.previous = node;
            head = node;
        }
        length++;
    }

    public void addLast(T value) {
        if (tail == null) {
            Node<T> node = new Node<>(value, null, null);
            tail = node;
            head = node;
        } else {
            Node<T> node = new Node<>(value, null, this.tail);
            tail.next = node;
            tail = node;
        }
        length++;
    }

    public void removeFirst() {
        if (head == null) {
            throw new NullPointerException("Linked list is null");
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        length--;
    }

    public void removeLast() {
        if (head == null) {
            throw new NullPointerException("Linked list is null");
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
        length--;
    }

    public void replaceFirst(T value) {
        if (head == tail) {
            head.value = value;
        } else {
            Node<T> currentNode = head;
            if (currentNode.previous == null) {
                currentNode.value = value;
            }
        }
    }

    public void replaceLast(T value) {
        if (head == tail) {
            tail.value = value;
        } else {
            Node<T> currentNode = tail;
            if (currentNode.next == null) {
                currentNode.value = value;
            }
        }
    }

    public boolean replace(T oldValue, T newValue) {
        Node<T> currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.value.equals(oldValue)) {
                currentNode.value = newValue;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public int getIndex(T value) {
        if (head == null) {
            throw new NullPointerException("Linked list is null");
        }
        if (head.value == value) {
            return 0;
        }
        Node<T> currentNode = head;
        int index = 0;
        while (currentNode != null) {
            index++;
            if (currentNode.next.value == value) {
                return index;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    public T findByIndex(int index) {
        Node<T> currentNode = head;
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Array out of bounds");
        }
        if (index < length / 2) {
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = tail;
            for (int i = length - 1; i > index; i--) {
                currentNode = currentNode.previous;
            }
        }
        return currentNode.value;
    }

    public void deleteByIndex(int index) {
        Node<T> currentNode = head;
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Array out of bounds");
        }
        if (index == 0) {
            removeFirst();
        } else if (index == length - 1) {
            removeLast();
        } else if (index < length / 2) {
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            currentNode.next.previous = currentNode.previous;
            currentNode.previous.next = currentNode.next;
            currentNode.next = null;
            currentNode.previous = null;
            length--;
        } else {
            currentNode = tail;
            for (int i = length - 1; i > index; i--) {
                currentNode = currentNode.previous;
            }
            currentNode.next.previous = currentNode.previous;
            currentNode.previous.next = currentNode.next;
            currentNode.next = null;
            currentNode.previous = null;
            length--;
        }
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("");
            Node<T> currentNode = head;
            while (currentNode.next != null) {
                sb.append(currentNode.value);
                sb.append(",\n");
                currentNode = currentNode.next;
            }
            sb.append(currentNode.value);
            sb.append("");
            return sb.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = currentNode.value;
            currentNode = currentNode.next;
            return value;
        }
    }
}

