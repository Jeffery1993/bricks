package com.jeffery.bricks.tree;

/**
 * 树
 *
 * @param <K>
 * @param <V>
 */
public interface Tree<K, V> {

    Node<K, V> root();

    V get(K key);

    void put(K key, V value);

    int size();

    int height();

    boolean isEmpty();

    void clear();

    /**
     * 树节点
     *
     * @param <K>
     * @param <V>
     */
    interface Node<K, V> {
        K getKey();

        V getValue();
    }

}
