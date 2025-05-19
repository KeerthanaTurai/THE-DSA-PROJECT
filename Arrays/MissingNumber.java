import java.util.*;
/**
 * Problem 268. Missing Number
 *
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 * Example 1:
 *   Input: nums = [3,0,1]
 *   Output: 2
 *
 * Example 2:
 *   Input: nums = [0,1]
 *   Output: 2
 *
 * Example 3:
 *   Input: nums = [9,6,4,2,3,5,7,0,1]
 *   Output: 8
 *
 * Intuition:
 *   The numbers from 0 to n sum to n*(n+1)/2. If we subtract the sum of the
 *   given array from that total, the result is the missing number.
 *
 * Approach:
 *   1. Let n = nums.length.
 *   2. Compute sum_n = n * (n + 1) / 2.
 *   3. Compute sum_arr = sum of all elements in nums.
 *   4. The missing number is sum_n - sum_arr.
 *
 * Time Complexity: O(n), one pass to sum the array.
 * Space Complexity: O(1), only a few integer variables used.
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum_n = n * (n + 1) / 2;
        int sum_arr = 0;
        for (int i = 0; i < n; i++) {
            sum_arr += nums[i];
        }
        return sum_n - sum_arr;
    }

    // Main method with edge-case and example tests
    public static void main(String[] args) {
        MissingNumber solver = new MissingNumber();

        int[][] testCases = {
            {3, 0, 1},                  // expect 2
            {0, 1},                     // expect 2
            {9,6,4,2,3,5,7,0,1},        // expect 8
            {0},                        // n=1, expect 1
            {1},                        // n=1, expect 0
            {2,0,1,3},                  // n=4, expect 4
            {5,4,3,2,1,0},              // n=6, expect 6
            {1,2,3,4,5,6,7,8,9,10},     // n=10, expect 0
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int missing = solver.missingNumber(nums);
            System.out.printf("Test %d: nums=%s → missing = %d%n",
                i + 1, Arrays.toString(nums), missing);
        }
    }
}