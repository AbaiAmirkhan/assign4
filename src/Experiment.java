import java.util.Random;

public class Experiment {

    public void runTraversals(Graph g) {
        // Run existing traversals
        long startBFS = System.nanoTime();
        g.bfs(0);
        long endBFS = System.nanoTime();

        long startDFS = System.nanoTime();
        g.dfs(0);
        long endDFS = System.nanoTime();

        System.out.println("BFS Execution Time: " + (endBFS - startBFS) + " ns");
        System.out.println("DFS Execution Time: " + (endDFS - startDFS) + " ns");

        long startDijkstra = System.nanoTime();
        g.dijkstra(0); // Computes shortest distance from start vertex 0 to all others
        long endDijkstra = System.nanoTime();

        System.out.println("Dijkstra Execution Time: " + (endDijkstra - startDijkstra) + " ns");
    }

    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        Random rand = new Random(42); // Seeded random for consistent edge weights

        for (int size : sizes) {
            System.out.println("\n============================");
            System.out.println("Testing Graph with " + size + " vertices");
            System.out.println("============================");

            Graph graph = new Graph();

            for (int i = 0; i < size; i++) {
                graph.addVertex(new Vertex(i));
            }

            // Create weighted connections
            for (int i = 0; i < size - 1; i++) {
                // Generates an edge weight between 1 and 10
                int weight1 = rand.nextInt(10) + 1;
                graph.addEdge(i, i + 1, weight1);

                if (i + 2 < size) {
                    int weight2 = rand.nextInt(10) + 1;
                    graph.addEdge(i, i + 2, weight2);
                }
            }

            if (size == 10) {
                graph.printGraph();
            }

            runTraversals(graph);
        }
    }

    public void printResults() {
        System.out.println("\nTraversal and Dijkstra experiments completed successfully.");
    }
}