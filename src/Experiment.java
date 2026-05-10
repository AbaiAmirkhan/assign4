public class Experiment {

    // Run BFS and DFS with execution time measurement
    public void runTraversals(Graph g) {

        long startBFS = System.nanoTime();
        g.bfs(0);
        long endBFS = System.nanoTime();

        long startDFS = System.nanoTime();
        g.dfs(0);
        long endDFS = System.nanoTime();

        long bfsTime = endBFS - startBFS;
        long dfsTime = endDFS - startDFS;

        System.out.println("BFS Execution Time: " + bfsTime + " ns");
        System.out.println("DFS Execution Time: " + dfsTime + " ns");
    }

    // Create and test graphs of different sizes
    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};

        for (int size : sizes) {
            System.out.println("\n============================");
            System.out.println("Testing Graph with " + size + " vertices");
            System.out.println("============================");

            Graph graph = new Graph();

            // Add vertices
            for (int i = 0; i < size; i++) {
                graph.addVertex(new Vertex(i));
            }

            // Add edges
            for (int i = 0; i < size - 1; i++) {
                graph.addEdge(i, i + 1);

                // Additional edges for more connectivity
                if (i + 2 < size) {
                    graph.addEdge(i, i + 2);
                }
            }

            // Print graph only for small graph
            if (size == 10) {
                graph.printGraph();
            }

            runTraversals(graph);
        }
    }

    public void printResults() {
        System.out.println("\nTraversal experiments completed successfully.");
    }
}