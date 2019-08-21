package com.jeffery.bricks.list;

import java.util.Iterator;

/**
 * 线性表
 *
 * @param <T>
 */
public interface List<T> extends Iterable<T> {

    void add(T item);

    void insert(int index, T item);

    void set(int index, T item);

    T get(int index);

    T delete(int index);

    int size();

    boolean isEmpty();

    void clear();

    default String list2String() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Iterator<T> iterator = this.iterator(); iterator.hasNext(); ) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
