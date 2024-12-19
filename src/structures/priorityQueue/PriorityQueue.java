package structures.priorityQueue;

import structures.heap.MaxHeapWithArrayList;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private final MaxHeapWithArrayList<E> heap;

    public PriorityQueue() {
        this.heap = new MaxHeapWithArrayList<>();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void add(E element) {
        heap.add(element);
    }

    @Override
    public E peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Очередь пустая");
        }
        return heap.peek();
    }

    @Override
    public E poll() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Очередь с приоритетом пуста");

        } else {
            return heap.poll();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < heap.size(); i++) {
            builder.append("   ").append(heap.get(i));
        }
        return builder.toString();

    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(6);
        queue.add(5);
        queue.add(3);
        queue.add(2);
        queue.add(7);
        queue.add(15);
        queue.add(28);
        queue.add(33);
        queue.add(-5);
        queue.add(4);
        System.out.println("Очередь до удаления: " + queue);
        System.out.println("Максимальный элемент: " + queue.peek());
        System.out.println("Удаляем максимальный элемент: " + queue.poll());
        System.out.println("Удаляем максимальный элемент: " + queue.poll());
        System.out.print("Очередь после удаления: " + queue);
    }
}
