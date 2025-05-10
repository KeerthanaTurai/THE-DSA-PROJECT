/**
 * Problem 20. Valid Parentheses
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * An input string is valid if:
 *   1. Open brackets must be closed by the same type of brackets.
 *   2. Open brackets must be closed in the correct order.
 *   3. Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 *   Input: s = "()"
 *   Output: true
 *
 * Example 2:
 *   Input: s = "()[]{}"
 *   Output: true
 *
 * Example 3:
 *   Input: s = "(]"
 *   Output: false
 *
 * Intuition:
 *   Use a stack to track opening brackets. Whenever we see a closing bracket,
 *   we pop the top of the stack and check that it matches; if it doesn't, or
 *   the stack is empty, the string is invalid.
 *
 * Approach:
 *   1. Initialize an empty stack.
 *   2. Iterate over each character par in the string:
 *        - If par is '(', '{', or '[', push it onto the stack.
 *        - Otherwise (it's a closing bracket), if the stack is empty return false.
 *          Pop openPar from the stack and check that openPar/par form a matching pair.
 *          If not, return false.
 *   3. After processing all characters, return true if and only if the stack is empty.
 *
 * Time Complexity: O(n), where n = s.length(), since each character is pushed and popped at most once.
 * Space Complexity: O(n), for the stack in the worst case (all opening brackets).
 */
import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char par = s.charAt(i);
            if (par == '(' || par == '{' || par == '[') {
                stack.push(par);
            } else {
                if (stack.isEmpty()) return false;
                char openPar = stack.pop();
                if (openPar == '(' && par == ')') continue;
                else if (openPar == '{' && par == '}') continue;
                else if (openPar == '[' && par == ']') continue;
                else return false;
            }
        }
        return stack.isEmpty();
    }

    // Simple tests
    public static void main(String[] args) {
        ValidParentheses solver = new ValidParentheses();
        String[] tests = {
            "()",
            "()[]{}",
            "(]",
            "([)]",
            "{[]}",
            ""
        };
        for (String test : tests) {
            System.out.printf("isValid(\"%s\") = %b%n", test, solver.isValid(test));
        }
    }
}
