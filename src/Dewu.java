import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Dewu {
    public static void main(String[] args) {
        int[] heights = {186, 186, 150, 200, 160, 130, 197, 220};
        System.out.println(new Random().nextInt());
    }

    public static boolean contain(String basket1, String basket2) {
        HashMap<Character, Integer> basket = new HashMap<>();
        for (char c : basket1.toCharArray()) {
            basket.put(c, basket.getOrDefault(c, 0) + 1);
        }

        for (char c : basket2.toCharArray()) {
            if (!basket.containsKey(c)) {
                return false;
            }
            basket.put(c, basket.getOrDefault(c, 0) - 1);
        }
        return true;
    }

    public static int minDelete(int[] arr) {
        int n = arr.length;
        if (n <= 2) return 0;

        int[] LIS = new int[n];
        Arrays.fill(LIS, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }

        int[] LDS = new int[n];
        Arrays.fill(LDS, 1);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }

        int maxRetain = 0;
        for (int i = 1; i < n - 1; i++) {
            if (LIS[i] > 1 && LDS[i] > 1) {
                maxRetain = Math.max(maxRetain, LIS[i] + LDS[i] - 1);
            }
        }

        return n - maxRetain;
    }
}
