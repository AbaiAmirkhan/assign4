Bonus Task: Dijkstra's Algorithm (Shortest Path)

As part of the bonus requirements, the repository's architecture was refactored and expanded to support weighted graph mechanics and single-source shortest path calculations:

### 1. Architectural Updates
* **Weighted Edges:** Modified the `Edge` class to natively support a primitive `int weight` field.
* **Graph Infrastructure:** Upgraded the `Graph` class adjacency map to transition from basic integer lists to comprehensive `Edge` object tracking (`Map<Integer, List<Edge>>`). This enables bidirectional, undirected weighted paths.
* **Weight Injection:** Updated the `Experiment` generator suite to automatically inject deterministic weights (randomly seeded bounded integers from `1` to `10`) during the structural build cycle.

### 2. Algorithmic Approach
The implementation utilizes a clean **loop-and-array approach** avoiding additional overhead from alternative collections like a priority queue:
* An array tracking path weights (`distances[]`) is instantiated and pre-filled with infinity ($\infty$), setting the starting source node parameter to `0`.
* A complementary boolean array (`visited[]`) keeps track of settled nodes.
* The algorithm loops through all unvisited vertices, locating the node `u` holding the absolute minimum distance, and registers it as visited.
* It systematically updates (relaxes) the path weights of all outstanding, neighboring nodes adjacent to `u`.

---

## Sample Execution Output

Below is a representation of the console summary generated during evaluation runs (specifically showcasing the structural output of a 10-node test graph):

```text
============================
Testing Graph with 10 vertices
============================
Adjacency List (with weights):
0 -> 1(w:4) 2(w:2) 
1 -> 0(w:4) 2(w:6) 3(w:8) 
2 -> 0(w:2) 1(w:6) 3(w:2) 4(w:1) 
3 -> 1(w:8) 2(w:2) 4(w:5) 5(w:10) 
4 -> 2(w:1) 3(w:5) 5(w:4) 6(w:2) 
5 -> 3(w:10) 4(w:4) 6(w:8) 7(w:1) 
6 -> 4(w:2) 5(w:8) 7(w:3) 8(w:8) 
7 -> 5(w:1) 6(w:3) 8(w:10) 9(w:2) 
8 -> 6(w:8) 7(w:10) 9(w:3) 
9 -> 7(w:2) 8(w:3) 

BFS Traversal: 0 1 2 3 4 5 6 7 8 9 
DFS Traversal: 0 1 2 3 4 5 6 7 8 9 
BFS Execution Time: 84700 ns
DFS Execution Time: 42100 ns

Dijkstra Shortest Paths from Vertex 0:
Vertex  Distance from Source
0       0
1       4
2       2
3       4
4       3
5       7
6       5
7       8
8       13
9       10

Dijkstra Execution Time: 125600 ns
