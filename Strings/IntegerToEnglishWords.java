/**
 * Problem 273. Integer to English Words
 *
 * Convert a non-negative integer num to its English words representation.
 *
 * Example 1:
 *   Input: num = 123
 *   Output: "One Hundred Twenty Three"
 *
 * Example 2:
 *   Input: num = 12345
 *   Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 *   Input: num = 1234567
 *   Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Intuition:
 *   We can process the number in chunks of three digits (hundreds, tens, units),
 *   converting each chunk to words, and then append the appropriate scale
 *   (thousand, million, billion) as we move through the number.
 *
 * Approach:
 *   1. Handle zero as a special case.
 *   2. Repeatedly take num % 1000 to get the last three digits, convert those
 *      to words, and prepend them along with the correct scale word.
 *   3. Divide num by 1000 and move to the next scale (thousand → million → billion).
 *   4. To convert a three-digit chunk:
 *        a. If >= 100, handle the "X Hundred" part.
 *        b. If the remainder is 10–19, use the below-twenty lookup.
 *        c. Otherwise handle tens (20,30,…,90) and then units.
 *   5. Trim and return the built string.
 *
 * Time Complexity: O(log N), where N = num, because we process O(log₁₀ N) digits,
 *                  grouping by threes → O((log N)/3) chunks.
 * Space Complexity: O(1) extra beyond the output (lookup arrays and a few variables).
 */
public class IntegerToEnglishWords {

    private static final String[] BELOW_TWENTY = {
        "Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine",
        "Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen",
        "Seventeen","Eighteen","Nineteen"
    };

    private static final String[] TENS = {
        "", "", "Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"
    };

    private static final String[] THOUSANDS = {
        "", "Thousand", "Million", "Billion"
    };

    /**
     * Converts num to its English words representation.
     */
    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        StringBuilder words = new StringBuilder();
        int scale = 0;

        while (num > 0) {
            int chunk = num % 1000;
            if (chunk != 0) {
                String chunkWords = threeDigitToWords(chunk);
                if (words.length() > 0) {
                    words.insert(0, " ");
                }
                if (!THOUSANDS[scale].isEmpty()) {
                    words.insert(0, THOUSANDS[scale] + " ");
                }
                words.insert(0, chunkWords);
            }
            num /= 1000;
            scale++;
        }

        return words.toString();
    }

    /**
     * Converts a number in [0,999] to words.
     */
    private String threeDigitToWords(int num) {
        StringBuilder sb = new StringBuilder();

        if (num >= 100) {
            int hundreds = num / 100;
            sb.append(BELOW_TWENTY[hundreds]).append(" Hundred");
            num %= 100;
        }

        if (num >= 20) {
            if (sb.length() > 0) sb.append(" ");
            int t = num / 10;
            sb.append(TENS[t]);
            num %= 10;
        } else if (num >= 1 && num < 20) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(BELOW_TWENTY[num]);
            return sb.toString();  // done with teen or below
        }

        if (num > 0) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(BELOW_TWENTY[num]);
        }

        return sb.toString();
    }

    // -------------------- Test Harness --------------------

    public static void main(String[] args) {
        IntegerToEnglishWords solver = new IntegerToEnglishWords();

        int[] tests = {
            0,
            5,
            13,
            20,
            45,
            100,
            123,
            508,
            1005,
            12345,
            1234567,
            1000000,
            1000000000,
            2147483647  // INT_MAX
        };

        for (int num : tests) {
            String words = solver.numberToWords(num);
            System.out.printf("%d -> \"%s\"%n", num, words);
        }
    }
}