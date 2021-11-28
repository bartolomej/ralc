package test;

import main.util.CollectionException;
import main.util.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    Stack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void isEmpty() throws CollectionException {
        assertTrue(stack.isEmpty());
        stack.push("test");
        assertFalse(stack.isEmpty());
    }

    @Test
    void isFull() throws CollectionException {
        assertFalse(stack.isFull());
        for (int i = 0; i < Stack.DEFAULT_CAPACITY; i++) {
            stack.push("" + i);
        }
        assertTrue(stack.isFull());
    }

    @Test
    void size() throws CollectionException {
        assertEquals(0, stack.size());
        stack.push("");
        assertEquals(1, stack.size());
    }

    @Test
    void addAll() throws CollectionException {
        Stack<String> s = new Stack<>();
        s.push("a");
        s.push("b");
        s.push("c");
        stack.addAll(s);
        assertEquals(stack.size(), s.size());
        while (!stack.isEmpty()) {
            assertEquals(stack.pop(), s.pop());
        }
    }

    @Test
    void top() throws CollectionException {
        stack.push("a");
        assertEquals("a", stack.top());
        assertEquals(1, stack.size());
    }

    @Test
    void push() throws CollectionException {
        assertEquals(0, stack.size());
        stack.push("a");
        assertEquals(1, stack.size());
        assertEquals("a", stack.pop());
    }

    @Test
    void pop() throws CollectionException {
        stack.push("a");
        assertEquals(1, stack.size());
        assertEquals("a", stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    void toArray() throws CollectionException {
        stack.push("a");
        stack.push("b");
        String[] a = new String[]{"a", "b"};
        assertArrayEquals(a, stack.toArray(new String[2]));
    }
}