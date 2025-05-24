import java.util.*;

/**
 * Problem 763. Partition Labels
 *
 * Intuition:
 *   Each letter must be contained within a single partition. If we know the
 *   last occurrence of every character in the string, then as we scan from
 *   left to right we can maintain a “window” that must at least extend to the
 *   furthest last occurrence of any character seen so far. Once our current
 *   index reaches that furthest extent, we can close a partition.
 *
 * Approach:
 *   1. First pass: record the last index of each character in a map.
 *   2. Second pass: iterate with index i from 0 to n−1.
 *        - Let `end` = lastIndex[s[i]] be the furthest we must go for s[i].
 *        - Expand the window’s `end` by taking the max over lastIndex of all
 *          characters seen until we reach i == end.
 *        - When i == end, we have one partition from `start` to `end`. Record
 *          its size (end − start + 1) and set start = i+1 for the next partition.
 *
 * Time Complexity: O(n), where n = s.length(), since we do two linear passes.
 * Space Complexity: O(1) extra space for the map of size at most 26 plus the
 *                  output list (O(n) in worst case if many partitions).
 */
public class PartitionLabels {

    /**
     * Partitions the string so that each letter appears in at most one part,
     * and returns a list of the sizes of these parts.
     */
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        // 1) Record last occurrence of each character
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            lastIndex.put(s.charAt(i), i);
        }

        List<Integer> res = new ArrayList<>();
        int start = 0;
        // 2) Scan and expand partitions
        while (start < n) {
            int end = lastIndex.get(s.charAt(start));
            int i = start;
            // Expand end as we see chars whose last index is further
            while (i < end) {
                end = Math.max(end, lastIndex.get(s.charAt(i)));
                i++;
            }
            // Now i == end, we can close this partition
            res.add(end - start + 1);
            start = end + 1;
        }
        return res;
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        PartitionLabels solver = new PartitionLabels();

        String[] testInputs = {
            "ababcbacadefegdehijhklij",  // Example 1
            "eccbbbbdec",                // Example 2
            "abcdef",                    // All distinct (each letter its own partition)
            "abac",                      // overlapping: "aba", "c"
            "caedbdedda"                 // single partition whole string
        };

        for (int t = 0; t < testInputs.length; t++) {
            String s = testInputs[t];
            List<Integer> parts = solver.partitionLabels(s);
            System.out.printf("Test %d: \"%s\" → %s%n",
                              t + 1, s, parts);
        }
    }
}