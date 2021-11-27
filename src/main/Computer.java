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
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new Stack<>();
        }
        mainStack = stacks[0];
    }

    public String execute(String program) throws Exception {
        String[] tokens = parse(program);

        StringBuilder out = new StringBuilder();
        int moveCount = 0;
        Stack<String> targetStack = null;
        for (String token : tokens) {
            // add a predefined number of next tokens
            // to a specified stack if encountering "fun" token
            if (token.equals("fun")) {
                int targetStackIndex = popInteger();
                moveCount = popInteger();
                targetStack = stacks[targetStackIndex];
            }
            else if (moveCount > 0 && targetStack != null) {
                targetStack.add(token);
                moveCount--;
            }
            else {
                 String result = executeNext(token);
                if (result != null) {
                    out.append(result).append("\n");
                }
            }
        }
        return out.toString();
    }

    public void clearState() {
        conditionState = false; // reset condition state
        for (Stack<String> stack : stacks) {
            stack.clear();
        }
    }

    public String executeNext(String token) throws Exception {
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
                mainStack.pop();
                return null;
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
                push(0, "" + (char) popInteger());
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
                push(0, Utils.factorial(popInteger()));
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
                push(0, b - a);
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
                push(0, Utils.random(b, a));
                return null;
            }
            case "then": {
                int x = popInteger();
                conditionState = x != 0;
                return null;
            }
            case "else": {
                conditionState = !conditionState;
                return null;
            }
            case "print": {
                if (mainStack.isEmpty()) {
                    return "";
                } else {
                    int index = popInteger();
                    return print(index);
                }
            }
            case "clear": {
                int index = popInteger();
                clear(index);
                return null;
            }
            case "move": {
                int targetStackIndex = popInteger();
                int moveCount = popInteger();
                moveToStack(targetStackIndex, moveCount);
                return null;
            }
            case "reverse": {
                int targetStackIndex = popInteger();
                reverseStack(targetStackIndex);
                return null;
            }
            case "run": {
                int targetStackIndex = popInteger();
                run(targetStackIndex);
                return null;
            }
            case "loop": {
                int targetStackIndex = popInteger();
                int repetitions = popInteger();
                loop(targetStackIndex, repetitions);
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

    private void loop(int targetStackIndex, int repetitions) throws Exception {
        for (int i = 0; i < repetitions; i++) {
            run(targetStackIndex);
        }
    }

    private void run(int targetStackIndex) throws Exception {
        Stack<String> targetStack = stacks[targetStackIndex];
        Stack<String> reversedStack = reverseStack(targetStack);
        while (!reversedStack.isEmpty()) {
            String token = reversedStack.pop();
            executeNext(token);
        }
    }

    private String print(int stackIndex) throws Exception {
        checkStackIndex(stackIndex);
        Stack<String> targetStack = stacks[stackIndex];
        Stack<String> tempStack = new Stack<>();
        tempStack.addAll(targetStack);
        String[] out = new String[tempStack.size()];
        for (int i = 0; i < out.length; i++) {
            out[out.length - i - 1] = tempStack.pop();
        }
        return Utils.join(out, " ");
    }

    private void clear(int stackIndex) throws Exception {
        checkStackIndex(stackIndex);
        stacks[stackIndex].clear();
    }

    private void moveToStack(int stackIndex, int count) throws Exception {
        checkStackIndex(stackIndex);
        for (int i = 0; i < count; i++) {
            String topElement = mainStack.pop();
            stacks[stackIndex].add(topElement);
        }
    }

    private void reverseStack(int stackIndex) throws Exception {
        checkStackIndex(stackIndex);
        Stack<String> tempStack = new Stack<>();
        Stack<String> targetStack = stacks[stackIndex];
        while (!targetStack.isEmpty()) {
            tempStack.push(targetStack.pop());
        }
        targetStack.addAll(tempStack);
    }

    private Stack<String> reverseStack(Stack<String> stack) {
        Stack<String> tempStack = new Stack<>();
        tempStack.addAll(stack);
        Stack<String> resultStack = new Stack<>();
        while (!tempStack.isEmpty()) {
            resultStack.push(tempStack.pop());
        }
        return resultStack;
    }

    private void checkStackIndex(int stackIndex) throws Exception {
        if (stackIndex >= stacks.length) {
            throw new Exception("Invalid stack index");
        }
    }

    public static String[] parse(String input) {
        return input.split("[ ]+");
    }
}
