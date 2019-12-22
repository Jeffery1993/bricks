package com.jeffery.bricks.graph.impl;

import com.jeffery.bricks.graph.DiGraph;
import com.jeffery.bricks.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 有向图邻接表实现——基于十字链表
 */
public class DiGraphAdjImpl implements DiGraph {

    private final Node[] nodes;

    public DiGraphAdjImpl(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i);
        }
    }

    @Override
    public Graph insertArc(int start, int end) {
        return insertArc(start, end, 1);
    }

    @Override
    public Graph insertArc(int start, int end, int weight) {
        if (start == end || weight < 0) {
            throw new IllegalArgumentException();
        }
        final Arc arc = new Arc(start, end, weight);
        final Node startNode = nodes[start];
        if (startNode.firstOut == null) {
            startNode.firstOut = arc;
        } else {
            Arc tmp = startNode.firstOut;
            while (tmp.tailLink != null && tmp.headVex != end) {
                tmp = tmp.tailLink;
            }
            if (tmp.headVex == end) {
                return this;
            }
            tmp.tailLink = arc;
        }
        final Node endNode = nodes[end];
        if (endNode.firstIn == null) {
            endNode.firstIn = arc;
        } else {
            Arc tmp = endNode.firstIn;
            while (tmp.headLink != null && tmp.tailVex != start) {
                tmp = tmp.headLink;
            }
            if (tmp.tailVex == start) {
                return this;
            }
            tmp.headLink = arc;
        }
        return this;
    }

    @Override
    public Graph deleteArc(int start, int end) {
        final Node startNode = nodes[start];
        if (startNode.firstOut == null) {
            return this;
        } else if (startNode.firstOut.headVex == end) {
            nodes[start].firstOut = startNode.firstOut.tailLink;
        } else {
            Arc previous = startNode.firstOut;
            Arc now = previous.tailLink;
            while (now.tailLink != null && now.headVex != end) {
                previous = now;
                now = previous.tailLink;
            }
            if (now.headVex == end) {
                previous.tailLink = now.tailLink;
            }
        }
        final Node endNode = nodes[end];
        if (endNode.firstIn == null) {
            return this;
        } else if (endNode.firstIn.tailVex == start) {
            nodes[end].firstIn = endNode.firstIn.headLink;
        } else {
            Arc previous = endNode.firstIn;
            Arc now = previous.headLink;
            while (now.headLink != null && now.tailVex != start) {
                previous = now;
                now = previous.headLink;
            }
            if (now.tailVex == start) {
                previous.headLink = now.headLink;
            }
        }
        return this;
    }

    @Override
    public boolean isConnected(int start, int end) {
        for (Arc tmp = nodes[start].firstOut; tmp != null; tmp = tmp.tailLink) {
            if (tmp.headVex == end) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Integer> getAdjVexes(int vertex) {
        List<Integer> adjVexes = new ArrayList<>();
        for (Arc tmp = nodes[vertex].firstOut; tmp != null; tmp = tmp.tailLink) {
            adjVexes.add(tmp.headVex);
        }
        return adjVexes;
    }

    @Override
    public int degree(int vertex) {
        return inDegree(vertex) + outDegree(vertex);
    }

    @Override
    public int inDegree(int vertex) {
        int inDegree = 0;
        for (Arc tmp = nodes[vertex].firstIn; tmp != null; tmp = tmp.headLink) {
            inDegree++;
        }
        return inDegree;
    }

    @Override
    public int outDegree(int vertex) {
        int outDegree = 0;
        for (Arc tmp = nodes[vertex].firstOut; tmp != null; tmp = tmp.tailLink) {
            outDegree++;
        }
        return outDegree;
    }

    @Override
    public int size() {
        return nodes.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodes.length; i++) {
            sb.append(i).append(": ");
            for (Arc tmp = nodes[i].firstOut; tmp != null; tmp = tmp.tailLink) {
                sb.append(tmp.headVex).append(" ");
            }
            if (i < nodes.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    static class Node {
        final int number;
        Arc firstIn;
        Arc firstOut;

        Node(int number) {
            this.number = number;
        }
    }

    static class Arc {
        final int tailVex;
        final int headVex;
        final int weight;
        Arc headLink;
        Arc tailLink;

        Arc(int tailVex, int headVex, int weight) {
            this.tailVex = tailVex;
            this.headVex = headVex;
            this.weight = weight;
        }
    }

}
