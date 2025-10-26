package mst;

import java.util.*;

public class Graph {
    private final Map<String, List<Edge>> adjacencyList = new HashMap<>();


    public void addEdge(String from, String to, int weight) {
        adjacencyList.putIfAbsent(from, new ArrayList<>());
        adjacencyList.putIfAbsent(to, new ArrayList<>());
        adjacencyList.get(from).add(new Edge(from, to, weight));
        adjacencyList.get(to).add(new Edge(to, from, weight));
    }


    public List<Edge> getAllEdges() {
        List<Edge> allEdges = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (String node : adjacencyList.keySet()) {
            for (Edge e : adjacencyList.get(node)) {
                String key = e.from + "-" + e.to;
                String reverse = e.to + "-" + e.from;
                if (!seen.contains(reverse)) {
                    allEdges.add(e);
                    seen.add(key);
                }
            }
        }
        return allEdges;
    }


    public Set<String> getVertices() {
        return adjacencyList.keySet();
    }


    public List<Edge> getEdges(String node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>());
    }
}