import java.util.*;

public class Dijkstra {
    private int nodes;
    private int[][] graph;
    private int[] distance;
    private boolean[] visited;

    // Constructor
    public Dijkstra(int nodes) {
        this.nodes = nodes;
        graph = new int[nodes + 1][nodes + 1];
        distance = new int[nodes + 1];
        visited = new boolean[nodes + 1];
    }

    // Dijkstra algorithm
    public void dijkstra(int source) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int count = 1; count <= nodes; count++) {
            int u = -1;
            int minDist = Integer.MAX_VALUE;

            // Find unvisited node with minimum distance
            for (int i = 1; i <= nodes; i++) {
                if (!visited[i] && distance[i] < minDist) {
                    minDist = distance[i];
                    u = i;
                }
            }

            if (u == -1) break;
            visited[u] = true;

            // Update distances of neighbours
            for (int v = 1; v <= nodes; v++) {
                if (!visited[v] && graph[u][v] != Integer.MAX_VALUE &&
                    distance[u] != Integer.MAX_VALUE &&
                    distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        Dijkstra d = new Dijkstra(n);

        System.out.println("Enter weighted adjacency matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                d.graph[i][j] = sc.nextInt();
                if (i != j && d.graph[i][j] == 0) d.graph[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();
        System.out.print("Enter destination vertex: ");
        int destination = sc.nextInt();

        d.dijkstra(source);

        System.out.println("Shortest distance from " + source + " to " + destination + " is: " +
                d.distance[destination]);
        sc.close();
    }
}
