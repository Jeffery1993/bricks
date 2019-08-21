package com.jeffery.bricks.list.impl;

import com.jeffery.bricks.list.Stack;

import java.util.EmptyStackException;

/**
 * 数组实现的栈
 *
 * @param <T>
 */
public class ArrayStack<T> implements Stack<T> {

    private final Object[] value;
    private int size;

    public ArrayStack(int capacity) {
        if (capacity > 0) {
            value = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }

    @Override
    public void push(T item) {
        if (size == value.length) {
            throw new StackOverflowError();
        }
        value[size++] = item;
    }

    @Override
    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T v = (T) value[--size];
        value[size] = null;
        return v;
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return (T) value[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            value[i] = null;
        }
        size = 0;
    }

}
