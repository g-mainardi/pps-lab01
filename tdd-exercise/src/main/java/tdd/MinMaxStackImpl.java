package tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {

    private final Stack<Integer> stack;
    private final List<Integer> orderedStack;

    public MinMaxStackImpl() {
        this.stack = new Stack<>();
        this.orderedStack = new ArrayList<>();
    }

    @Override
    public void push(final int value) {
        this.stack.push(value);
        this.insertInOrder(value);
    }

    private void insertInOrder(final int value) {
        int index;
        for(index=0;index<this.orderedStack.size();index++){
            if(value < this.orderedStack.get(index)){
                break;
            }
        }
        this.orderedStack.add(index, value);
    }

    @Override
    public int pop() {
        this.checkEmptyStack();
        return this.stack.pop();
    }

    @Override
    public int peek() {
        this.checkEmptyStack();
        return this.stack.get(this.size() - 1);
    }

    @Override
    public int getMin() {
        this.checkEmptyStack();
        return this.orderedStack.get(0);
    }

    private void checkEmptyStack() {
        if(this.isEmpty()){
            throw new IllegalStateException("Empty stack");
        }
    }

    @Override
    public int getMax() {
        this.checkEmptyStack();
        return this.orderedStack.get(this.orderedStack.size()-1);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }
}
