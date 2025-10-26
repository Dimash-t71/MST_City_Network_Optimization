package mst;

import java.util.*;

public class KruskalAlgorithm {

    static class UnionFind {
        private final Map<String, String> parent = new HashMap<>();


        public String find(String x) {
            parent.putIfAbsent(x, x);
            if (!parent.get(x).equals(x))
                parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }


        public void union(String a, String b) {
            String rootA = find(a);
            String rootB = find(b);
            if (!rootA.equals(rootB))
                parent.put(rootA, rootB);
        }
    }


    public static MSTResult run(Graph graph) {
        List<Edge> edges = new ArrayList<>(graph.getAllEdges());
        Collections.sort(edges);
        UnionFind uf = new UnionFind();
        List<Edge> mstEdges = new ArrayList<>();
        int operations = 0;
        double start = System.nanoTime();

        for (Edge e : edges) {
            operations++;
            if (!uf.find(e.from).equals(uf.find(e.to))) {
                uf.union(e.from, e.to);
                mstEdges.add(e);
            }
        }

        double end = System.nanoTime();
        int totalCost = mstEdges.stream().mapToInt(ed -> ed.weight).sum();
        double timeMs = (end - start) / 1_000_000;

        return new MSTResult(mstEdges, totalCost, operations, timeMs);
    }
}