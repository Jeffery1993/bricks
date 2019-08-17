package com.jeffery.bricks.sort;

import java.util.Objects;

/**
 * 归并排序
 */
public class MergingSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr) {
        Objects.requireNonNull(arr);
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int s, int t) {
        if (s != t) {
            int m = (s + t) / 2;
            sort(arr, s, m);
            sort(arr, m + 1, t);
            merge(arr, s, m, t);
        }
    }

    private void merge(int[] arr, int s, int m, int n) {
        int[] tmp = new int[n - s + 1];
        int i = s, j = m + 1, k = 0;
        for (; i <= m && j <= n; k++) {
            if (arr[i] <= arr[j]) {
                tmp[k] = arr[i++];
            } else {
                tmp[k] = arr[j++];
            }
        }
        if (i <= m) {
            System.arraycopy(arr, i, tmp, k, m - i + 1);
        } else if (j <= n) {
            System.arraycopy(arr, j, tmp, k, n - j + 1);
        }
        System.arraycopy(tmp, 0, arr, s, tmp.length);
    }

}
