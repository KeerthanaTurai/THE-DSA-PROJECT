import java.util.*;

/**
 * Problem 76. Minimum Window Substring
 *
 * Intuition:
 *   We need the smallest substring of s that contains all characters of t
 *   (including duplicates). We can use the two-pointer (sliding window)
 *   technique to expand a window until it contains all needed chars, then
 *   contract it to try to find a smaller valid window.
 *
 * Approach:
 *   1. If s is shorter than t, immediately return "".
 *   2. Build an array need[128] to count required frequencies of each char in t.
 *   3. Count how many distinct characters in t (required).
 *   4. Use two pointers l and r to represent the window [l, r) in s.
 *      Maintain window[128] for counts in the current window, and a counter
 *      formed for how many characters have met their required frequency.
 *   5. Expand r:
 *        - Add s.charAt(r) to window.
 *        - If window[c] == need[c], increment formed.
 *   6. While formed == required:
 *        - Update answer if current window is smaller.
 *        - Remove s.charAt(l) from window, and if window[c] < need[c],
 *          decrement formed. Increment l to contract.
 *   7. Continue until r reaches the end.
 *   8. Return the smallest window found, or "" if none.
 *
 * Time Complexity: O(m + n), where m = s.length(), n = t.length().
 *   - We scan s with r once, and each character is added/removed at most once.
 *
 * Space Complexity: O(1) (128-sized arrays for ASCII).
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) return "";

        // 1) count frequencies in t
        int[] need = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        // number of distinct chars we actually need
        int required = 0;
        for (int f : need) {
            if (f > 0) required++;
        }

        // 2) sliding window
        int[] window = new int[128];
        int formed = 0;     // how many chars have met their required count
        int l = 0, r = 0;   // [l, r) is our window
        int minLen = Integer.MAX_VALUE;
        int minL   = 0;

        while (r < m) {
            char c = s.charAt(r);
            window[c]++;

            // if this char is needed, and we’ve hit exactly the needed count, increment 'formed'
            if (need[c] > 0 && window[c] == need[c]) {
                formed++;
            }
            r++;

            // when all required chars are formed, try to shrink from the left
            while (l < r && formed == required) {
                if (r - l < minLen) {
                    minLen = r - l;
                    minL   = l;
                }
                char cl = s.charAt(l);
                window[cl]--;
                // if we dropped below the needed count for a required char, we’re no longer "formed"
                if (need[cl] > 0 && window[cl] < need[cl]) {
                    formed--;
                }
                l++;
            }
        }

        return (minLen == Integer.MAX_VALUE)
             ? ""
             : s.substring(minL, minL + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solver = new MinimumWindowSubstring();

        class Test {
            String s, t, expected;
            Test(String s, String t, String e) { this.s = s; this.t = t; this.expected = e; }
        }

        List<Test> tests = Arrays.asList(
            new Test("ADOBECODEBANC", "ABC", "BANC"),   // example 1
            new Test("a",           "a",   "a"),      // example 2
            new Test("a",           "aa",  ""),       // example 3
            // additional edge cases
            new Test("ABAACBAB",     "ABC", "ACB"),    // overlapping choices
            new Test("AAAB",        "AAB", "AAB"),    // duplicates in t
            new Test("XYZ",         "Z",   "Z"),      // single char
            new Test("XYZ",         "W",   "")        // no match
        );

        for (int i = 0; i < tests.size(); i++) {
            Test tc = tests.get(i);
            String result = solver.minWindow(tc.s, tc.t);
            System.out.printf(
                "Test %d: s=\"%s\", t=\"%s\" → result=\"%s\", expected=\"%s\"%n",
                i + 1, tc.s, tc.t, result, tc.expected
            );
        }
    }
}