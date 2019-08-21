package com.jeffery.bricks.list;

/**
 * 队列
 *
 * @param <T>
 */
public interface Queue<T> {

    void enqueue(T item);

    T dequeue();

    int size();

    boolean isEmpty();

    void clear();

}
