/**
 * Problem 28. Find the Index of the First Occurrence in a String
 *
 * Given two strings needle and haystack, return the index of the first occurrence
 * of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Examples:
 *   Input: haystack = "sadbutsad", needle = "sad"
 *   Output: 0
 *   Explanation: "sad" occurs at index 0 and 6; first occurrence is 0.
 *
 *   Input: haystack = "leetcode", needle = "leeto"
 *   Output: -1
 *   Explanation: "leeto" is not a substring of "leetcode".
 *
 * Edge cases:
 *   - If needle is empty, return 0.
 *   - If haystack is empty but needle is non-empty, return -1.
 *
 * Intuition:
 *   Slide a window of length needle.length() over haystack. At each position,
 *   compare characters one by one. If all match, we've found the first occurrence.
 *
 * Approach:
 *   1. Let m = haystack.length(), n = needle.length().
 *   2. If n == 0, return 0 immediately.
 *   3. For i from 0 to m - n:
 *        a. If haystack.charAt(i) == needle.charAt(0):
 *             - Try matching the next n characters:
 *                  • Keep a pointer j over needle.
 *                  • If mismatch, break and continue outer loop.
 *             - If j reaches n, return i.
 *   4. If no match found, return -1.
 *
 * Time Complexity: O(m * n) in the worst case, where m = haystack.length(),
 *                  n = needle.length().
 * Space Complexity: O(1), only a few integer variables used.
 */
public class StrStr {

    /**
     * Returns the index of the first occurrence of needle in haystack,
     * or -1 if needle is not part of haystack.
     */
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        // Edge case: empty needle
        if (n == 0) return 0;
        // Slide window
        for (int i = 0; i <= m - n; i++) {
            // If first character matches, check the rest
            if (haystack.charAt(i) == needle.charAt(0)) {
                int index = i;
                int j = 1;
                // Compare subsequent characters
                while (j < n && haystack.charAt(i + j) == needle.charAt(j)) {
                    j++;
                }
                // If we matched all of needle, return start index
                if (j == n) {
                    return index;
                }
            }
        }
        // No match found
        return -1;
    }

    /**
     * Runs a suite of test cases to validate strStr.
     */
    public static void main(String[] args) {
        StrStr solver = new StrStr();

        class Test {
            String haystack, needle;
            int expected;
            Test(String h, String n, int e) { haystack = h; needle = n; expected = e; }
        }

        Test[] tests = new Test[] {
            new Test("sadbutsad", "sad",      0),
            new Test("leetcode",   "leeto",  -1),
            new Test("hello",      "ll",      2),
            new Test("aaaaa",      "bba",    -1),
            new Test("",           "",        0),
            new Test("abc",        "",        0),
            new Test("",           "a",      -1),
            new Test("mississippi","issi",    1),
            new Test("abc",        "c",       2),
            new Test("aaa",        "aa",      0),
            new Test("abcde",      "e",       4)
        };

        for (int i = 0; i < tests.length; i++) {
            Test t = tests[i];
            int result = solver.strStr(t.haystack, t.needle);
            System.out.printf(
                "Test %2d: haystack=\"%s\", needle=\"%s\" → result = %2d (expected %2d)%n",
                i + 1, t.haystack, t.needle, result, t.expected
            );
        }
    }
}