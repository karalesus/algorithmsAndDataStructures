package structures.BinaryTree;

import java.util.List;

public class MainBinaryTree {
    public static void main(String[] args) {
        AbstractBinaryTree<Integer> tree = new BinaryTree<>(1);
        AbstractBinaryTree<Integer> tree1 = new BinaryTree<>(3);
        AbstractBinaryTree<Integer> tree2 = new BinaryTree<>(2, tree, tree1);

        AbstractBinaryTree<Integer> tree3 = new BinaryTree<>(5);
        AbstractBinaryTree<Integer> tree4 = new BinaryTree<>(7);
        AbstractBinaryTree<Integer> tree5 = new BinaryTree<>(6, tree3, tree4);

        AbstractBinaryTree<Integer> mainTree = new BinaryTree<>(4, tree2, tree5);
        System.out.println("Вывод дерева в виде строки, где каждый внутренний уровень идентифицируется двумя пробелами в качестве отступа");
        System.out.println(mainTree.asIndentedPreOrder(0));

        System.out.println("Обход preOrder (вершина, левое поддерево, правое поддерево): ");
        List<AbstractBinaryTree<Integer>> preOrderList = mainTree.preOrder();
        for (AbstractBinaryTree<Integer> node : preOrderList) {
            System.out.print(node.getKey() + " ");
        }

        System.out.println("\nОбход postOrder (левое поддерево, правое поддерево, вершина): ");
        List<AbstractBinaryTree<Integer>> postOrderList = mainTree.postOrder();
        for (AbstractBinaryTree<Integer> node : postOrderList) {
            System.out.print(node.getKey() + " ");
        }

        System.out.println("\nОбход inOrder (левое поддерево, вершина, правое поддерево): ");
        mainTree.forEachInOrder(e -> System.out.print(e + " "));

        System.out.println("\nПоиск в ширину: ");
        mainTree.breadthFirstSearch();

        System.out.println("\nПоиск в глубину: ");
        mainTree.depthFirstSearch();

        System.out.println("\nКрасивый вывод");
        System.out.println(mainTree);
    }
}
