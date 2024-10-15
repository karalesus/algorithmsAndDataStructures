package structures.BinaryTree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {

    private E key;
    private AbstractBinaryTree<E> leftChild;
    private AbstractBinaryTree<E> rightChild;

    public BinaryTree(E key) {
        this.key = key;
    }

    public BinaryTree(E key, AbstractBinaryTree<E> leftChild, AbstractBinaryTree<E> rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public E getKey() {
        return key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return leftChild;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return rightChild;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) { // n
        StringBuilder tree = new StringBuilder();
        String indentString = " ".repeat(indent * 2);
        tree.append(indentString).append(this.key).append("\n");

        if (this.leftChild != null) {
            tree.append(this.leftChild.asIndentedPreOrder(indent + 1));
        }
        if (this.rightChild != null) {
            tree.append(this.rightChild.asIndentedPreOrder(indent + 1));
        }
        return tree.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> tree = new ArrayList<>();
        tree.add(this);
        if (this.leftChild != null) {
            tree.addAll(leftChild.preOrder());
        }
        if (this.rightChild != null) {
            tree.addAll(rightChild.preOrder());
        }
        return tree;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> tree = new ArrayList<>();
        if (this.leftChild != null){
            tree.addAll(leftChild.inOrder());
        }
        tree.add(this);
        if (this.rightChild != null) {
            tree.addAll(rightChild.inOrder());
        }
        return tree;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> tree = new ArrayList<>();
        if (this.leftChild != null) {
            tree.addAll(leftChild.postOrder());
        }
        if (this.rightChild != null) {
            tree.addAll(rightChild.postOrder());
        }
        tree.add(this);
        return tree;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (this.leftChild != null) {
            leftChild.forEachInOrder(consumer);
        }
        consumer.accept(key);
        if (this.rightChild != null) {
            rightChild.forEachInOrder(consumer);
        }
    }

    @Override
    public void depthFirstSearch() {
        Stack<AbstractBinaryTree<E>> tree = new Stack<>();
        tree.push(this);
        while (!tree.isEmpty()) {
            AbstractBinaryTree<E> currentNode = tree.pop();
            System.out.println(currentNode.getKey());
            if (currentNode.getRight() != null) {
                tree.push(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                tree.push(currentNode.getLeft());
            }
        }
    }

    @Override
    public void breadthFirstSearch() {
        Queue<AbstractBinaryTree<E>> tree = new LinkedList<>();
        tree.add(this);
        while (!tree.isEmpty()){
            AbstractBinaryTree<E> currentNode = tree.poll();
            System.out.println(currentNode.getKey());
            if (currentNode.getLeft() != null) {
                tree.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                tree.add(currentNode.getRight());
            }
        }
    }
}
