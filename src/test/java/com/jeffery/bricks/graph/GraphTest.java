package com.jeffery.bricks.graph;

import com.jeffery.bricks.graph.impl.DiGraphAdjImpl;
import com.jeffery.bricks.graph.impl.DiGraphMatImpl;
import com.jeffery.bricks.graph.impl.UndiGraphAdjImpl;
import com.jeffery.bricks.graph.impl.UndiGraphMatImpl;
import org.junit.Test;

public class GraphTest {

    @Test
    public void test() {
        int[][] mat = {{0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}, {1, 0, 0, 0}};
        Graph graph1 = new DiGraphMatImpl(mat);
        test(graph1);

        Graph graph2 = new UndiGraphMatImpl(5);
        graph2.insertArc(0, 1).insertArc(0, 3).insertArc(1, 2).insertArc(1, 4).insertArc(2, 3).insertArc(2, 4);
        test(graph2);

        Graph graph3 = new UndiGraphMatImpl(7);
        graph3.insertArc(0, 1).insertArc(0, 2).insertArc(1, 3).insertArc(1, 4).insertArc(2, 5).insertArc(2, 6);
        test(graph3);

        Graph graph4 = new DiGraphAdjImpl(4);
        graph4.insertArc(0, 1).insertArc(0, 2).insertArc(2, 3).insertArc(3, 0);
        test(graph4);

        Graph graph5 = new UndiGraphAdjImpl(4);
        graph5.insertArc(0, 1).insertArc(0, 2).insertArc(2, 3).insertArc(3, 0);
        test(graph5);
    }

    private void test(Graph graph) {
        System.out.println("****************************************");
        System.out.println(graph);
        graph.dfsTraverse(v -> System.out.print(v + " "));
        System.out.println();
        graph.bfsTraverse(v -> System.out.print(v + " "));
        System.out.println();
    }

}
