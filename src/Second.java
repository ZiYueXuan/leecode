import java.util.*;

public class Second {

    private static Map<String, Integer> vertexMap = new HashMap<>();
    private static List<String> indexVertex = new ArrayList<>();
    private static int vertices;
    private static int[][] matrix;
    private static boolean[] visited;
    private static int[] parent;
    private static int[] distance;



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        input.nextLine();
        vertices = N;
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(matrix[i], Integer.MAX_VALUE / 2);
        }

        String start = input.next();
        String end = input.next();
        input.nextLine();

        buildVertexMap(input);


        while (true) {
            String source = input.next();
            if (source.equals("0000")) {
                break;
            }
            String destination = input.next();
            int weight = input.nextInt();
            addEdge(source, destination, weight);
        }

        int startVertex = vertexMap.get(start);
        int endVertex = vertexMap.get(end);
        dijkstra(startVertex);

        printShortestPath(endVertex);
    }

    private static void buildVertexMap(Scanner input) {
        for (int i = 0; i < vertices; i++) {
            String vertexName = input.next();
            vertexMap.put(vertexName, i);
            indexVertex.add(vertexName);
        }
    }

    private static void printShortestPath(int endVertex) {
        int currentVertex = endVertex;
        Stack<String> path = new Stack<>();
        while (currentVertex != -1) {
            path.push(indexVertex.get(currentVertex));
            currentVertex = parent[currentVertex];
        }
        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }

    private static void addEdge(String source, String destination, int weight) {
        int sourceIndex = vertexMap.get(source);
        int destinationIndex = vertexMap.get(destination);
        matrix[sourceIndex][destinationIndex] = weight;
        matrix[destinationIndex][sourceIndex] = weight;
    }

    public static void dijkstra(int source) {
        visited = new boolean[vertices];
        parent = new int[vertices];
        distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distance[source] = 0;
        for (int i = 0; i < vertices; i++) {
            int u = minDistance(distance, visited);
            visited[u] = true;
            for (int v = 0; v < vertices; v++) {
                if (matrix[u][v] != Integer.MAX_VALUE && !visited[v] && distance[u] + matrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + matrix[u][v];
                    parent[v] = u;
                }
            }
        }
    }

    public static int minDistance(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}
