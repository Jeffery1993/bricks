package com.jeffery.bricks.list.impl;

import com.jeffery.bricks.list.List;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 数组实现的线性表
 *
 * @param <T>
 */
public class ArrayList<T> implements List<T> {

    private Object[] value;
    private int size;

    public ArrayList() {
        value = new Object[0];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity == 0) {
            value = new Object[0];
        } else if (initialCapacity > 0) {
            value = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
    }

    @Override
    public void add(T item) {
        insert(size, item);
    }

    @Override
    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(size + 1);
        if (size - index > 0) {
            System.arraycopy(value, index, value, index + 1, size - index);
        }
        value[index] = item;
        size++;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > value.length) {
            int newCapacity;
            if (value.length == 0) {
                newCapacity = 1 << 3;
            } else {
                newCapacity = value.length << 1;
            }
            if (newCapacity < 0) {
                throw new OutOfMemoryError();
            }
            value = Arrays.copyOf(value, newCapacity);
        }
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        value[index] = item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) value[index];
    }

    @Override
    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T v = (T) value[index];
        if (size - index - 1 > 0) {
            System.arraycopy(value, index + 1, value, index, size - index - 1);
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
        for (int i = 0; i < size; i++) {
            value[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        return list2String();
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            return (T) value[cursor++];
        }
    }

}
