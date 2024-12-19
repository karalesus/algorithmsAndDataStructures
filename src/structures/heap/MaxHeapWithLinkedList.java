package structures.heap;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class MaxHeapWithLinkedList<E extends Comparable<E>> implements Heap<E> {

    private final LinkedList<E> elements;

    public MaxHeapWithLinkedList() {
        this.elements = new LinkedList<>();
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

    public int getParentAt(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E peek() {
        if (this.size() == 0) {
            throw new IllegalStateException("Heap is null");
        } else {
            return this.elements.getFirst();
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
        Random random = new Random();
        int dataSize = 10_000;
        MaxHeapWithLinkedList<Integer> heapWithLinkedList = new MaxHeapWithLinkedList<>();
        for (int i = 0; i <= dataSize; i++) {
            heapWithLinkedList.add(random.nextInt(1000));
        }

        MaxHeapWithArrayList<Integer> heapWithArrayList = new MaxHeapWithArrayList<>();
        for (int i = 0; i <= dataSize; i++) {
            heapWithArrayList.add(random.nextInt(1000));
        }

        long arrayListTime = measurePerformanceWithPeek(heapWithArrayList);
        long linkedListTime = measurePerformanceWithPeek(heapWithLinkedList);
        System.out.println("Время выполнения peek в ArrayList: " + arrayListTime + " нс");
        System.out.println("Время выполнения peek в Linked: " + linkedListTime + " нс");

        long arrayListTimePoll = measurePerformanceWithPoll(heapWithArrayList);
        long linkedListTimePoll = measurePerformanceWithPoll(heapWithLinkedList);
        System.out.println("Время выполнения poll в ArrayList: " + arrayListTimePoll + " нс");
        System.out.println("Время выполнения poll в LinkedList: " + linkedListTimePoll + " нс");

    }

    private static long measurePerformanceWithPeek(Heap<Integer> heap) {
        long totalTime = 0;
        long startTime = System.nanoTime();
        heap.peek();
        long endTime = System.nanoTime();
        totalTime += (endTime - startTime);
        return totalTime;
    }

    private static long measurePerformanceWithPoll(Heap<Integer> heap) {
        long totalTime = 0;
        long startTime = System.nanoTime();
        heap.poll();
        long endTime = System.nanoTime();
        totalTime += (endTime - startTime);
        return totalTime;
    }
}
