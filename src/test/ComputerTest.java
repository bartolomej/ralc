package test;

import main.Computer;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    @org.junit.jupiter.api.Test
    void parse() {
        String[] actual = Computer.parse("1 2  echo ");
        String[] expected = new String[]{"1", "2", "echo"};
        assertArrayEquals(expected,  actual);
    }

    @org.junit.jupiter.api.Test
    void executeNext() {
        Computer computer = new Computer();
        computer.executeNext("2");
        assertEquals("2", computer.executeNext("echo"));
    }

    @org.junit.jupiter.api.Test
    void execute() {
        Computer computer = new Computer();
        assertEquals("2", computer.execute("1 2  echo "));
    }

    @org.junit.jupiter.api.Test
    void executeDup2() {
        Computer computer = new Computer();
        computer.execute("1 2 dup2");
        String[] actual = computer.mainStack.toArray(new String[4]);
        String[] expected = new String[]{"1", "2", "1", "2"};
        assertArrayEquals(expected, actual);
        assertEquals(computer.mainStack.size(), 4);
    }

    @org.junit.jupiter.api.Test
    void condition() {
        Computer computer = new Computer();
        assertEquals("Hello\n", computer.execute("Hello 1 2 == else ?echo"));
        assertEquals("Hello\n", computer.execute("Hello 1 1 == then ?echo"));
    }
}