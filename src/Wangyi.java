import java.util.Scanner;

public class Wangyi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int P = sc.nextInt();
        for (int p = 0; p < P; p++) {
            int T = sc.nextInt();
            int C = sc.nextInt();
            int N = sc.nextInt();
            int M = sc.nextInt();

            long[] s = new long[N];
            for (int i = 0; i < N; i++) {
                s[i] = sc.nextLong();
            }

            long[] m = new long[M];
            for (int i = 0; i < M; i++) {
                m[i] = sc.nextLong();
            }

            int maxKills = 0;
            for (long si : s) {
                long end = si + T;
                int left = lowerBound(m, si);
                int right = lowerBound(m, end);
                int count = right - left;
                if (count > maxKills) {
                    maxKills = count;
                }
            }
            System.out.println(maxKills);
        }
        sc.close();
    }

    private static int lowerBound(long[] arr, long target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
