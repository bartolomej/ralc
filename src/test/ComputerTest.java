package test;

import main.Computer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    @Test
    void parse() {
        String[] actual = Computer.parse("1 2  echo ");
        String[] expected = new String[]{"1", "2", "echo"};
        assertArrayEquals(expected,  actual);
    }

    @Test
    void executeNext() throws Exception {
        Computer computer = new Computer();
        computer.executeNext("2");
        assertEquals("2", computer.executeNext("echo"));
    }

    @Test
    void execute() throws Exception {
        Computer computer = new Computer();
        assertEquals("2\n", computer.execute("1 2  echo "));
    }

    @Test
    void executeDup2() throws Exception {
        Computer computer = new Computer();
        computer.execute("1 2 dup2");
        String[] actual = computer.mainStack.toArray(new String[4]);
        String[] expected = new String[]{"1", "2", "1", "2"};
        assertArrayEquals(expected, actual);
        assertEquals(computer.mainStack.size(), 4);
    }

    @Test
    void condition() throws Exception {
        Computer computer = new Computer();
        assertEquals("Hello\n", computer.execute("Hello 1 2 == else ?echo"));
        computer.clearState(); // clear state before the next program
        assertEquals("Hello\n", computer.execute("Hello 1 1 == then ?echo"));
    }

    @Test
    void funOperation() throws Exception {
        Computer computer = new Computer();
        // should move items 2,3,4 to stack 1 and print the last item: 1
        assertEquals("1\n", computer.execute("1 3 1 fun 2 3 4 echo"));
        assertEquals(3, computer.stacks[1].size());
    }

}