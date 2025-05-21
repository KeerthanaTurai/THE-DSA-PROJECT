import java.util.*;

/**
 * Problem 937. Reorder Data in Log Files
 *
 * Given an array of logs. Each log is a space-delimited string of words,
 * where the first word is the identifier.
 *
 * - Letter-logs: All words (except the identifier) consist of lowercase letters.
 * - Digit-logs: All words (except the identifier) consist of digits.
 *
 * We must reorder the logs so that:
 *   1. All letter-logs come before any digit-log.
 *   2. Letter-logs are sorted lexicographically by content, and ties by identifier.
 *   3. Digit-logs remain in their original order.
 *
 * Intuition:
 *   We can define a custom Comparator<String> that:
 *     - Splits each log into [identifier, rest].
 *     - Checks whether rest starts with a digit.
 *     - If both are letter-logs, compares (rest, id).
 *     - If one is letter and one is digit, letter comes first.
 *     - If both are digit-logs, they compare equal to preserve original order.
 *
 * Approach:
 *   1. Build the Comparator<String> comp as described.
 *   2. Call Arrays.sort(logs, comp).
 *   3. Return the sorted logs.
 *
 * Time Complexity: O(N log N * M), where N = number of logs, M = average length of a log,
 *   since comparison may examine up to M characters, and sorting is O(N log N).
 * Space Complexity: O(log N) due to sorting stack usage (plus O(1) extra besides output).
 */
public class ReorderLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> comp = new Comparator<>() {
            @Override
            public int compare(String log1, String log2) {
                // Split into two parts: identifier and the rest
                String[] split1 = log1.split(" ", 2);
                String[] split2 = log2.split(" ", 2);

                // Determine if they are digit-logs
                boolean log1_isDigit = Character.isDigit(split1[1].charAt(0));
                boolean log2_isDigit = Character.isDigit(split2[1].charAt(0));

                // Both letter-logs: compare by content, then by identifier
                if (!log1_isDigit && !log2_isDigit) {
                    int cmp = split1[1].compareTo(split2[1]);
                    if (cmp != 0) return cmp;
                    return split1[0].compareTo(split2[0]);
                }

                // One is letter-log, the other is digit-log: letter-log comes first
                if (!log1_isDigit && log2_isDigit) {
                    return -1;
                } else if (log1_isDigit && !log2_isDigit) {
                    return 1;
                }

                // Both digit-logs: keep original order
                return 0;
            }
        };

        Arrays.sort(logs, comp);
        return logs;
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        ReorderLogFiles solver = new ReorderLogFiles();

        String[][] testCases = {
            // Example 1
            {
                "dig1 8 1 5 1",
                "let1 art can",
                "dig2 3 6",
                "let2 own kit dig",
                "let3 art zero"
            },
            // Example 2
            {
                "a1 9 2 3 1",
                "g1 act car",
                "zo4 4 7",
                "ab1 off key dog",
                "a8 act zoo"
            },
            // All digit-logs: should remain in original order
            {
                "d1 9 2",
                "d2 5 6",
                "d3 1 0"
            },
            // All letter-logs: sorted by content then identifier
            {
                "let1 abc def",
                "let2 abc def",
                "let0 abc",
                "let3 abc dee"
            },
            // Mixed with identical contents
            {
                "x1 aa bb",
                "x2 aa bb",
                "d1 1 2 3",
                "x0 aa bb"
            }
        };

        for (int i = 0; i < testCases.length; i++) {
            String[] logs = Arrays.copyOf(testCases[i], testCases[i].length);
            String[] result = solver.reorderLogFiles(logs);
            System.out.printf("Test %d result: %s%n", i + 1, Arrays.toString(result));
        }
    }
}