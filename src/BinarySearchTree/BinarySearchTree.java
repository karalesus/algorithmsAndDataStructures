package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {

    private Node<E> root;

    public BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    public BinarySearchTree() {
    }

    @Override
    public void insert(E value) {
        root = insertRecursive(value, root);
        balance();
    }

    public Node<E> insertRecursive(E value, Node<E> node) {
        if (node == null) {
            return new Node<E>(value);
        }
        if (value.compareTo(node.value) < 0) {
            node.leftChild = insertRecursive(value, node.leftChild);
        }
        if (value.compareTo(node.value) > 0) {
            node.rightChild = insertRecursive(value, node.rightChild);
        }
        return node;
    }

    private Node<E> buildBalancedTree(List<E> elements, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        Node<E> node = new Node<>(elements.get(mid));

        node.leftChild = buildBalancedTree(elements, start, mid - 1);
        node.rightChild = buildBalancedTree(elements, mid + 1, end);

        return node;
    }

    public void balance() {
        List<E> elements = new ArrayList<>();
        inOrderTraversal(root, elements);
        root = buildBalancedTree(elements, 0, elements.size() - 1);
    }


    @Override
    public boolean contains(E element) {
        return (search(element) != null);
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        Node<E> currentNode = searchRecursive(root, element);
        if (currentNode == null) {
            return null;
        }
        BinarySearchTree<E> tree = new BinarySearchTree<E>();
        tree.root = currentNode;
        return tree;
    }

    public Node<E> searchRecursive(Node<E> root, E element) {
        if (root == null || root.value.equals(element)) {
            return root;
        } else if (element.compareTo(root.value) < 0) {
            return searchRecursive(root.leftChild, element);
        } else return searchRecursive(root.rightChild, element);
    }

    @Override
    public Node<E> getRoot() {
        return root;
    }

    @Override
    public Node<E> getLeft() {
        if (root != null && root.leftChild != null) {
            return root.leftChild;
        } else {
            throw new NullPointerException("Левого поддерева не найдено");
        }
    }

    @Override
    public Node<E> getRight() {
        if (root != null && root.rightChild != null) {
            return root.rightChild;
        } else {
            throw new NullPointerException("Левого поддерева не найдено");
        }
    }

    @Override
    public E getValue() {
        if (root != null) {
            return root.value;
        } else {
            throw new NullPointerException("Значение узла равно null");
        }
    }

    private void inOrderTraversal(Node<E> node, List<E> elements) {
        if (node == null) return;
        inOrderTraversal(node.leftChild, elements);
        elements.add(node.value);
        inOrderTraversal(node.rightChild, elements);
    }

    @Override
    public String toString() {
        if (root != null) {
            System.out.println(print());
        } else {
            throw new NullPointerException("Дерево пустое");
        }
        return "";
    }

    @Override
    public String print() {
        if (root == null) return "";

        StringBuilder result = new StringBuilder();
        List<List<String>> lines = new ArrayList<>();
        List<Node<E>> currentLevel = new ArrayList<>();
        List<Node<E>> nextLevel = new ArrayList<>();
        int widestNode = 0;
        int nodesInLevel = 1;

        currentLevel.add(root);

        while (nodesInLevel > 0) {
            List<String> line = new ArrayList<>();
            nodesInLevel = 0;

            for (Node<E> node : currentLevel) {
                if (node == null) {
                    line.add(null);
                    nextLevel.add(null);
                    nextLevel.add(null);
                } else {
                    String nodeValue = String.valueOf(node.value);
                    line.add(nodeValue);
                    widestNode = Math.max(widestNode, nodeValue.length());

                    nextLevel.add(node.leftChild);
                    nextLevel.add(node.rightChild);
                    if (node.leftChild != null) nodesInLevel++;
                    if (node.rightChild != null) nodesInLevel++;
                }
            }

            lines.add(line);
            currentLevel = new ArrayList<>(nextLevel);
            nextLevel.clear();
        }

        widestNode += widestNode % 2;
        int perPiece = lines.get(lines.size() - 1).size() * (widestNode + String.valueOf(root.value).length());

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
                        result.append((" ").repeat(halfPieceWidth))
                                .append(j % 2 == 0 ? "/" : "\\")
                                .append((" ").repeat(halfPieceWidth));
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

