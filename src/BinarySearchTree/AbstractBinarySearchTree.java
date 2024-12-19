package BinarySearchTree;

import structures.BinaryTree.AbstractBinaryTree;

public interface AbstractBinarySearchTree<E extends Comparable<E>> {


    String print();

    public static class Node<E> {
        public E value;
        public Node<E> leftChild;
        public Node<E> rightChild;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> leftChild, Node<E> rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        @Override
        public String toString() {
            return "Узел {" +
                    "значение = " + value +
                    ", левый потомок = " + leftChild +
                    ", правый потомок = " + rightChild +
                    '}';
        }
    }

    void insert(E element);

    boolean contains(E element);

    AbstractBinarySearchTree<E> search(E element);

    Node<E> getRoot();

    Node<E> getLeft();

    Node<E> getRight();

    E getValue();

    void balance();
}
