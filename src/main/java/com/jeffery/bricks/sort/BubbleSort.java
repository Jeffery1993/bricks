package com.jeffery.bricks.sort;

import java.util.Objects;

/**
 * 冒泡排序
 */
public class BubbleSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr) {
        Objects.requireNonNull(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

}
