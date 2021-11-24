package main;

import java.util.Stack;

public class Computer {

    public final Stack<String>[] stacks;
    public Stack<String> mainStack;
    private static final int DEFAULT_STACKS = 42;
    private boolean conditionState;

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
        conditionState = false;
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
        clearState();
        return out.toString();
    }

    public void clearState() {
        init();
    }

    public String executeNext(String token) {
        // commands with "?" prefix only execute if conditionState=true
        if (token.startsWith("?")) {
            if (conditionState) {
                token = token.replaceAll("\\?", "");
            } else {
                return null;
            }
        }
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
                push(0, (char) popInteger());
                return null;
            }
            case "even": {
                push(0, popInteger() % 2 == 0);
                return null;
            }
            case "odd": {
                push(0, popInteger() % 2 != 0);
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
            case "<>": {
                int a = popInteger();
                int b = popInteger();
                push(0, a != b);
                return null;
            }
            case "==": {
                int a = popInteger();
                int b = popInteger();
                push(0, a == b);
                return null;
            }
            case "<": {
                int a = popInteger();
                int b = popInteger();
                push(0, a < b);
                return null;
            }
            case "<=": {
                int a = popInteger();
                int b = popInteger();
                push(0, a <= b);
                return null;
            }
            case ">": {
                int a = popInteger();
                int b = popInteger();
                push(0, a > b);
                return null;
            }
            case ">=": {
                int a = popInteger();
                int b = popInteger();
                push(0, a >= b);
                return null;
            }
            case "+": {
                int a = popInteger();
                int b = popInteger();
                push(0, a + b);
                return null;
            }
            case "-": {
                int a = popInteger();
                int b = popInteger();
                push(0, a - b);
                return null;
            }
            case "*": {
                int a = popInteger();
                int b = popInteger();
                push(0, a * b);
                return null;
            }
            case "/": {
                int a = popInteger();
                int b = popInteger();
                push(0, a / b);
                return null;
            }
            case "%": {
                int a = popInteger();
                int b = popInteger();
                push(0, b % a);
                return null;
            }
            case ".": {
                push(0, mainStack.pop() + mainStack.pop());
                return null;
            }
            case "rnd": {
                int a = popInteger();
                int b = popInteger();
                // random number is >= a and <= b
                push(0, random(b, a));
                return null;
            }
            case "then": {
                int x = popInteger();
                conditionState = x != 0;
                return null;
            }
            case "else": {
                int x = popInteger();
                conditionState = x == 0;
                return null;
            }
            default: {
                mainStack.push(token);
                return null;
            }
        }
    }

    private void push(int stackIndex, boolean value) {
        stacks[stackIndex].push(value ? "1" : "0");
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

    private int random(int max, int min) {
        return (int) (Math.random() * (max - min + 1) + min);
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
