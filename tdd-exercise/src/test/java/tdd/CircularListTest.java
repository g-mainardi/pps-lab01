package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    public static final int VALUE_1 = 500;
    public static final int VALUE_2 = 4377;
    public static final int QUEUE_CAPACITY = 5;
    private CircularQueue queue;

    @BeforeEach
    public void init() {
        queue = new CircularQueueImpl(QUEUE_CAPACITY);
    }

    @Test
    public void testIsEmptyAndSize() {
        int size = 0;
        assertTrue(queue.isEmpty());
        assertEquals(size, queue.size());
        queue.add(VALUE_1);
        size++;
        assertEquals(size, queue.size());
    }

    @Test
    public void testInsertAndRemove() {
        queue.add(VALUE_1);
        queue.add(VALUE_2);
        assertEquals(VALUE_1, queue.remove());
        assertEquals(VALUE_2, queue.getHead());
    }

    @Test
    public void testOverCapacityInsertion() {
        fillQueue(VALUE_1);
        assertEquals(VALUE_1, queue.getHead());
        fillQueue(VALUE_2);
        assertEquals(VALUE_2, queue.getHead());
    }

    private void fillQueue(final int value) {
        for(int i=0; i<QUEUE_CAPACITY; i++) {
            queue.add(value);
        }
    }
}
