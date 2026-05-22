import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to, int weight) {
        Vertex source = new Vertex(from);
        Vertex destination = new Vertex(to);

        adjacencyList.get(from).add(new Edge(source, destination, weight));
        adjacencyList.get(to).add(new Edge(destination, source, weight)); // Undirected
    }

    public void printGraph() {
        System.out.println("Adjacency List (with weights):");
        for (int vertex : adjacencyList.keySet()) {
            System.out.print(vertex + " -> ");
            for (Edge edge : adjacencyList.get(vertex)) {
                System.out.print(edge.getDestination().getId() + "(w:" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }

    public void dijkstra(int start) {
        int numVertices = adjacencyList.size();

        int[] distances = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int u = findMinDistanceVertex(distances, visited, numVertices);

            if (u == -1) break;
            visited[u] = true;

            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.getDestination().getId();
                int weight = edge.getWeight();

                if (!visited[v] && distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }

        printDijkstraResults(start, distances);
    }

    private int findMinDistanceVertex(int[] distances, boolean[] visited, int numVertices) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < numVertices; v++) {
            if (!visited[v] && distances[v] <= min) {
                min = distances[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void printDijkstraResults(int start, int[] distances) {
        System.out.println("\nDijkstra Shortest Paths from Vertex " + start + ":");
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < distances.length; i++) {
            String distStr = (distances[i] == Integer.MAX_VALUE) ? "Unreachable" : String.valueOf(distances[i]);
            System.out.println(i + "\t\t" + distStr);
        }
    }

    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (Edge edge : adjacencyList.get(current)) {
                int neighbor = edge.getDestination().getId();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (Edge edge : adjacencyList.get(current)) {
            int neighbor = edge.getDestination().getId();
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }
}