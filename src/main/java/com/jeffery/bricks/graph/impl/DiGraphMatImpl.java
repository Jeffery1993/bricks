package com.jeffery.bricks.graph.impl;

import com.jeffery.bricks.graph.DiGraph;
import com.jeffery.bricks.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 有向图矩阵实现
 */
public class DiGraphMatImpl implements DiGraph {

    protected final int[][] mat;

    public DiGraphMatImpl(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        this.mat = new int[size][size];
    }

    public DiGraphMatImpl(int[][] mat) {
        this.mat = mat.clone();
    }

    @Override
    public Graph insertArc(int start, int end) {
        return insertArc(start, end, 1);
    }

    @Override
    public Graph insertArc(int start, int end, int weight) {
        return setValueInternal(start, end, weight);
    }

    @Override
    public Graph deleteArc(int start, int end) {
        return setValueInternal(start, end, 0);
    }

    protected Graph setValueInternal(int start, int end, int weight) {
        if (start == end || weight < 0) {
            throw new IllegalArgumentException();
        }
        mat[start][end] = weight;
        return this;
    }

    @Override
    public boolean isConnected(int start, int end) {
        if (start == end) {
            throw new IllegalArgumentException();
        }
        return mat[start][end] > 0;
    }

    @Override
    public List<Integer> getAdjVexes(int vertex) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < mat.length; j++) {
            if (mat[vertex][j] > 0) {
                list.add(j);
            }
        }
        return list;
    }

    @Override
    public int degree(int vertex) {
        return inDegree(vertex) + outDegree(vertex);
    }

    @Override
    public int inDegree(int vertex) {
        int inDegree = 0;
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][vertex] > 0) {
                inDegree++;
            }
        }
        return inDegree;
    }

    @Override
    public int outDegree(int vertex) {
        int outDegree = 0;
        for (int j = 0; j < mat.length; j++) {
            if (mat[vertex][j] > 0) {
                outDegree++;
            }
        }
        return outDegree;
    }

    @Override
    public int size() {
        return mat.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                sb.append(mat[i][j]);
                if (j < mat.length - 1) {
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
