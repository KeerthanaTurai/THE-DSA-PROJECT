/**
 * Problem 387. First Unique Character in a String
 *
 * Given a string s, find the first non-repeating character in it and return
 * its index. If it does not exist, return -1.
 *
 * Intuition:
 *   We need to know how many times each character appears, then scan again
 *   to find the first with frequency 1.
 *
 * Approach:
 *   1. Traverse the string once to build a frequency array cnt[26].
 *   2. Traverse the string a second time; at each index i, if cnt[s.charAt(i)-'a'] == 1,
 *      return i.
 *   3. If no unique character is found, return -1.
 *
 * Time Complexity: O(n), where n = s.length(), since we scan the string twice.
 * Space Complexity: O(1), the frequency array is fixed size 26.
 */
public class FirstUniqueCharacterInString {

    /**
     * Returns the index of the first non-repeating character in s, or -1.
     */
    public int firstUniqChar(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        // 1) Count frequencies
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        // 2) Find first index with frequency == 1
        for (int i = 0; i < n; i++) {
            if (cnt[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /** Simple tests covering typical and edge cases. */
    public static void main(String[] args) {
        FirstUniqueCharacterInString solver = new FirstUniqueCharacterInString();

        String[] tests = {
            "leetcode",      // expect 0 ('l')
            "loveleetcode",  // expect 2 ('v')
            "aabb",          // expect -1 (no unique)
            "z",             // expect 0 (single character)
            "zz",            // expect -1
            "abcabcx",       // expect 6 ('x')
            "abacabad",      // expect 6 ('d')
            "ababcd",        // expect 4 ('c')
            "a",             // expect 0
            "aaabb",         // expect -1
        };

        for (int i = 0; i < tests.length; i++) {
            String s = tests[i];
            int result = solver.firstUniqChar(s);
            System.out.printf("Test %2d: \"%s\" → %d%n", i + 1, s, result);
        }
    }
}