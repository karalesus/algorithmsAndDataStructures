package structures.BinaryTree;

import BinarySearchTree.AbstractBinarySearchTree;

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
        if (this.leftChild != null) {
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
            System.out.print(currentNode.getKey() + " ");
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
        while (!tree.isEmpty()) {
            AbstractBinaryTree<E> currentNode = tree.poll();
            System.out.print(currentNode.getKey() + " ");
            if (currentNode.getLeft() != null) {
                tree.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                tree.add(currentNode.getRight());
            }
        }
    }

    @Override
    public String toString() {
        System.out.println(print());
        return "";
    }

    @Override
    public String print() {
        if (this == null) return "";

        StringBuilder result = new StringBuilder();
        List<List<String>> lines = new ArrayList<>();
        List<AbstractBinaryTree<E>> currentLevel = new ArrayList<>();
        List<AbstractBinaryTree<E>> nextLevel = new ArrayList<>();
        int widestNode = 0;
        int nodesInLevel = 1;

        currentLevel.add(this);

        while (nodesInLevel > 0) {
            List<String> line = new ArrayList<>();
            nodesInLevel = 0;

            for (AbstractBinaryTree<E> node : currentLevel) {
                if (node == null) {
                    line.add(null);
                    nextLevel.add(null);
                    nextLevel.add(null);
                } else {
                    String nodeValue = String.valueOf(node.getKey());
                    line.add(nodeValue);
                    widestNode = Math.max(widestNode, nodeValue.length());

                    nextLevel.add(node.getLeft());
                    nextLevel.add(node.getRight());
                    if (node.getLeft() != null) nodesInLevel++;
                    if (node.getRight() != null) nodesInLevel++;
                }
            }

            lines.add(line);
            currentLevel = new ArrayList<>(nextLevel);
            nextLevel.clear();
        }

        widestNode += widestNode % 2;
        int perPiece = lines.get(lines.size() - 1).size() * (widestNode + String.valueOf(key).length());

        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int halfPieceWidth = (int) Math.floor(perPiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    char connector = ' ';
                    result.append(connector);

                    if (line.get(j) == null) {
                        result.append(" ".repeat(perPiece - 1));
                    } else {
                        result.append((" ").repeat(halfPieceWidth)).append(j % 2 == 0 ? "/" : "\\").append((" ").repeat(halfPieceWidth));
                    }
                }
                result.append("\n");
            }

            for (String nodeValue : line) {
                if (nodeValue == null) nodeValue = "";
                int leftPad = (int) Math.ceil((perPiece - nodeValue.length()) / 2.0);
                int rightPad = (int) Math.floor((perPiece - nodeValue.length()) / 2.0);

                result.append(" ".repeat(leftPad)).append(nodeValue).append(" ".repeat(rightPad));
            }
            result.append("\n");

            perPiece /= 2;
        }
        return result.toString();
    }
}
