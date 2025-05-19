import java.util.*;

/**
 * Problem 819. Most Common Word
 *
 * Given a string paragraph and a string array of the banned words banned,
 * return the most frequent word that is not banned. It is guaranteed there
 * is at least one word that is not banned, and that the answer is unique.
 *
 * Intuition:
 *   Normalize the paragraph to lowercase, split on any non-letter characters
 *   to extract words, then count frequencies while skipping banned words.
 *
 * Approach:
 *   1. Build a HashSet<String> ban from banned[] for O(1) lookups.
 *   2. Lowercase paragraph and split on regex [^a-z]+ to get words[].
 *   3. Use a HashMap<String,Integer> freq to count occurrences:
 *        – Initialize freq for each banned word to -1, so they stay excluded.
 *        – For each word s in words[], if s is non-empty and not in ban,
 *          increment freq[s].
 *   4. Scan freq entries to find the key with the largest count.
 *
 * Time Complexity: O(N + B), where N = paragraph.length, B = banned.length.
 *                  Splitting and scanning words is O(N), map operations are O(1) each.
 * Space Complexity: O(W + B), where W = number of unique words in paragraph.
 */
public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        // 1) Build banned set
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        // 2) Split paragraph into words (lowercase, non-letters as delimiters)
        String[] words = paragraph
            .toLowerCase()
            .split("[^a-z]+");

        // 3) Count frequencies, initializing banned words to -1
        Map<String, Integer> freq = new HashMap<>();
        for (String s : banned) {
            freq.put(s, -1);
        }
        for (String s : words) {
            if (s.length() > 0 && !ban.contains(s)) {
                freq.put(s, freq.getOrDefault(s, 0) + 1);
            }
        }

        // 4) Find the word with the highest non-banned frequency
        int max_count = 0;
        String result = "";
        int count;
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            count = entry.getValue();
            if (count > max_count) {
                max_count = count;
                result = entry.getKey();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MostCommonWord solver = new MostCommonWord();

        // Example 1
        String paragraph1 = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned1 = {"hit"};
        System.out.println(solver.mostCommonWord(paragraph1, banned1));
        // Expected output: "ball"

        // Example 2
        String paragraph2 = "a.";
        String[] banned2 = {};
        System.out.println(solver.mostCommonWord(paragraph2, banned2));
        // Expected output: "a"

        // Additional tests
        String paragraph3 = "Hello, hello, HELLO!! world; world.";
        String[] banned3 = {"hello"};
        System.out.println(solver.mostCommonWord(paragraph3, banned3));
        // Expected output: "world"

        String paragraph4 = "Test. test? TeSt!";
        String[] banned4 = {};
        System.out.println(solver.mostCommonWord(paragraph4, banned4));
        // Expected output: "test"
    }
}