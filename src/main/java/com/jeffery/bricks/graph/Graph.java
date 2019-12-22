package com.jeffery.bricks.graph;

import com.jeffery.bricks.list.Queue;
import com.jeffery.bricks.list.impl.ArrayQueue;

import java.util.List;
import java.util.function.IntConsumer;

/**
 * 图
 */
public interface Graph {

    Graph insertArc(int start, int end);

    Graph insertArc(int start, int end, int weight);

    Graph deleteArc(int start, int end);

    boolean isConnected(int start, int end);

    List<Integer> getAdjVexes(int vertex);

    int degree(int vertex);

    int size();

    default void dfsTraverse(IntConsumer action) {
        boolean[] visited = new boolean[size()];
        for (int v = 0; v < size(); v++) {
            if (!visited[v]) {
                doDfsTraverse(v, action, visited);
            }
        }
    }

    default void doDfsTraverse(int v, IntConsumer action, boolean[] visited) {
        visited[v] = true;
        action.accept(v);
        List<Integer> adjVexes = getAdjVexes(v);
        for (int i = 0; i < adjVexes.size(); i++) {
            int w = adjVexes.get(i);
            if (!visited[w]) {
                doDfsTraverse(w, action, visited);
            }
        }
    }

    default void bfsTraverse(IntConsumer action) {
        boolean[] visited = new boolean[size()];
        Queue<Integer> queue = new ArrayQueue<>(size());
        for (int v = 0; v < size(); v++) {
            if (!visited[v]) {
                visited[v] = true;
                action.accept(v);
                queue.enqueue(v);
                while (!queue.isEmpty()) {
                    Integer u = queue.dequeue();
                    List<Integer> adjVexes = getAdjVexes(u);
                    for (int i = 0; i < adjVexes.size(); i++) {
                        int w = adjVexes.get(i);
                        if (!visited[w]) {
                            visited[w] = true;
                            action.accept(w);
                            queue.enqueue(w);
                        }
                    }
                }
            }
        }
    }

}
