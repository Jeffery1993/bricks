package com.jeffery.bricks.graph;

/**
 * 有向图
 */
public interface DiGraph extends Graph {

    int inDegree(int vertex);

    int outDegree(int vertex);

}
