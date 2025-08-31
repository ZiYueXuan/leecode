import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        input.nextLine();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = input.nextInt();
        }
        int[] maxSteepness = maxSteepness(arr);
        for (int i = 0; i < maxSteepness.length; i++) {
            System.out.print(maxSteepness[i] + " ");
        }
    }

    public static int calculateSteepness(int[] arr) {
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            int num = Math.abs(arr[i] - arr[i - 1]);
            res += num;
        }
        return res;
    }

    public static int[] maxSteepness(int[] arr) {
        Arrays.sort(arr);
        int[] result = new int[arr.length];
        int left = 0;
        int right = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                result[i] = arr[right--];
            } else {
                result[i] = arr[left++];
            }
        }

        // 调整一下数组的顺序，尽量让它满足字典序
        for (int i = 0; i < result.length - 1; i += 2) {
            if (i + 1 < result.length && result[i] > result[i + 1]) {
                // 交换相邻的两个元素
                int temp = result[i];
                result[i] = result[i + 1];
                result[i + 1] = temp;
            }
        }

        return result;
    }
}