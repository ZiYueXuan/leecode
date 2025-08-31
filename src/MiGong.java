import java.util.*;

public class MiGong {
    private static final int[] dx = {-1, 1};
    private static final int[] dy = {0, 0};

    static class Point {
        int x;
        int y;
        int steps;
        public Point(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        input.nextLine();

        char[][] matrix = new char[n][m];
        int startX = -1, startY = -1;
        List<Point> exits = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = input.nextLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line.charAt(j);
                if (matrix[i][j] == 'k') {
                    startX = i;
                    startY = j;
                } else if (matrix[i][j] == 'e') {
                    exits.add(new Point(i, j, 0));
                }
            }
        }

        if (startX == -1) {
            System.out.println("-1");
        }

        int reachableExits = 0;
        int minSteps = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[n][m];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (matrix[point.x][point.y] == 'e') {
                reachableExits++;
                minSteps = Math.min(minSteps, point.steps);
            }

            for (int i = 0; i < 2; i++) {
                int nextX = point.x + dx[i];
                int nextY = point.y + dy[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && matrix[nextX][nextY] != '*' && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.offer(new Point(nextX, nextY, point.steps + 1));
                }
            }
        }

        if (reachableExits == 0) {
            System.out.println("-1");
        } else {
            System.out.println(reachableExits + " " + minSteps);
        }
    }
}
