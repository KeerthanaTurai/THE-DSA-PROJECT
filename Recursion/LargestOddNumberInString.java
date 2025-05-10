/**
 * File: LargestOddNumberInString.java
 *
 * Problem 1903. Largest Odd Number in String
 *
 * You are given a string num, representing a large integer. Return the largest-valued
 * odd integer (as a string) that is a non-empty substring of num, or an empty string
 * "" if no odd integer exists.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 * Input: num = "52"
 * Output: "5"
 *
 * Example 2:
 * Input: num = "4206"
 * Output: ""
 *
 * Example 3:
 * Input: num = "35427"
 * Output: "35427"
 *
 * Intuition:
 *   To maximize the odd-valued substring, we should take as many leading digits
 *   as possible. The suffix of the original string ending at the last odd digit
 *   is the largest odd number we can form.
 *
 * Approach:
 *   1. Let n = num.length().
 *   2. Iterate i from n-1 down to 0:
 *        - If (num.charAt(i) - '0') % 2 != 0, return num.substring(0, i+1).
 *   3. If no odd digit is found, return "".
 *
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(n) for the substring result.
 */
public class LargestOddNumberInString {

    public String largestOddNumber(String num) {
        int n = num.length();
        for (int i = n - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 != 0) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        LargestOddNumberInString sol = new LargestOddNumberInString();
        String[] tests = {
            "52",      // expect "5"
            "4206",    // expect ""
            "35427",   // expect "35427"
            "135790",  // expect "13579"
            "24680",   // expect ""
            "7",       // expect "7"
            ""         // expect ""
        };
        for (String test : tests) {
            String result = sol.largestOddNumber(test);
            System.out.printf("Input: \"%s\" -> Output: \"%s\"%n", test, result);
        }
    }
}
