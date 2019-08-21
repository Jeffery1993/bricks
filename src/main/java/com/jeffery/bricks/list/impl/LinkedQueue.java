package com.jeffery.bricks.list.impl;

import com.jeffery.bricks.list.Queue;

/**
 * 链表实现的队列
 *
 * @param <T>
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> first;
    private final int capacity;
    private int size;

    public LinkedQueue(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }

    @Override
    public void enqueue(T item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full!");
        }
        if (first == null) {
            first = new Node<>(item, null);
        } else {
            Node<T> p = first;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new Node<>(item, null);
        }
        ++size;
    }

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty!");
        }
        T v = first.item;
        first = first.next;
        --size;
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
        first.next = null;
        first.item = null;
        first = null;
        size = 0;
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

}
