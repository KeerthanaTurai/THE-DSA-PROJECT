/**
 * Problem 12. Integer to Roman
 *
 * Given an integer, convert it to a Roman numeral.
 *
 * Roman numerals use these symbols:
 *   Symbol  Value
 *   I       1
 *   V       5
 *   X       10
 *   L       50
 *   C       100
 *   D       500
 *   M       1000
 *
 * Rules for building numerals:
 * 1. Symbols are placed from largest to smallest, left to right, and summed.
 * 2. To represent 4 and 9 in any decimal place, use the subtractive form:
 *    - 4 = IV, 9 = IX
 *    - 40 = XL, 90 = XC
 *    - 400 = CD, 900 = CM
 * 3. No symbol repeats more than three times in a row; use subtractive form instead.
 *
 * Example 1:
 *   Input: num = 3749
 *   Output: "MMMDCCXLIX"
 *
 * Example 2:
 *   Input: num = 58
 *   Output: "LVIII"
 *
 * Example 3:
 *   Input: num = 1994
 *   Output: "MCMXCIV"
 *
 * Intuition:
 *   Greedily subtract the largest possible Roman value from num, appending
 *   its symbol, until num is reduced to zero.
 *
 * Approach:
 *   1. Prepare parallel arrays values[] and symbols[] of length 13, ordered
 *      from 1000→"M" down to 1→"I", including subtractive pairs (900→"CM", etc.).
 *   2. Iterate index i from 0 while num > 0:
 *        - While values[i] ≤ num:
 *            • append symbols[i] to result
 *            • subtract values[i] from num
 *        - increment i
 *   3. Return the accumulated string.
 *
 * Time Complexity: O(1) since the loop runs at most a fixed number of steps
 *                  (13 values) and each subtraction reduces num.
 * Space Complexity: O(1) extra space (the output string is at most length ~15).
 */
public class IntegerToRoman {

    public String intToRoman(int num) {
        int[] values =   {1000, 900, 500, 400, 100,  90,  50,  40,  10,   9,   5,   4,  1};
        String[] symbols ={"M", "CM","D", "CD","C", "XC","L", "XL","X", "IX","V", "IV","I"};
        StringBuilder res = new StringBuilder();

        for (int i = 0; num > 0; i++) {
            while (values[i] <= num) {
                res.append(symbols[i]);
                num -= values[i];
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman solver = new IntegerToRoman();
        int[] tests = {
            1,     // I
            3,     // III
            4,     // IV
            9,     // IX
            40,    // XL
            58,    // LVIII
            90,    // XC
            1994,  // MCMXCIV
            3749,  // MMMDCCXLIX
            3999   // MMMCMXCIX (max standard)
        };

        for (int num : tests) {
            String roman = solver.intToRoman(num);
            System.out.printf("%4d -> %s%n", num, roman);
        }
    }
}