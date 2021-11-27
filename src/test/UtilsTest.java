package test;

import main.Help;
import main.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void join() {
        String[] array = new String[]{"1", "2", "3"};
        assertEquals("1,2,3", Utils.join(array, ","));
    }

    @Test
    void help() {
        Help.printHelp();
    }
}