package com.jeffery.bricks.sort;

import java.util.Objects;

/**
 * 插入排序
 */
public class InsertionSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr) {
        Objects.requireNonNull(arr);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                int tmp = arr[i];
                arr[i] = arr[i - 1];
                int j = i - 2;
                for (; j >= 0 && tmp < arr[j]; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = tmp;
            }
        }
    }

}
