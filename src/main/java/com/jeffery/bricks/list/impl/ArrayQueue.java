package com.jeffery.bricks.list.impl;

import com.jeffery.bricks.list.Queue;

/**
 * 数组实现的队列
 *
 * @param <T>
 */
public class ArrayQueue<T> implements Queue<T> {

    private final Object[] value;
    private int putIndex;
    private int takeIndex;
    private int size;

    public ArrayQueue(int capacity) {
        if (capacity > 0) {
            value = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }

    @Override
    public void enqueue(T item) {
        if (size == value.length) {
            throw new IllegalStateException("Queue is full!");
        }
        value[putIndex++] = item;
        if (putIndex == value.length) {
            putIndex = 0;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty!");
        }
        T v = (T) value[takeIndex];
        value[takeIndex] = null;
        if (++takeIndex == value.length) {
            takeIndex = 0;
        }
        size--;
        return v;
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
        for (int i = 0; i < value.length; i++) {
            value[i] = null;
        }
        size = 0;
    }

}
