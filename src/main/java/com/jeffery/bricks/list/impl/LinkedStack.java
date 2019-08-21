package com.jeffery.bricks.list.impl;

import com.jeffery.bricks.list.Stack;

import java.util.EmptyStackException;

/**
 * 链表实现的栈
 *
 * @param <T>
 */
public class LinkedStack<T> implements Stack<T> {

    private Node<T> top;
    private final int capacity;
    private int size;

    public LinkedStack(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }

    @Override
    public void push(T item) {
        if (size == capacity) {
            throw new StackOverflowError();
        }
        top = new Node<>(item, top);
        ++size;
    }

    @Override
    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T v = top.item;
        top = top.next;
        --size;
        return v;
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return top.item;
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
        top.next = null;
        top.item = null;
        top = null;
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
