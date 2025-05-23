/**
 * Problem 208. Implement Trie (Prefix Tree)
 *
 * A trie (prefix tree) efficiently stores and retrieves strings by their prefixes.
 * Each node has up to 26 children (for lowercase letters 'a'–'z') and a flag
 * indicating whether a word ends at that node.
 *
 * Intuition:
 *   - To insert, we walk down from the root, creating child nodes as needed,
 *     and mark the final node as the end of a word.
 *   - To search for a full word, we walk down; if any character is missing,
 *     the word isn't present; otherwise we check the end-of-word flag.
 *   - To check a prefix, we similarly walk down and return true if all prefix
 *     characters are found (regardless of the end-of-word flag).
 *
 * Approach:
 *   - TrieNode[] children: length 26, one slot per letter.
 *   - boolean isEnd: marks completion of a word.
 *   - insert(String word):
 *       • node = root
 *       • for each character c in word:
 *           – if !node.containsKey(c), node.put(c, new TrieNode())
 *           – node = node.get(c)
 *       • node.setEnd()
 *   - search(String word):
 *       • node = searchPrefix(word)
 *       • return node != null && node.isEnd()
 *   - startsWith(String prefix):
 *       • return searchPrefix(prefix) != null
 *   - searchPrefix(String s):
 *       • node = root
 *       • for c in s:
 *           – if !node.containsKey(c), return null
 *           – node = node.get(c)
 *       • return node
 *
 * Time Complexity (per operation):
 *   O(L), where L = length of the word or prefix, since we traverse L levels.
 * Space Complexity:
 *   O(N * 26) in the worst case for N inserted characters (each can create up to 26 pointers),
 *   but practically proportional to the total number of characters inserted.
 */
public class Trie {
    private final TrieNode root;

    /** Initialize your Trie here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.containsKey(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.setEnd();
    }

    /** Returns true if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    /** Returns true if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    /** Helper: traverse the trie by the given string. */
    private TrieNode searchPrefix(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if (!node.containsKey(c)) {
                return null;
            }
            node = node.get(c);
        }
        return node;
    }

    // -------------------- TrieNode Definition --------------------
    class TrieNode {
        private final TrieNode[] children;
        private boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }

        public boolean containsKey(char c) {
            return children[c - 'a'] != null;
        }

        public TrieNode get(char c) {
            return children[c - 'a'];
        }

        public void put(char c, TrieNode node) {
            children[c - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Example 1
        System.out.println("Example 1:");
        trie.insert("apple");
        System.out.println("search(\"apple\"): " + trie.search("apple") + " (expected true)");
        System.out.println("search(\"app\"): "   + trie.search("app")   + " (expected false)");
        System.out.println("startsWith(\"app\"): " + trie.startsWith("app") + " (expected true)");
        trie.insert("app");
        System.out.println("search(\"app\"): "   + trie.search("app")   + " (expected true)");
        System.out.println();

        // Additional tests
        System.out.println("Additional Tests:");
        Trie t2 = new Trie();
        String[] words = {"hello", "hell", "heaven", "goodbye"};
        for (String w : words) {
            t2.insert(w);
            System.out.println("Inserted \"" + w + "\"");
        }

        String[] queries = {"hell", "hello", "he", "heav", "good", "goo", "goodby", "bye", ""};
        for (String q : queries) {
            System.out.printf("search(\"%s\"): %b, startsWith(\"%s\"): %b%n",
                q, t2.search(q), q, t2.startsWith(q));
        }
    }
}