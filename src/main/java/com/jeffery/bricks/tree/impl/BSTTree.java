package com.jeffery.bricks.tree.impl;

import com.jeffery.bricks.tree.BinaryTree;

/**
 * 普通二叉搜索树实现
 *
 * @param <K>
 * @param <V>
 */
public class BSTTree<K extends Comparable<K>, V> implements BinaryTree<K, V> {

    protected BSTNode<K, V> root;
    protected int size;

    @Override
    public BiNode<K, V> root() {
        return root;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    protected BSTNode<K, V> put(BSTNode<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new BSTNode<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    protected final int height(BSTNode node) {
        return node == null ? 0 : node.height;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    static final class BSTNode<K extends Comparable<K>, V> implements BiNode<K, V> {
        K key;
        V value;
        int height;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public BiNode<K, V> leftChild() {
            return left;
        }

        @Override
        public BiNode<K, V> rightChild() {
            return right;
        }
    }

}
