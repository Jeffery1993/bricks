package com.jeffery.bricks.graph.impl;

import com.jeffery.bricks.graph.Graph;
import com.jeffery.bricks.graph.UndiGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 无向图矩阵实现
 */
public class UndiGraphMatImpl extends DiGraphMatImpl implements UndiGraph {

    public UndiGraphMatImpl(int size) {
        super(size);
    }

    public UndiGraphMatImpl(int[][] mat) {
        super(mat);
    }

    @Override
    protected Graph setValueInternal(int start, int end, int weight) {
        if (start == end || weight < 0) {
            throw new IllegalArgumentException();
        }
        if (start > end) {
            mat[start][end] = weight;
        } else {
            mat[end][start] = weight;
        }
        return this;
    }

    @Override
    public boolean isConnected(int start, int end) {
        if (start == end) {
            throw new IllegalArgumentException();
        }
        if (start > end) {
            return mat[start][end] > 0;
        } else {
            return mat[end][start] > 0;
        }
    }

    @Override
    public List<Integer> getAdjVexes(int vertex) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < vertex; j++) {
            if (mat[vertex][j] > 0) {
                list.add(j);
            }
        }
        for (int i = vertex + 1; i < mat.length; i++) {
            if (mat[i][vertex] > 0) {
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public int degree(int vertex) {
        int degree = 0;
        for (int j = 0; j < vertex; j++) {
            if (mat[vertex][j] > 0) {
                degree++;
            }
        }
        for (int i = vertex + 1; i < mat.length; i++) {
            if (mat[i][vertex] > 0) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j <= i; j++) {
                sb.append(mat[i][j]);
                if (j < i) {
                    sb.append(" ");
                }
            }
            if (i < mat.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

}
