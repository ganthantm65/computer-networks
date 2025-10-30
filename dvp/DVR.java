import java.io.*;

public class DVR {
    static int v, e;
    static int[][] cost = new int[10][10];
    static int[][] dist = new int[10][10];
    static int[][] via = new int[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter number of vertices: ");
        v = Integer.parseInt(br.readLine());

        System.out.print("Enter number of edges: ");
        e = Integer.parseInt(br.readLine());

        // Initialize graph
        for (int i = 0; i < v; i++)
            for (int j = 0; j < v; j++)
                cost[i][j] = (i == j) ? 0 : 9999;

        // Input edges
        for (int i = 0; i < e; i++) {
            System.out.println("\nEdge " + (i + 1));
            System.out.print("Source: ");
            int s = Integer.parseInt(br.readLine()) - 1;
            System.out.print("Destination: ");
            int d = Integer.parseInt(br.readLine()) - 1;
            System.out.print("Cost: ");
            int c = Integer.parseInt(br.readLine());
            cost[s][d] = cost[d][s] = c;
        }

        // Initial table setup
        for (int i = 0; i < v; i++)
            for (int j = 0; j < v; j++) {
                dist[i][j] = cost[i][j];
                via[i][j] = (cost[i][j] != 9999 && i != j) ? j : -1;
            }

        // DVR update
        for (int k = 0; k < v; k++)
            for (int i = 0; i < v; i++)
                for (int j = 0; j < v; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        via[i][j] = k;
                    }

        // Display final routing table
        System.out.println("\n--- Final Routing Table ---");
        for (int i = 0; i < v; i++) {
            System.out.println("\nRouter " + (i + 1));
            for (int j = 0; j < v; j++) {
                System.out.println("To Node " + (j + 1) + " : Distance = " + dist[i][j]);
            }
        }
    }
}
