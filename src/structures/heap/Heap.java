package structures.heap;

public interface Heap<E extends Comparable<E>> {
    int size();
    void add(E element);
    E peek();
    E poll();
}
