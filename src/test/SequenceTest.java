package test;

import main.util.Sequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceTest {

    Sequence<Integer> sequence;

    @BeforeEach
    void setUp() {
        sequence = new Sequence<>();
    }

    @Test
    void getAndGet() {
        sequence.set(0, 1);
        assertEquals(1, sequence.get(0));
    }

    @Test
    void insert() {
        sequence = new Sequence<>(2);
        assertEquals(2, sequence.size());
        sequence.insert(16, 1);
        assertEquals(32, sequence.size());
        assertEquals(1, sequence.get(16));
    }

}