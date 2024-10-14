package structures.Stack;

public class TestStack {
    public static void main(String[] args) {
        String expression = "{[](())}";
        if (isExpressionTrue(expression)) {
            System.out.println("все ок!");
        } else {
            System.out.println("скобки расставлены неправильно");
        }
    }

    public static boolean isExpressionTrue(String expression) {
        Stack<Character> stack = new Stack<>(expression.length());
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (!stack.isEmpty() && ((stack.peek() == '(' && c == ')')
                    || (stack.peek() == '{' || c == '}')
                    || (stack.peek() == '[' || c == ']'))) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
