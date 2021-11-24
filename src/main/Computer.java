package main;

import java.util.Stack;

public class Computer {

    public final Stack<String>[] stacks;
    public Stack<String> mainStack;
    private static final int DEFAULT_STACKS = 42;

    public Computer() {
        this(DEFAULT_STACKS);
    }

    public Computer(int totalStacks) {
        stacks = new Stack[totalStacks];
        init();
    }

    private void init() {
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new Stack<>();
        }
        mainStack = stacks[0];
    }

    public String execute(String program) {
        String[] tokens = parse(program);

        StringBuilder out = new StringBuilder();
        for (String token : tokens) {
            String result = executeNext(token);
            if (result != null) {
                out.append(result).append("\n");
            }
        }
        return out.toString();
    }

    public void clearState() {
        init();
    }

    public String executeNext(String token) {
        switch (token) {
            case "echo": {
                if (mainStack.isEmpty()) {
                    return "";
                } else {
                    return mainStack.peek();
                }
            }
            case "pop": {
                return mainStack.pop();
            }
            case "dup": {
                mainStack.push(mainStack.peek());
                return null;
            }
            case "dup2": {
                String x = mainStack.pop();
                String y = mainStack.pop();
                mainStack.push(y);
                mainStack.push(x);
                mainStack.push(y);
                mainStack.push(x);
                return null;
            }
            case "swap": {
                String x = mainStack.pop();
                String y = mainStack.pop();
                mainStack.push(x);
                mainStack.push(y);
                return null;
            }
            case "char": {
                push(0, (char)popInteger());
                return null;
            }
            case "even": {
                boolean isEven = popInteger() % 2 == 0;
                push(0, isEven ? 1 : 0);
                return null;
            }
            case "odd": {
                boolean isOdd = popInteger() % 2 != 0;
                push(0, isOdd ? 1 : 0);
                return null;
            }
            case "!": {
                push(0, factorial(popInteger()));
                return null;
            }
            case "len": {
                push(0, mainStack.pop().length());
                return null;
            }
            default: {
                mainStack.push(token);
                return null;
            }
        }
    }

    private void push(int stackIndex, int value) {
        stacks[stackIndex].push("" + value);
    }

    private void push(int stackIndex, String value) {
        stacks[stackIndex].push(value);
    }

    private int popInteger() {
        return Integer.parseInt(mainStack.pop());
    }

    private int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static String[] parse(String input) {
        return input.split("[ ]+");
    }
}
