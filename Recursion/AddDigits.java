/**
 * Problem 258. Add Digits
 *
 * Given an integer num, repeatedly add all its digits until the result has
 * only one digit, and return it.
 *
 * Example 1:
 * Input: num = 38
 * Output: 2
 * Explanation:
 *   38 → 3 + 8 = 11
 *   11 → 1 + 1 = 2
 *
 * Example 2:
 * Input: num = 0
 * Output: 0
 *
 * Intuition:
 *   Repeatedly summing the digits of a number until one digit remains is
 *   like computing its “digital root.” You can do this by summing digits
 *   recursively (or iteratively) until the value is < 10.
 *
 * Approach:
 *   1. Base case: if num is between 0 and 9 inclusive, return num.
 *   2. Otherwise, sum its digits by extracting each digit with num % 10
 *      and dividing num by 10.
 *   3. Call addDigits on the sum.
 *
 * Time Complexity: O(d * k) where d is the number of digits and k is the
 *                  number of times you need to sum until one digit remains.
 *                  In practice this is very small.
 * Space Complexity: O(k) recursion depth (or O(1) if you convert to an iterative loop).
 */
public class AddDigits {

    public int addDigits(int num) {
        if (num >= 0 && num < 10) {
            return num;
        }
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return addDigits(sum);
    }

    public static void main(String[] args) {
        AddDigits solver = new AddDigits();
        int[] tests = {38, 0, 9, 10, 11111};

        System.out.println("Add Digits Results:");
        for (int n : tests) {
            System.out.printf("addDigits(%d) = %d%n", n, solver.addDigits(n));
        }
    }
}
