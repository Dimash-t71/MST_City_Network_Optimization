package mst;

import java.util.List;

public class MSTResult {
    public List<Edge> mstEdges;
    public int totalCost;
    public int operations;
    public double executionTimeMs;

    public MSTResult(List<Edge> mstEdges, int totalCost, int operations, double executionTimeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operations = operations;
        this.executionTimeMs = executionTimeMs;
    }

    @Override
    public String toString() {
        return "MSTResult{" +
                "totalCost=" + totalCost +
                ", operations=" + operations +
                ", executionTimeMs=" + executionTimeMs +
                ", mstEdges=" + mstEdges +
                '}';
    }
}