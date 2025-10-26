Assignment 3 — Optimization of a City Transportation Network

1. Objective
The goal of this project was to find the Minimum Spanning Tree of a weighted undirected graph using two algorithms — Prim’s and Kruskal’s.  
The MST connects all city districts with the smallest total construction cost.

2. Description
In this assignment, the city transportation network is represented as a graph:
- Vertices — city districts  
- Edges — possible roads between districts  
- Weights — cost of construction for each road  

Two algorithms were implemented and compared:
1. Prim’s Algorithm — starts from one vertex and grows the MST step by step.  
2. Kruskal’s Algorithm — sorts edges by weight and joins components using Union-Find.

The goal was to check that both algorithms produce the same total cost and to compare their speed and number of operations.

3. Input / Output format
 {
  "id": 1,
  "nodes": ["A","B","C","D","E"],
  "edges": [
    {"from":"A","to":"B","weight":4},
    ...
  ]
}
Output: ass_3_output.json — contains results array. For each graph we store:
graph_id
prim and kruskal objects each with:
mstEdges (list),
totalCost,
operations,
executionTimeMs

4. Summary of input data and algorithm results 

I used two small test graphs included in the repo see ass_3_input.json. After running both algorithms the ass_3_output.json contained example:

Graph 1
	•	Vertices: 5, Edges: 7
	•	Prim: totalCost = 16, operations = 14, executionTimeMs ≈ 0.05
	•	Kruskal: totalCost = 16, operations = 7, executionTimeMs ≈ 0.015

Graph 2
	•	Vertices: 4, Edges: 5
	•	Prim: totalCost = 6, operations = 10, executionTimeMs ≈ 0.013
	•	Kruskal: totalCost = 6, operations = 5, executionTimeMs ≈ 0.004

5. Comparison: Prim vs Kruskal 

Theory:
	1) Prim’s: uses a priority queue, grows tree from a start node. Complexity ~ O(E log V) with binary heap. Good for dense graphs or adjacency matrix representations.
	2) Kruskal’s: sorts edges and uses Union-Find. Complexity ~ O(E log E) (≈ O(E log V)). Good for sparse graphs and when edges can be sorted quickly.

What I observed:
	1)	Both algorithms produce the same total MST cost .
	2)	On my small test graphs Kruskal was faster — expected for sparse graphs.
	3)	Prim did more queue operations here, but on dense graphs its behavior should be closer or better because it avoids sorting all edges.


6. When to prefer which 
	1)	Use Kruskal when the graph is sparse (E relatively small) or when edges are listed and easy to sort. Union-Find is simple to implement.
  2)  Use Prim when the graph is dense or you have adjacency structures and want to avoid global edge sorting.
	3)	Implementation complexity: Kruskal + Union-Find is straightforward; Prim needs a good priority queue and careful handling of visited nodes.
	4) 	In practice: both are fine — choice depends on input size/density and available data structures.

Tests

I added simple JUnit tests that verify:
	1 Prim and Kruskal return same total cost on the sample graph.
	2	Number of MST edges equals V-1 (when connected).
	3	Behaviour on a disconnected graph (we check it does not produce a full MST).
	4	Execution time / operation counters are non-negative.
  
  Files in this repo
	1)	src/main/java/mst/ — Java sources (Edge, Graph, algorithms, Main)
	2)	src/main/resources/ass_3_input.json — sample input
	3)	src/main/resources/ass_3_output.json — generated results
	4)	src/test/java/mst/MSTTest.java — unit tests
	 5) pom.xml — Maven config

  Conclusion

Both Prim’s and Kruskal’s algorithms were implemented and tested successfully.
They produced identical MST total costs and verified theoretical expectations:
	1)	Kruskal performed better on sparse graphs.
	2)	Prim is more efficient on dense graphs.
The implementation meets all requirements of the assignment.

 References
	1.	Cormen, Leiserson, Rivest, and Stein — Introduction to Algorithms -CLRS.
	2.	GeeksForGeeks — Prim’s vs Kruskal’s Algorithm.
	3.	Java Docs — PriorityQueue, Collections.sort, Gson library documentation

