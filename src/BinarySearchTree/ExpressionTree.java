package BinarySearchTree;


import structures.BinaryTree.AbstractBinaryTree;
import structures.BinaryTree.BinaryTree;

import java.util.Stack;

/**
 * Реализовать дерево выражения с операндами — числами и написать метод вычисления значения
 * арифметического выражения. По бесскобочной префиксной записи построить дерево выражения
 * и вычислить его значение;
 * операторы - узлы дерева
 * операнды - листья дерева
 * 3 * (5*2) == * 3 + 5 2
 * Идем с конца
 * 2 5 + 3 *
 * 2 5 листья , + узел, 3 лист, * узел
 */

public class ExpressionTree<E> extends BinaryTree<E> {

    public ExpressionTree(E key, AbstractBinaryTree<E> leftChild, AbstractBinaryTree<E> rightChild) {
        super(key, leftChild, rightChild);
    }


    public double evaluate() {
        if (this.getLeft() == null && this.getRight() == null) {
            return Double.parseDouble(this.getKey().toString());
        }
        double leftValue = ((ExpressionTree<E>) this.getLeft()).evaluate();
        double rightValue = ((ExpressionTree<E>) this.getRight()).evaluate();

        switch (this.getKey().toString()) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Неизвестный оператор");
        }
    }


    public static AbstractBinaryTree<String> buildExpressionTree(String[] tokens) {
        Stack<AbstractBinaryTree<String>> stack = new Stack<>();
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (isOperator(token)) {
                AbstractBinaryTree<String> left = stack.pop();
                AbstractBinaryTree<String> right = stack.pop();
                stack.push(new ExpressionTree<>(token, left, right));
            } else {
                stack.push(new ExpressionTree<>(token, null, null));
            }
        }
        return stack.pop();
    }

    private static boolean isOperator(String symbol) {
        return symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/");
    }

    public static void main(String[] args) { // - 5 * 6 7 == - 37 == 5 - (6 * 7)
        String[] expression = {"-", "5", "*", "6", "7"};
        AbstractBinaryTree<String> tree = buildExpressionTree(expression);
        System.out.println(tree.print());
        System.out.println("Результат: " + ((ExpressionTree<String>) tree).evaluate());

    }
}