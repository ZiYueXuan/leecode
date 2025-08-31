import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        input.nextLine();
        Map<String, String> versions = new HashMap<>();
        Map<String, Integer> inDegrees = new HashMap<>();
        Set<String> allVersions = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String line = input.nextLine();
            String[] parts = line.split(" ");
            String currentVersion = parts[0];
            String previousVersion = parts[1];

            allVersions.add(currentVersion);
            allVersions.add(previousVersion);
            versions.put(currentVersion, previousVersion);

            inDegrees.put(previousVersion, inDegrees.getOrDefault(previousVersion, 0) + 1);
            inDegrees.put(currentVersion, 0);
        }

        Map<String, Integer> counts = new HashMap<>();

        Queue<String> queue = new LinkedList<>();

        for (String version: inDegrees.keySet()) {
            if (inDegrees.get(version) == 0 && !version.equals("NA")) {
                queue.add(version);
                counts.put(version, 1);
            }
            while (!queue.isEmpty()) {
                String currentVersion = queue.poll();
                String previousVersion = versions.get(currentVersion);
                if (!previousVersion.equals("NA")) {
                    int count = counts.get(currentVersion) + 1;
                    counts.put(previousVersion, counts.getOrDefault(previousVersion, 0) + count);
                    inDegrees.put(previousVersion, inDegrees.get(previousVersion) - 1);

                    if (inDegrees.get(previousVersion) == 0) {
                        queue.add(previousVersion);
                    }
                }
            }
        }

        int maxCount = 0;
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry: counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                result.clear();
                result.add(entry.getKey());
            } else if (entry.getValue() == maxCount) {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result);

        StringBuilder s = new StringBuilder();
        for (String version: result) {
            s.append(version).append(" ");
        }
        System.out.println(s.toString().trim());
    }
}