package structures.Stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {

    private int defaultCapacity;
    private T[] stackArray;
    private int top;

    public Stack(int size) {
        this.defaultCapacity = size;
        this.stackArray = (T[]) new Object[defaultCapacity];
        this.top = 0;
    }

    public void push(T element) {
        if (top == stackArray.length) {
            resizeArray();
        }
        stackArray[top] = element;
        top++;
    }

    public T pop() {
        if (isEmpty()) {
            resizeArray();
        }
        int oldTop = top;
        T element = stackArray[oldTop - 1];
        top--;
        stackArray[oldTop - 1] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NullPointerException("Stack is empty");
        }
        return stackArray[top - 1];
    }

    public void resizeArray() {
        int newCapacity = stackArray.length * 2;
        T[] newStackArray = Arrays.copyOf(stackArray, newCapacity);
        stackArray = newStackArray;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public String toString() {
        return "Stack: " + Arrays.toString(stackArray) + " Current top: " + top + " Length: " + stackArray.length;

    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }


    private class StackIterator implements Iterator<T> {

        private int currentIndex = top - 1;

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return stackArray[currentIndex--];
        }
    }
}
