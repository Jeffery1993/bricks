package com.jeffery.bricks.tree;

import com.jeffery.bricks.tree.impl.AVLTree;
import com.jeffery.bricks.tree.impl.BSTTree;
import org.junit.Assert;
import org.junit.Test;

public class TreeTest {

    @Test
    public void test() {
        test(new BSTTree<>());
        test(new AVLTree<>());
    }

    public void test(BinaryTree<Integer, String> tree) {
        final int size = 15;
        for (int i = 1; i <= size; i++) {
            tree.put(i, String.valueOf(i));
        }
        Assert.assertEquals(size, tree.size());
        for (int i = 1; i <= size; i++) {
            Assert.assertEquals(String.valueOf(i), tree.get(i));
        }
        System.out.println("Height=" + tree.height());
        System.out.print("前序遍历：");
        tree.preOrderTraverse((k, v) -> System.out.print(k + "=" + v + " "));
        System.out.print("\n中序遍历：");
        tree.inOrderTraverse((k, v) -> System.out.print(k + "=" + v + " "));
        System.out.print("\n后序遍历：");
        tree.postOrderTraverse((k, v) -> System.out.print(k + "=" + v + " "));
        System.out.print("\n层序遍历：");
        tree.levelOrderTraverse((k, v) -> System.out.print(k + "=" + v + " "));
        System.out.print("\n");
    }

}
