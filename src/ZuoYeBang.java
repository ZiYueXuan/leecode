import java.util.Scanner;

public class ZuoYeBang {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String[] strs = str.split(" ");
        String word1 = strs[0];
        String word2 = strs[1];
        System.out.println(minDistance(word1, word2));

    }

    public static String convertStr(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        for (char c : str.toCharArray()) {
            if ( c == '_') {
                flag = true;
                continue;
            }

            if (flag) {
                sb.append(Character.toUpperCase(c));
                flag = false;
            } else {
                sb.append(Character.toLowerCase(c));

            }
        }

        return sb.toString();
    }

    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();

        if (m > n) {
            return minDistance(word2, word1);
        }

        int[] dp = new int[m + 1];

        for (int i = 0; i <= m; i++) {
            dp[i] = i;
        }

        for (int j = 1; j <= n; j++) {
            int prev = dp[0];
            dp[0] = j;

            for (int i = 1; i <= m; i++) {
                int temp = dp[i];

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i] = prev;
                } else {
                    dp[i] = 1 + Math.min(prev, Math.min(dp[i - 1], dp[i]));
                }

                prev = temp;
            }
        }

        return dp[m];
    }
}
