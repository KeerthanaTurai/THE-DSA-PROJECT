import java.util.*;

/**
 * Problem 49. Group Anagrams
 *
 * Given an array of strings strs, group the anagrams together. You can return
 * the answer in any order.
 *
 * Examples:
 *   Input: ["eat","tea","tan","ate","nat","bat"]
 *   Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 *   Input: [""]
 *   Output: [[""]]
 *
 *   Input: ["a"]
 *   Output: [["a"]]
 *
 * Intuition:
 *   Anagrams consist of the same letters in any order. We can canonicalize
 *   each string by counting letter frequencies and using that count as a key
 *   to group anagrams.
 *
 * Approach:
 *   1. For each string s in strs:
 *      a. Compute a 26-length frequency array count for letters 'a' to 'z'.
 *      b. Build a key by concatenating counts with a delimiter, e.g. "1#0#2#...#".
 *      c. Use a HashMap<String, List<String>> to map each key to its list of anagrams.
 *   2. After processing all strings, return the map's values as the grouped anagrams.
 *
 * Time Complexity: O(n * k), where n = number of strings and k = maximum length of a string,
 *                 since counting each string takes O(k).
 * Space Complexity: O(n * k), for storing all strings and the frequency keys.
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            sb.setLength(0);
            for (int c : count) {
                sb.append(c).append('#');
            }
            String key = sb.toString();
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams solver = new GroupAnagrams();

        String[][] testCases = {
            {"eat","tea","tan","ate","nat","bat"},
            {""},
            {"a"},
            {},
            {"abba","baba","bbaa","cd","cd"},
            {"abc","cba","bac","xyz","zyx","zz"}
        };

        for (int i = 0; i < testCases.length; i++) {
            String[] input = testCases[i];
            List<List<String>> groups = solver.groupAnagrams(input);
            System.out.printf("Test %d:%n  Input: %s%n  Output: %s%n%n",
                i + 1, Arrays.toString(input), groups);
        }
    }
}