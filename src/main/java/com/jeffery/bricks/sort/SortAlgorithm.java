package com.jeffery.bricks.sort;

/**
 * 排序算法接口
 */
public interface SortAlgorithm {

    void sort(int[] arr);

    default void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
