package com.jeffery.bricks.list.impl;

import com.jeffery.bricks.list.List;

import java.util.Iterator;
import java.util.Objects;

/**
 * 链表实现的线性表
 *
 * @param <T>
 */
public class LinkedList<T> implements List<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    @Override
    public void add(T item) {
        insert(size, item);
    }

    @Override
    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0) {
            first = last = new Node<>(null, item, null);
        } else {
            if (index == 0) {
                first = new Node<>(null, item, first);
                first.next.prev = first;
            } else if (index == size) {
                last = new Node<>(last, item, null);
                last.prev.next = last;
            } else {
                Node<T> node = getNode(index);
                Node<T> newNode = new Node<>(node.prev, item, node);
                newNode.prev.next = newNode;
                node.prev = newNode;
            }
        }
        ++size;
    }

    @Override
    public void set(int index, T item) {
        Node<T> node = getNode(index);
        Objects.requireNonNull(node);
        node.item = item;
    }

    @Override
    public T get(int index) {
        Node<T> node = getNode(index);
        Objects.requireNonNull(node);
        return node.item;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index <= size / 2) {
            Node<T> p = first;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p;
        } else {
            Node<T> p = last;
            for (int i = 0; i < (size - index - 1); i++) {
                p = p.prev;
            }
            return p;
        }
    }

    @Override
    public T delete(int index) {
        Node<T> node = getNode(index);
        Node<T> prev = node.prev;
        Node<T> next = node.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
        --size;
        return node.item;
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
        last.prev = null;
        first.item = null;
        last.item = null;
        first = null;
        last = null;
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
        Node<T> p = first;

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public T next() {
            T v = p.item;
            p = p.next;
            return v;
        }
    }

    private static class Node<T> {
        Node<T> prev;
        T item;
        Node<T> next;

        Node(Node<T> prev, T item, Node<T> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

}
