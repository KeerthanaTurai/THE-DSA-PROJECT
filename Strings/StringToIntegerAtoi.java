/**
 * Problem 8. String to Integer (atoi)
 *
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit
 * signed integer (similar to C/C++’s atoi).
 *
 * The algorithm is as follows:
 *   1. Read in and ignore any leading whitespace.
 *   2. Check if the next character is '-' or '+'. Read it in if present to
 *      determine the sign. Assume positive if neither is present.
 *   3. Read in next the characters until the next non-digit character or the
 *      end of the input is reached. Convert these digits into an integer.
 *      If no digits were read, the integer is 0.
 *   4. Clamp the integer so that it remains within the 32-bit signed integer
 *      range [−2^31, 2^31 − 1]. Specifically:
 *        • If the integer is less than −2^31, return −2^31.
 *        • If the integer is greater than 2^31 − 1, return 2^31 − 1.
 *   5. Return the integer as the final result.
 *
 * Intuition:
 *   Walk through the string once, maintaining a pointer. Skip spaces, detect
 *   sign, accumulate digits into a running sum, and check for overflow at each
 *   step.
 *
 * Approach:
 *   - Use an index i to traverse the string of length n.
 *   - Skip leading spaces.
 *   - Check for optional '+' or '-' to set sign (neg = ±1).
 *   - While the current character is a digit:
 *       • Compute d = c - '0'.
 *       • Before adding, check if sum > INT_MAX/10 or sum == INT_MAX/10 and
 *         d > INT_MAX%10 → clamp and return INT_MAX or INT_MIN.
 *       • Otherwise sum = sum * 10 + d.
 *       • Advance i.
 *   - Return sum * neg.
 *
 * Time Complexity: O(n), n = s.length(), since we traverse at most once.
 * Space Complexity: O(1), only a few variables are used.
 */
public class StringToIntegerAtoi {
    public int myAtoi(String s) {
        int n = s.length(), i = 0, neg = 1, sum = 0;
        char c;

        // 1) Skip leading spaces
        while (i < n && (c = s.charAt(i)) == ' ') {
            i++;
        }

        // 2) Optional sign
        if (i < n && ((c = s.charAt(i)) == '+' || c == '-')) {
            neg = (c == '-') ? -1 : 1;
            i++;
        }

        // 3) Parse digits
        while (i < n && Character.isDigit(c = s.charAt(i))) {
            int d = c - '0';
            // 4) Overflow check
            if (sum > Integer.MAX_VALUE / 10 ||
                (sum == Integer.MAX_VALUE / 10 && d > Integer.MAX_VALUE % 10)) {
                return (neg == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            sum = sum * 10 + d;
            i++;
        }

        return sum * neg;
    }

    public static void main(String[] args) {
        StringToIntegerAtoi solver = new StringToIntegerAtoi();
        String[] tests = {
            "42",
            "   -042",
            "1337c0d3",
            "0-1",
            "words and 987",
            "",
            "+1",
            "-91283472332",  // underflow
            "2147483647",    // INT_MAX
            "2147483648",    // overflow → INT_MAX
            "-2147483648",   // INT_MIN
            "-2147483649",   // underflow → INT_MIN
            "000000000001234", // leading zeros
            "  +000123abc"
        };

        for (String test : tests) {
            int result = solver.myAtoi(test);
            System.out.printf("Input: \"%s\" → Output: %d%n", test, result);
        }
    }
}
