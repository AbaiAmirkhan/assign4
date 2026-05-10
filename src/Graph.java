import java.util.*;

public class Graph {

    private Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Add a new vertex
    public void addVertex(Vertex v) {
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    // Add an edge between vertices
    public void addEdge(int from, int to) {

        adjacencyList.get(from).add(to);

        // Uncomment for undirected graph
        adjacencyList.get(to).add(from);
    }

    // Print graph structure
    public void printGraph() {

        System.out.println("Adjacency List:");

        for (int vertex : adjacencyList.keySet()) {

            System.out.print(vertex + " -> ");

            for (int neighbor : adjacencyList.get(vertex)) {
                System.out.print(neighbor + " ");
            }

            System.out.println();
        }
    }

    // Breadth-First Search
    public void bfs(int start) {

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {

            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjacencyList.get(current)) {

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        System.out.println();
    }

    // Depth-First Search
    public void dfs(int start) {

        Set<Integer> visited = new HashSet<>();

        System.out.print("DFS Traversal: ");
        dfsHelper(start, visited);

        System.out.println();
    }

    private void dfsHelper(int current, Set<Integer> visited) {

        visited.add(current);
        System.out.print(current + " ");

        for (int neighbor : adjacencyList.get(current)) {

            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }
}