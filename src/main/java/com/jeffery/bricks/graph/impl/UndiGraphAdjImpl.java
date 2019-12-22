package com.jeffery.bricks.graph.impl;

import com.jeffery.bricks.graph.Graph;
import com.jeffery.bricks.graph.UndiGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 无向图邻接表实现——基于邻接多重表
 */
public class UndiGraphAdjImpl implements UndiGraph {

    private final Node[] nodes;

    public UndiGraphAdjImpl(int size) {
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
        insertInternal(start, end, arc);
        insertInternal(end, start, arc);
        return this;
    }

    private void insertInternal(int index, int value, Arc arc) {
        final Node node = nodes[index];
        if (node.firstEdge == null) {
            node.firstEdge = arc;
        } else {
            Arc tmp = node.firstEdge;
            while (tmp.getThisLink(index) != null && tmp.getThatVex(index) != value) {
                tmp = tmp.getThisLink(index);
            }
            if (tmp.getThatVex(index) == value) {
                return;
            }
            tmp.setThisLink(index, arc);
        }
    }

    @Override
    public Graph deleteArc(int start, int end) {
        deleteInternal(start, end);
        deleteInternal(end, start);
        return this;
    }

    private void deleteInternal(int index, int value) {
        final Node node = nodes[index];
        final Arc firstArc = node.firstEdge;
        if (node.firstEdge == null) {
            return;
        } else if (firstArc.getThatVex(index) == value) {
            nodes[index].firstEdge = firstArc.getThisLink(index);
        } else {
            Arc previous = firstArc;
            Arc now = previous.getThisLink(index);
            while (now.getThisLink(index) != null && now.getThatVex(index) != value) {
                previous = now;
                now = previous.getThisLink(index);
            }
            if (now.getThatVex(index) == value) {
                previous.setThisLink(index, now.getThisLink(index));
            }
        }
    }

    @Override
    public boolean isConnected(int start, int end) {
        Arc tmp = nodes[start].firstEdge;
        if (tmp == null) {
            return false;
        }
        while (tmp != null) {
            if (tmp.getThatVex(start) == end) {
                return true;
            }
            tmp = tmp.getThisLink(start);
        }
        return false;
    }

    @Override
    public List<Integer> getAdjVexes(int vertex) {
        Arc tmp = nodes[vertex].firstEdge;
        if (tmp == null) {
            return Collections.emptyList();
        }
        List<Integer> adjVexes = new ArrayList<>();
        while (tmp != null) {
            adjVexes.add(tmp.getThatVex(vertex));
            tmp = tmp.getThisLink(vertex);
        }
        return adjVexes;
    }

    @Override
    public int degree(int vertex) {
        Arc tmp = nodes[vertex].firstEdge;
        if (tmp == null) {
            return 0;
        }
        int degree = 0;
        while (tmp != null) {
            degree++;
            tmp = tmp.getThisLink(vertex);
        }
        return degree;
    }

    @Override
    public int size() {
        return nodes.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodes.length; i++) {
            sb.append(i).append(": ").append(getAdjVexes(i));
            if (i < nodes.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    static class Node {
        final int number;
        Arc firstEdge;

        Node(int number) {
            this.number = number;
        }
    }

    static class Arc {
        final int ivex;
        final int jvex;
        final int weight;
        Arc ilink;
        Arc jlink;

        Arc(int ivex, int jvex, int weight) {
            this.ivex = ivex;
            this.jvex = jvex;
            this.weight = weight;
        }

        int getThatVex(int one) {
            return ivex == one ? jvex : ivex;
        }

        Arc getThisLink(int one) {
            return ivex == one ? ilink : jlink;
        }

        Arc getThatLink(int one) {
            return ivex == one ? jlink : ilink;
        }

        void setThisLink(int one, Arc link) {
            if (ivex == one) {
                ilink = link;
            } else {
                jlink = link;
            }
        }
    }

}
