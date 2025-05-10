import java.util.Stack;

/**
 * Problem 921. Minimum Add to Make Parentheses Valid
 *
 * A parentheses string is valid if and only if:
 *   1. It is the empty string,
 *   2. It can be written as AB (A concatenated with B), where A and B are valid strings, or
 *   3. It can be written as (A), where A is a valid string.
 *
 * You are given a parentheses string s. In one move, you can insert a parenthesis
 * at any position of the string. Return the minimum number of moves required to
 * make s valid.
 *
 * Example 1:
 *   Input: s = "())"
 *   Output: 1
 *
 * Example 2:
 *   Input: s = "((("
 *   Output: 3
 *
 * Intuition:
 *   Each unmatched closing parenthesis ‘)’ requires an opening before it,
 *   and each unmatched opening ‘(’ at the end requires a closing after.
 *   We can use a stack to cancel matching pairs and count the rest.
 *
 * Approach:
 *   1. Let n = s.length(). If n < 2, we need n insertions (either “(” or “)”).
 *   2. Initialize an empty stack and count = 0.
 *   3. For each character par in s:
 *        - If par == '(', push onto stack.
 *        - Else (par == ')'):
 *            • If stack is not empty, pop a matching '('.
 *            • Otherwise increment count (we need an extra '(').
 *   4. After the loop, any '(' left in stack each needs a ')' → add stack.size() to count.
 *   5. Return count.
 *
 * Time Complexity: O(n), we traverse the string once and each char is pushed/popped at most once.
 * Space Complexity: O(n), for the stack in the worst case (all '(').
 */
public class MinAddToMakeValid {
    public int minAddToMakeValid(String s) {
        int n = s.length();
        if (n < 2) return n;

        Stack<Character> stack = new Stack<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            char par = s.charAt(i);
            if (par == '(') {
                stack.push(par);
            } else { // par == ')'
                if (stack.isEmpty()) {
                    // no matching '(', need to insert one
                    count++;
                } else {
                    // cancel one matching '('
                    stack.pop();
                }
            }
        }

        // any remaining '(' each needs a ')'
        count += stack.size();
        return count;
    }

    public static void main(String[] args) {
        MinAddToMakeValid solver = new MinAddToMakeValid();
        String[] tests = {
            "())",    // expect 1
            "(((",    // expect 3
            "()()",   // expect 0
            "()))((", // expect 4: insert '(' at start and two ')' at end
        };
        for (String test : tests) {
            System.out.printf("s = \"%s\" → minAddToMakeValid = %d%n",
                test, solver.minAddToMakeValid(test));
        }
    }
}
