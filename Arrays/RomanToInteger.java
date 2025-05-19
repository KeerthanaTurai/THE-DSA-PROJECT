/**
 * Problem 13. Roman to Integer
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *   Symbol       Value
 *   I             1
 *   V             5
 *   X             10
 *   L             50
 *   C             100
 *   D             500
 *   M             1000
 *
 * Normally symbols are written from largest to smallest left to right and added together.
 * However, when a smaller value precedes a larger one, it is subtracted. Six instances of
 * subtraction exist:
 *   • I before V (5) or X (10) to make 4 and 9.
 *   • X before L (50) or C (100) to make 40 and 90.
 *   • C before D (500) or M (1000) to make 400 and 900.
 *
 * Given a Roman numeral, convert it to an integer.
 *
 * Example 1:
 *   Input: s = "III"
 *   Output: 3
 *
 * Example 2:
 *   Input: s = "LVIII"
 *   Output: 58
 *   Explanation: L = 50, V = 5, III = 3.
 *
 * Example 3:
 *   Input: s = "MCMXCIV"
 *   Output: 1994
 *   Explanation: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * Intuition:
 *   Traverse the string from right to left, keeping track of the value of the
 *   previous symbol. If the current symbol is at least as large as the previous,
 *   add its value; otherwise, subtract it. This handles the subtraction rule
 *   automatically.
 *
 * Approach:
 *   1. Initialize sum = 0, prev = 0.
 *   2. For each character c from end of s to start:
 *        a. Map c to its integer value cur.
 *        b. If cur >= prev, sum += cur; else sum -= cur.
 *        c. Set prev = cur.
 *   3. Return sum.
 *
 * Time Complexity: O(n), where n = s.length(), since we scan once.
 * Space Complexity: O(1), only fixed extra variables are used.
 */
public class RomanToInteger {

    public int romanToInt(String s) {
        int sum = 0;
        int prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int cur = valueOfRoman(s.charAt(i));
            if (cur >= prev) {
                sum += cur;
            } else {
                sum -= cur;
            }
            prev = cur;
        }
        return sum;
    }

    /** Maps a single Roman character to its integer value. */
    private int valueOfRoman(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:  return 0;
        }
    }

    public static void main(String[] args) {
        RomanToInteger solver = new RomanToInteger();

        String[] tests = {
            "",           // empty string → 0
            "I",          // 1
            "II",         // 2
            "IV",         // 4
            "IX",         // 9
            "LVIII",      // 58
            "XL",         // 40
            "XC",         // 90
            "CD",         // 400
            "CM",         // 900
            "MCMXCIV",    // 1994
            "MMXXIII",    // 2023
            "MMMCMXCIX"   // 3999 (largest standard)
        };

        for (int i = 0; i < tests.length; i++) {
            String s = tests[i];
            int result = solver.romanToInt(s);
            System.out.printf("Test %2d: \"%s\" -> %d%n", i + 1, s, result);
        }
    }
}
