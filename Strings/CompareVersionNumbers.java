/**
 * Problem 165. Compare Version Numbers
 *
 * Intuition:
 *   A version string is a sequence of integer revisions separated by dots.
 *   To compare two versions, we compare their corresponding revisions
 *   from left to right. If one version runs out of revisions, we treat
 *   missing ones as 0.
 *
 * Approach:
 *   1. Split each version on "\\." into v1_parts and v2_parts.
 *   2. Use two pointers over these arrays (i, m, n).
 *   3. At each index i, parse v1_parts[i] and v2_parts[i] (or 0 if out of bounds)
 *      into integers v1 and v2.
 *   4. If v1 < v2 return -1; if v1 > v2 return 1.
 *   5. If they’re equal, advance i. When both arrays are exhausted, return 0.
 *
 * Time Complexity: O(M + N), where M and N are lengths of the version strings
 *                  (dominated by split and parse operations).
 * Space Complexity: O(M + N), for the split arrays.
 */
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        int i = 0;
        String[] v1_parts = version1.split("\\.");
        String[] v2_parts = version2.split("\\.");
        int m = v1_parts.length;
        int n = v2_parts.length;
        while (i < m || i < n) {
            int v1 = 0, v2 = 0;
            if (i < m) {
                v1 = Integer.parseInt(v1_parts[i]);
            }
            if (i < n) {
                v2 = Integer.parseInt(v2_parts[i]);
            }
            if (v1 < v2) return -1;
            if (v1 > v2) return 1;
            i++;
        }
        return 0;
    }

    public static void main(String[] args) {
        CompareVersionNumbers solver = new CompareVersionNumbers();

        String[][] tests = {
            {"1.2",      "1.10"},     // expect -1
            {"1.01",     "1.001"},    // expect 0
            {"1.0",      "1.0.0.0"},  // expect 0
            {"0.1",      "1.1"},      // expect -1
            {"1.0.1",    "1"},        // expect 1
            {"7.5.2.4",  "7.5.3"},    // expect -1
            {"7.5.3",    "7.5.2.4"},  // expect 1
            {"1.0",      "1"},        // expect 0
            {"3.4.5",    "3.4"},      // expect 1
            {"3.4",      "3.4.5"},    // expect -1
            {"10.4.6",   "10.4"}      // expect 1
        };
        int[] expected = {
            -1, 0, 0, -1, 1, -1, 1, 0, 1, -1, 1
        };

        for (int k = 0; k < tests.length; k++) {
            String v1 = tests[k][0], v2 = tests[k][1];
            int result = solver.compareVersion(v1, v2);
            System.out.printf("Test %2d: compareVersion(\"%s\",\"%s\") = %2d (expected %2d)%n",
                              k + 1, v1, v2, result, expected[k]);
        }
    }
}