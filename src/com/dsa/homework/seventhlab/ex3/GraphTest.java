package com.dsa.homework.seventhlab.ex3;

import com.dsa.homework.seventhlab.ex1.UndirectedGraph;
import com.dsa.homework.seventhlab.ex2.DirectedWeightedGraph;

public class GraphTest {
    public static void main(String[] args) {
        // Test đồ thị vô hướng
        UndirectedGraph uGraph = new UndirectedGraph(5);
        uGraph.addEdge(0, 1);
        uGraph.addEdge(0, 2);
        uGraph.addEdge(1, 3);

        System.out.println("Undirected Graph:");
        System.out.println("Number of vertices: " + uGraph.numVertices());
        System.out.println("Number of edges: " + uGraph.numEdges());
        System.out.println("Degree of vertex 0: " + uGraph.degree(0));
        System.out.println("Adjacent vertices of 0: " + uGraph.adjacentVertices(0));
        System.out.println("Has edge between 0 and 1: " + uGraph.hasEdge(0, 1));
        System.out.println();

        // Test đồ thị có hướng
        DirectedWeightedGraph dGraph = new DirectedWeightedGraph(5);
        dGraph.addEdge(0, 1, 3);
        dGraph.addEdge(0, 2, 2);
        dGraph.addEdge(1, 3, 4);

        System.out.println("Directed Weighted Graph:");
        System.out.println("Number of vertices: " + dGraph.numVertices());
        System.out.println("Number of edges: " + dGraph.numEdges());
        System.out.println("Out degree of vertex 0: " + dGraph.outDegree(0));
        System.out.println("In degree of vertex 3: " + dGraph.inDegree(3));
        System.out.println("Weight of edge 0 -> 1: " + dGraph.getWeight(0, 1));
        System.out.println("Adjacent vertices of 0: " + dGraph.adjacentVertices(0));
    }
}

