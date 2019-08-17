package com.jeffery.bricks.sort;

import org.junit.Assert;
import org.junit.Test;

public class SortTest {

    @Test
    public void test() {
        test(new int[]{2, 1, 5, 3, 4}, new int[]{1, 2, 3, 4, 5}, new InsertionSort());
        test(new int[]{2, 1, 5, 3, 4}, new int[]{1, 2, 3, 4, 5}, new BubbleSort());
        test(new int[]{2, 1, 5, 3, 4}, new int[]{1, 2, 3, 4, 5}, new QuickSort());
        test(new int[]{2, 1, 5, 3, 4}, new int[]{1, 2, 3, 4, 5}, new MergingSort());
    }

    private void test(int[] src, int[] target, SortAlgorithm sortAlgorithm) {
        sortAlgorithm.sort(src);
        Assert.assertArrayEquals(target, src);
    }

}
