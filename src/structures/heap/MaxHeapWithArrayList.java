package structures.heap;

import java.util.ArrayList;
import java.util.Collections;

public class MaxHeapWithArrayList<E extends Comparable<E>> implements Heap<E>  {

    private final ArrayList<E> elements;

    public MaxHeapWithArrayList() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    public void heapifyUp(int index) {
        while (index > 0 && less(parent(index), elements.get(index))) {
            int parentAt = getParentAt(index);
            Collections.swap(this.elements, parentAt, index);
            index = parentAt;
        }
    }

    public boolean less(E parent, E child) {
        return parent.compareTo(child) <= 0;
    }

    public E parent(int index) {
        return this.elements.get((index - 1) / 2);
    }

    public E get(int index) {
        return this.elements.get(index);
    }

    public int getParentAt(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E peek() {
        if (this.size() == 0) {
            throw new IllegalStateException("Heap is null");
        } else {
            return this.elements.get(0);
        }
    }

    public E poll() {
        if (this.size() == 0) {
            throw new IllegalStateException("Heap is null");
        } else {
            E element = this.elements.get(0);
            Collections.swap(elements, 0, this.elements.size() - 1);
            this.elements.remove(this.elements.size() - 1);
            this.heapifyDown(0);
            return element;
        }
    }

    public void heapifyDown(int index) {
        while (index < this.elements.size() / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            if (rightChild < this.elements.size() && less(this.elements.get(leftChild), this.elements.get(rightChild))) {
                leftChild = rightChild;
            }

            if (less(this.elements.get(leftChild), this.elements.get(index))) {
                break;
            }

            Collections.swap(this.elements, index, leftChild);
            index = leftChild;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Куча: ");
        for (E element : elements) {
            builder.append("   ").append(element);
        }
        return builder.toString();

    }

    public static void main(String[] args) {
        MaxHeapWithArrayList<Integer> heap = new MaxHeapWithArrayList<>();
        heap.add(5);
        heap.add(3);
        heap.add(6);
        heap.add(8);
        heap.add(14);
        heap.add(25);
        heap.add(1);
        heap.add(89);
        heap.add(45);
        System.out.println("Просмотр первого элемента кучи: " + heap.peek());
        System.out.println(heap);
    }
}
