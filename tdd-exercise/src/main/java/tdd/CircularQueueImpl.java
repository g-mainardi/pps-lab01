package tdd;

import java.util.*;

public class CircularQueueImpl implements CircularQueue {

    private final Queue<Integer> queue;
    private final int capacity;

    public CircularQueueImpl(final int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public void add(int value) {
        if (this.isFullCapacity()){
            this.remove();
        }
        this.queue.add(value);
    }

    private boolean isFullCapacity() {
        return this.size() == this.getCapacity();
    }

    @Override
    public int remove() {
        checkIsEmpty();
        return this.queue.remove();
    }

    @Override
    public int getHead() {
        checkIsEmpty();
        return this.queue.element();
    }

    private void checkIsEmpty() {
        if(this.isEmpty()){
            throw new IllegalStateException("Empty queue");
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return this.queue.size();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }
}
