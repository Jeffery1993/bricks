package com.jeffery.bricks.tree.impl;

/**
 * AVL-平衡二叉树实现
 *
 * @param <K>
 * @param <V>
 */
public class AVLTree<K extends Comparable<K>, V> extends BSTTree<K, V> {

    @Override
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
            if (height(node.left) - height(node.right) == 2) {
                if (key.compareTo(node.left.key) < 0) {
                    node = llRotate(node);
                } else {
                    node = lrRotate(node);
                }
            }
        } else {
            node.right = put(node.right, key, value);
            if (height(node.left) - height(node.right) == -2) {
                if (key.compareTo(node.right.key) > 0) {
                    node = rrRotate(node);
                } else {
                    node = rlRotate(node);
                }
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private BSTNode llRotate(BSTNode node) {
        BSTNode x = node.left;
        node.left = x.right;
        x.right = node;

        node.height = Math.max(this.height(node.right), this.height(node.left)) + 1;
        x.height = Math.max(this.height(x.left), node.height) + 1;
        return x;
    }

    private BSTNode rrRotate(BSTNode node) {
        BSTNode x = node.right;
        node.right = x.left;
        x.left = node;

        node.height = Math.max(this.height(node.right), this.height(node.left)) + 1;
        x.height = Math.max(this.height(x.right), node.height) + 1;
        return x;
    }

    private BSTNode lrRotate(BSTNode node) {
        node.left = rrRotate(node.left);
        return llRotate(node);
    }

    private BSTNode rlRotate(BSTNode node) {
        node.right = llRotate(node.right);
        return rrRotate(node);
    }

}
