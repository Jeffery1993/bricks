package com.jeffery.bricks.tree;

import com.jeffery.bricks.list.Queue;
import com.jeffery.bricks.list.impl.ArrayQueue;

import java.util.function.BiConsumer;

/**
 * 二叉树
 *
 * @param <K>
 * @param <V>
 */
public interface BinaryTree<K, V> extends Tree<K, V> {

    BiNode<K, V> root();

    default void preOrderTraverse(BiConsumer<? super K, ? super V> action) {
        final BiNode<K, V> root = root();
        if (root != null) {
            root.preOrderTraverse(action);
        }
    }

    default void inOrderTraverse(BiConsumer<? super K, ? super V> action) {
        final BiNode<K, V> root = root();
        if (root != null) {
            root.inOrderTraverse(action);
        }
    }

    default void postOrderTraverse(BiConsumer<? super K, ? super V> action) {
        final BiNode<K, V> root = root();
        if (root != null) {
            root.postOrderTraverse(action);
        }
    }

    default void levelOrderTraverse(BiConsumer<? super K, ? super V> action) {
        final BiNode<K, V> root = root();
        if (root != null) {
            Queue<BiNode> queue = new ArrayQueue<>(size() / 2 + 1);
            queue.enqueue(root());
            while (!queue.isEmpty()) {
                BiNode<K, V> node = queue.dequeue();
                if (node.leftChild() != null) {
                    queue.enqueue(node.leftChild());
                }
                if (node.rightChild() != null) {
                    queue.enqueue(node.rightChild());
                }
                action.accept(node.getKey(), node.getValue());
            }
        }
    }

    /**
     * 二叉树节点
     *
     * @param <K>
     * @param <V>
     */
    interface BiNode<K, V> extends Node<K, V> {
        BiNode<K, V> leftChild();

        BiNode<K, V> rightChild();

        default void preOrderTraverse(BiConsumer<? super K, ? super V> action) {
            action.accept(this.getKey(), this.getValue());
            final BiNode<K, V> leftChild = leftChild();
            if (leftChild != null) {
                leftChild.preOrderTraverse(action);
            }
            final BiNode<K, V> rightChild = rightChild();
            if (rightChild != null) {
                rightChild.preOrderTraverse(action);
            }
        }

        default void inOrderTraverse(BiConsumer<? super K, ? super V> action) {
            final BiNode<K, V> leftChild = leftChild();
            if (leftChild != null) {
                leftChild.inOrderTraverse(action);
            }
            action.accept(this.getKey(), this.getValue());
            final BiNode<K, V> rightChild = rightChild();
            if (rightChild != null) {
                rightChild.inOrderTraverse(action);
            }
        }

        default void postOrderTraverse(BiConsumer<? super K, ? super V> action) {
            final BiNode<K, V> leftChild = leftChild();
            if (leftChild != null) {
                leftChild.postOrderTraverse(action);
            }
            final BiNode<K, V> rightChild = rightChild();
            if (rightChild != null) {
                rightChild.postOrderTraverse(action);
            }
            action.accept(this.getKey(), this.getValue());
        }
    }

}
