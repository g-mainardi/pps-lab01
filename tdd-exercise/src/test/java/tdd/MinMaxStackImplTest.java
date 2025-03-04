package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    public static final int VALUE_1 = 341;
    public static final int VALUE_2 = 960;
    public static final int VALUE_3 = 122;
    public static final int VALUE_4 = 587;
    private static final int NUM_AUXILIAR_NUMBERS = 20;
    private MinMaxStack stack;

    @BeforeEach
    public void init() {
        this.stack = new MinMaxStackImpl();
    }

    @Test
    public void testInitiallyEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPushAndPeek() {
        stack.push(VALUE_1);
        stack.push(VALUE_2);
        assertEquals(VALUE_2, stack.peek());
    }

    @Test
    public void testPushAndPop() {
        stack.push(VALUE_1);
        for (int i = 0; i< NUM_AUXILIAR_NUMBERS; i++) {
            stack.push(VALUE_2);
            assertEquals(VALUE_2, stack.pop());
        }
        assertEquals(VALUE_1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testSize() {
        int stackSize = 0;
        stack.push(VALUE_1);
        stackSize++;
        for (int i = 0; i< NUM_AUXILIAR_NUMBERS; i++) {
            stack.push(VALUE_2);
        }
        stackSize += NUM_AUXILIAR_NUMBERS;
        assertEquals(stackSize, stack.size());
        stack.pop();
        stackSize--;
        assertEquals(stackSize, stack.size());
    }

    @Test
    public void testMinMax() {
        stack.push(VALUE_1);
        stack.push(VALUE_2);
        stack.push(VALUE_3);
        stack.push(VALUE_4);
        assertEquals(VALUE_3, stack.getMin());
        assertEquals(VALUE_2, stack.getMax());
    }
}