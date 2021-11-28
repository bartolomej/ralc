package test;

import main.Computer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamplesTest {

    Computer computer;

    @BeforeEach
    void setUp() {
        computer = new Computer();
    }

    @Test
    void example1() throws Exception {
        assertEquals(
                "111 53 111 53 -100 3131 103 -12 0\n",
                computer.execute("0 -12 103 3131 -100 53 111 dup2 0 reverse 0 print")
        );
    }

    @Test
    void example2() throws Exception {
        assertEquals(
                "41192\n-150\n",
                computer.execute("41051 141 + echo -100 50 - echo")
        );
    }

    @Test
    void example3() throws Exception {
        assertEquals(
                "330\n",
                computer.execute("5 11 17 + + 10 * 0 print")
        );
    }

    @Test
    void example4() throws Exception {
        assertEquals(
                "120\n1\n",
                computer.execute("5 ! echo even 0 print")
        );
    }

    @Test
    void example5() throws Exception {
        // output example: 66\nB\n
        String[] out = computer.execute("70 90 rnd echo char echo").split("\n");
        int expectedCharCode = Integer.parseInt(out[0]);
        int actualCharCode = out[1].charAt(0);
        assertEquals(expectedCharCode, actualCharCode);
    }

    @Test
    void example6() throws Exception {
        assertEquals(
                "0 1 2\ndup 0 reverse swap\n4 3\n",
                computer.execute("0 1 2 3 4 4 1 fun dup 0 reverse swap 2 2 move 0 print 1 print 2 print")
        );
    }

    @Test
    void example7() throws Exception {
        assertEquals(
                "2 1 0 0\n2 2 1 0 0 0\n",
                computer.execute("0 1 2 3 1 fun 0 reverse dup 1 run 0 print 2 1 loop 0 print")
        );
    }

    @Test
    void example8() throws Exception {
        assertEquals(
                "8\n",
                computer.execute("5 3 1 2 5 1 fun == then ?dup2 else ?+ 1 run 0 print")
        );
    }

    @Test
    void example9() throws Exception {
        assertEquals(
                "24 10\n2\n",
                computer.execute("9 1 fun dup 0 reverse swap % dup then ?1 ?run 24 10 0 print 1 run pop echo")
        );
    }

    @Test
    void example10() throws Exception {
        String[] actual = computer.execute("3 1 fun 0 100 rnd 3 2 fun 5 1 loop  7 3 fun dup2 <= then ?pop else ?swap ?pop 3 4 fun 4 3 loop 1 print 2 print 3 print 4 print 2 run 0 print 4 run 0 print").split("\n");
        String[] expected = new String[]{
                "0 100 rnd",
                "5 1 loop",
                "dup2 <= then ?pop else ?swap ?pop",
                "4 3 loop"
        };
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
        // other output lines are based on random variables
    }

}
