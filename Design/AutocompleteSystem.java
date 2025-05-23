import java.util.*;

/**
 * Problem 642. Design Search Autocomplete System
 *
 * We want to support typing characters one by one, and after each character
 * (except the terminating '#'), return the top 3 most frequently-typed
 * historical sentences with the current prefix. Ties in frequency are broken
 * by ASCII order of the sentences.
 *
 * Intuition:
 *   A Trie lets us efficiently track all sentences sharing a given prefix.
 *   At each node we also keep a map of sentence→frequency for all sentences
 *   passing through that node. When the user inputs a new character, we
 *   descend the Trie (or detect that no further matches exist) and then
 *   select the top 3 sentences by frequency (and ASCII order tie-breaker)
 *   using a min-heap of size up to 3.
 *
 * Approach:
 *   - Build a TrieNode with:
 *       • children: Map<Character,TrieNode>
 *       • sentences: Map<String,Integer> storing counts of all sentences
 *         that use this node in their path.
 *   - On initialization, insert each historical sentence into the Trie,
 *     incrementing the sentence count at every node along its path.
 *   - Maintain a StringBuilder currSentence and a pointer curNode into
 *     the Trie. Also have a dummy `dead` node for no-match states.
 *   - input(char c):
 *       • If c == '#': finalize the current sentence by reinserting it
 *         with count+1, reset currSentence and curNode to root, and return [].
 *       • Otherwise append c, if curNode has no child c then switch to
 *         `dead` (all future inputs until '#' will return empty). Else
 *         move curNode = curNode.children.get(c).
 *       • At curNode, gather its sentences.keySet(), push into a min-heap
 *         ordered by (freq ascending, sentence descending) so that the heap
 *         holds at most 3 best candidates. Pop them into a list, reverse,
 *         and return.
 *
 * Time Complexity:
 *   - Construction: O(N * L), where N = number of initial sentences,
 *     L = average sentence length.
 *   - insert (on '#'): O(L) to walk/increment the trie.
 *   - input non-‘#’: O(1) to descend + O(M log 3) to maintain a size-3 heap,
 *     where M = number of distinct sentences sharing the prefix.
 *
 * Space Complexity:
 *   O(TotalChars + TotalSentences) for the trie nodes and per-node maps.
 */
public class AutocompleteSystem {
    // A node in our Trie
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        Map<String, Integer> sentences = new HashMap<>();
    }

    private final TrieNode root;
    private TrieNode curNode;
    private final TrieNode dead;        // sink node when prefix fails
    private final StringBuilder currSB;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        // Build initial history
        for (int i = 0; i < sentences.length; i++) {
            insertSentence(sentences[i], times[i]);
        }
        currSB = new StringBuilder();
        curNode = root;
        dead = new TrieNode();  // has no children and no sentences
    }

    /**
     * Processes the next character c.
     * If c == '#', finalizes the current sentence and returns [].
     * Else, returns top 3 hot sentences with the current prefix.
     */
    public List<String> input(char c) {
        if (c == '#') {
            String completed = currSB.toString();
            insertSentence(completed, 1);
            currSB.setLength(0);
            curNode = root;
            return Collections.emptyList();
        }

        currSB.append(c);
        // Descend or dead-end
        if (curNode != dead && curNode.children.containsKey(c)) {
            curNode = curNode.children.get(c);
        } else {
            curNode = dead;
            return Collections.emptyList();
        }

        // Gather top 3 from curNode.sentences
        PriorityQueue<String> heap = new PriorityQueue<>(
            (a, b) -> {
                int fa = curNode.sentences.get(a);
                int fb = curNode.sentences.get(b);
                if (fa != fb) return fa - fb;          // lower freq first
                return b.compareTo(a);                 // tie: reverse ASCII
            }
        );
        for (String s : curNode.sentences.keySet()) {
            heap.offer(s);
            if (heap.size() > 3) heap.poll();
        }
        List<String> result = new ArrayList<>();
        while (!heap.isEmpty()) result.add(heap.poll());
        Collections.reverse(result);
        return result;
    }

    // Inserts sentence into the Trie, incrementing its count by cnt
    private void insertSentence(String sentence, int cnt) {
        TrieNode node = root;
        for (char c : sentence.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.sentences.put(
                sentence,
                node.sentences.getOrDefault(sentence, 0) + cnt
            );
        }
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        String[] sentences = {
            "i love you", "island", "iroman", "i love leetcode"
        };
        int[] times = {5, 3, 2, 2};
        AutocompleteSystem sys = new AutocompleteSystem(sentences, times);

        System.out.println(sys.input('i')); // [i love you, island, i love leetcode]
        System.out.println(sys.input(' ')); // [i love you, i love leetcode]
        System.out.println(sys.input('a')); // []
        System.out.println(sys.input('#')); // []

        // After finishing "i a", its count is now 1
        System.out.println(sys.input('i')); // [i love you, island, i love leetcode]
        System.out.println(sys.input(' ')); // [i love you, i love leetcode, i a]
        System.out.println(sys.input('a')); // [i a]
        System.out.println(sys.input('#')); // []
    }
}
