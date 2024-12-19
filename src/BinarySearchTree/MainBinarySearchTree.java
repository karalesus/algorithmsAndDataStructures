package BinarySearchTree;

public class MainBinarySearchTree {
    public static void main(String[] args) {
        AbstractBinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        System.out.println("Заполненное дерево поиска:");
        tree.insert(6);
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(5);
        tree.insert(9);
        tree.insert(7);
        tree.insert(0);
        tree.insert(11);

        System.out.println(tree);
        System.out.println("\nДерево с заданным значением элемента в качестве корневого:");
        System.out.println(tree.search(1));
        System.out.println("Содержится ли в дереве число 10? " + tree.contains(10));
        System.out.println("Содержится ли в дереве число 3? " + tree.contains(3));
    }
}
