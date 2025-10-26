package mst;

import java.util.*;

public class PrimAlgorithm {
    public static MSTResult run(Graph graph) {
        Set<String> visited = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int operations = 0;
        double start = System.nanoTime();


        String startNode = graph.getVertices().iterator().next();
        visited.add(startNode);
        pq.addAll(graph.getEdges(startNode));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            operations++;

            if (visited.contains(edge.to)) continue;

            visited.add(edge.to);
            mstEdges.add(edge);
            pq.addAll(graph.getEdges(edge.to));
        }

        double end = System.nanoTime();
        int totalCost = mstEdges.stream().mapToInt(e -> e.weight).sum();
        double timeMs = (end - start) / 1_000_000;

        return new MSTResult(mstEdges, totalCost, operations, timeMs);
    }
}