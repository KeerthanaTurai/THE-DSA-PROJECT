import java.util.*;

/**
 * Problem 127. Word Ladder
 *
 * Intuition:
 *   We need the shortest sequence of one-letter transformations from beginWord
 *   to endWord using only words from wordList. This is a classic shortest-path
 *   problem on an implicit graph where each word is a node and edges connect
 *   words that differ by exactly one letter. BFS finds the shortest path in O(N·L·26)
 *   time, where N is the number of words and L is word length.
 *
 * Approach:
 *   1. Put all words from wordList into a HashSet<String> for O(1) lookups.
 *   2. If endWord is not in the set, return 0 immediately.
 *   3. Initialize a Queue<Pair> for BFS, storing (currentWord, currentLevel).
 *   4. Start by offering (beginWord, 1) and removing beginWord from the set.
 *   5. While the queue is not empty:
 *        a. Poll (word, level).
 *        b. If word.equals(endWord), return level.
 *        c. For each position i in word:
 *             - For each letter c from 'a' to 'z':
 *                 • Change word[i] to c → newWord.
 *                 • If newWord is in the set, remove it and offer (newWord, level+1).
 *        d. Restore word[i] before moving on.
 *   6. If BFS completes without finding endWord, return 0.
 *
 * Time Complexity: O(N * L * 26) in the worst case, where N = wordList.size(),
 *                  L = length of each word (<=10). Each word is visited once
 *                  and for each we try 26 substitutions per position.
 * Space Complexity: O(N * L) for the HashSet and the BFS queue.
 */


public class WordLadder {
    public static class Pair {
        String first;
        int second;
        Pair(String _first, int _second) {
            this.first = _first;
            this.second = _second;
        }
    }
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(beginWord, 1));
        // Remove beginWord to avoid revisiting
        words.remove(beginWord);

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            String word = cur.first;
            int level = cur.second;

            if (word.equals(endWord)) {
                return level;
            }

            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char original = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String neighbor = new String(chars);
                    if (words.contains(neighbor)) {
                        words.remove(neighbor);
                        queue.offer(new Pair(neighbor, level + 1));
                    }
                }
                chars[i] = original;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        class Test {
            String begin, end;
            List<String> dict;
            int expected;
            Test(String b, String e, String[] d, int ex) {
                begin = b;
                end = e;
                dict = Arrays.asList(d);
                expected = ex;
            }
        }

        List<Test> tests = List.of(
            new Test("hit", "cog",
                     new String[]{"hot","dot","dog","lot","log","cog"}, 5),
            new Test("hit", "cog",
                     new String[]{"hot","dot","dog","lot","log"}, 0),
            new Test("a", "c",
                     new String[]{"a","b","c"}, 2),
            new Test("ab", "cd",
                     new String[]{"ab","ac","ad","bd","cd"}, 3),
            new Test("abc", "abc",
                     new String[]{"abc"}, 1)
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int result = ladderLength(t.begin, t.end, t.dict);
            System.out.printf("Test %d: begin=\"%s\", end=\"%s\", dict=%s → %d (expected %d)%n",
                              i + 1, t.begin, t.end, t.dict, result, t.expected);
        }
    }
}