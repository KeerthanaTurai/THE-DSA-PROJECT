/**
 * Problem 3. Longest Substring Without Repeating Characters
 *
 * Given a string s, find the length of the longest substring without duplicate
 * characters.
 *
 * Examples:
 *   Input: s = "abcabcbb"
 *   Output: 3
 *   Explanation: The answer is "abc", with the length of 3.
 *
 *   Input: s = "bbbbb"
 *   Output: 1
 *   Explanation: The answer is "b", with the length of 1.
 *
 *   Input: s = "pwwkew"
 *   Output: 3
 *   Explanation: The answer is "wke", with the length of 3.
 *
 * Intuition:
 *   Use a sliding-window over the string, expanding the window until a duplicate
 *   character appears. Track the last seen index of each character in a map.
 *   When a duplicate is found inside the current window, move the window's start
 *   just past that character's previous index.
 *
 * Approach:
 *   1. Traverse the string with index i, maintaining:
 *        - start: the start index of the current window
 *        - cur_len: the length of the current non-repeating substring
 *        - max: the maximum seen so far
 *        - map: a HashMap from character to its last seen index
 *   2. For each character x at i:
 *        a. Look up its last index r = map.getOrDefault(x, -1).
 *        b. If r < start, x isn't in the window → extend: cur_len++, update map.
 *        c. Otherwise, x is a duplicate inside the window:
 *             - Update max = max(max, cur_len).
 *             - Move start to r + 1 so the window excludes the old x.
 *             - Set cur_len = i - r.
 *             - Update map with the new index of x.
 *   3. After the loop, update max with cur_len one last time.
 *   4. Return max.
 *
 * Time Complexity: O(n), each character visited once and map lookup is O(1) on average.
 * Space Complexity: O(min(n, m)), where m is the size of the character set (for the map).
 */
import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n < 2) return n;

        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0, cur_len = 0, start = 0;

        for (int i = 0; i < n; i++) {
            char x = s.charAt(i);
            int r = map.getOrDefault(x, -1);
            if (r < start) {
                // x not in current window
                map.put(x, i);
                cur_len++;
            } else {
                // x duplicated in window: shrink from the left
                if (max < cur_len) {
                    max = cur_len;
                }
                cur_len = i - r;
                map.put(x, i);
                start = r + 1;
            }
        }
        if (max < cur_len) {
            max = cur_len;
        }
        return max;
    }

    // Simple tests
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solver =
            new LongestSubstringWithoutRepeatingCharacters();

        String[] tests = {
            "abcabcbb",
            "bbbbb",
            "pwwkew",
            "",
            "au",
            "dvdf"
        };

        for (String test : tests) {
            int result = solver.lengthOfLongestSubstring(test);
            System.out.printf(
                "Input: \"%s\" → Length of longest substring without repeating = %d%n",
                test, result
            );
        }
    }
}
