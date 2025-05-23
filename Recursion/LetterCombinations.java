import java.util.*;

/**
 * Problem 17. Letter Combinations of a Phone Number
 *
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent. Return the answer in any order.
 *
 * Intuition:
 *   Each digit maps to a set of letters (like on a telephone keypad). We need
 *   all combinations where we pick one letter per digit in sequence. This
 *   naturally leads to a DFS/backtracking approach.
 *
 * Approach:
 *   1. Build a map from char digit → list of corresponding letters.
 *   2. If the input is empty, return an empty list.
 *   3. Use a backtrack(index, currentBuilder) function:
 *        - If index == digits.length(), add the built string to results.
 *        - Otherwise, for each letter mapped from digits.charAt(index):
 *            • append the letter,
 *            • recurse to index+1,
 *            • remove the letter (backtrack).
 *
 * Time Complexity:
 *   O(4^n · n), where n = digits.length(). Each position can branch up to 4
 *   ways (for digit '7' or '9'), and building each string costs O(n).
 *
 * Space Complexity:
 *   O(n) for the recursion stack and O(4^n · n) total for storing the results.
 */
public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        Map<Character,List<String>> letters = Map.of(
            '2', List.of("a","b","c"),
            '3', List.of("d","e","f"),
            '4', List.of("g","h","i"),
            '5', List.of("j","k","l"),
            '6', List.of("m","n","o"),
            '7', List.of("p","q","r","s"),
            '8', List.of("t","u","v"),
            '9', List.of("w","x","y","z")
        );
        int n = digits.length();
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        StringBuilder sb = new StringBuilder(n);
        backtrack(0, digits, letters, sb, res);
        return res;
    }

    private void backtrack(int i, String digits,
                           Map<Character,List<String>> letters,
                           StringBuilder sb, List<String> res) {
        if (i == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char d = digits.charAt(i);
        for (String letter : letters.get(d)) {
            sb.append(letter);
            backtrack(i + 1, digits, letters, sb, res);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinations solver = new LetterCombinations();
        String[] testInputs = {
            "",       // expect []
            "2",      // expect [a,b,c]
            "23",     // expect [ad,ae,af,bd,be,bf,cd,ce,cf]
            "79",     // expect combinations of pqrs × wxyz
            "234"     // a deeper test
        };

        for (int i = 0; i < testInputs.length; i++) {
            String digits = testInputs[i];
            List<String> combos = solver.letterCombinations(digits);
            System.out.printf("Test %d: digits=\"%s\" → %s%n",
                              i + 1, digits, combos);
        }
    }
}