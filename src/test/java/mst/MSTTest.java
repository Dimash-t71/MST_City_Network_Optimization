package mst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MSTTest {

    @Test
    void testPrimAndKruskalSameCost() {
        Graph g = new Graph();
        g.addEdge("A", "B", 4);
        g.addEdge("A", "C", 3);
        g.addEdge("B", "C", 2);
        g.addEdge("B", "D", 5);
        g.addEdge("C", "D", 7);
        g.addEdge("C", "E", 8);
        g.addEdge("D", "E", 6);

        MSTResult prim = PrimAlgorithm.run(g);
        MSTResult kruskal = KruskalAlgorithm.run(g);

        // Одинаковая общая стоимость
        assertEquals(prim.totalCost, kruskal.totalCost,
                "Prim and Kruskal should produce the same total MST cost");

        // Кол-во рёбер = V - 1 (5 вершин -> 4 ребра)
        assertEquals(4, prim.mstEdges.size());
        assertEquals(4, kruskal.mstEdges.size());

        // Проверка, что результат положительный
        assertTrue(prim.totalCost > 0);
        assertTrue(kruskal.totalCost > 0);
    }

    @Test
    void testDisconnectedGraph() {
        Graph g = new Graph();
        g.addEdge("A", "B", 2);
        g.addEdge("C", "D", 3); // граф из двух несвязанных компонент

        MSTResult prim = PrimAlgorithm.run(g);
        MSTResult kruskal = KruskalAlgorithm.run(g);

        // MST не может быть полным (несвязный граф)
        assertTrue(prim.mstEdges.size() < g.getVertices().size() - 1);
        assertTrue(kruskal.mstEdges.size() < g.getVertices().size() - 1);
    }

    @Test
    void testPerformanceNonNegative() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 2);
        g.addEdge("C", "D", 3);
        g.addEdge("D", "E", 4);
        g.addEdge("E", "F", 5);
        g.addEdge("F", "G", 6);

        MSTResult prim = PrimAlgorithm.run(g);
        MSTResult kruskal = KruskalAlgorithm.run(g);

        assertTrue(prim.executionTimeMs >= 0);
        assertTrue(kruskal.executionTimeMs >= 0);
        assertTrue(prim.operations >= 0);
        assertTrue(kruskal.operations >= 0);
    }
}