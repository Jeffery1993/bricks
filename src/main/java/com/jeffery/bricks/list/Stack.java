package com.jeffery.bricks.list;

/**
 * 栈
 *
 * @param <T>
 */
public interface Stack<T> {

    void push(T item);

    T pop();

    T peek();

    int size();

    boolean isEmpty();

    void clear();

}
