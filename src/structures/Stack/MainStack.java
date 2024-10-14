package structures.Stack;

public class MainStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(5);

        System.out.println("=== ЗАПОЛНЕНИЕ СТЕКА ===");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);

        System.out.println("=== RESIZING ===");
        stack.push(6);
        System.out.println(stack);

        System.out.println("=== МЕТОДЫ СТЕКА ===");
        System.out.println("Pop #1: " + stack.pop());
        System.out.println("Pop #2: " + stack.pop());
        System.out.println("Updated stack: " + stack);
        System.out.println("Peek: " +stack.peek());

        System.out.println("=== ИТЕРАТОР ===");
        for (int i : stack) {
            System.out.println(i);
        }
    }
}