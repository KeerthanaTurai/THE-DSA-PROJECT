import java.util.Arrays;
/**
 * Problem 238. Product of Array Except Self
 *
 * Given an integer array nums, return an array output such that
 * output[i] is equal to the product of all the elements of nums
 * except nums[i].
 *
 * You must write an algorithm that runs in O(n) time and without
 * using the division operation.
 *
 * Intuition:
 *   We can build the result by two passes:
 *     1) In a forward pass, compute the running product of all elements before i,
 *        and store it at output[i].
 *     2) In a backward pass, compute the running product of all elements after i,
 *        and multiply it into output[i].
 *
 * Approach:
 *   - Let n = nums.length.
 *   - Allocate output[n].
 *   - Use pre = 1 to track the product of nums[0..i-1]. Initialize output[0] = 1.
 *     Then for i from 0 to n-2:
 *       pre *= nums[i];
 *       output[i+1] = pre;
 *   - Use post = 1 to track the product of nums[i+1..n-1]. Then for i from n-1
 *     down to 2:
 *       post *= nums[i];
 *       output[i-1] *= post;
 *   - Finally, for i == 0, multiply by the product of nums[1..n-1], which is held
 *     in post after the loop ends.
 *
 * Time Complexity: O(n), two linear passes.
 * Space Complexity: O(1) extra (ignoring the output array).
 */
public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];
        int pre = 1, post = 1, i;

        // forward pass: pre computes product of all elements before i
        for (i = 0; i < n - 1; i++) {
            pre = pre * nums[i];
            output[i + 1] = pre;
        }

        // backward pass: post computes product of all elements after i
        for (i = n - 1; i > 1; i--) {
            post = post * nums[i];
            output[i - 1] *= post;
        }

        // handle i == 0: multiply by product of all elements after index 0
        output[0] = post * nums[i];

        return output;
    }

    // Simple test harness
    public static void main(String[] args) {
        ProductExceptSelf solver = new ProductExceptSelf();

        int[][] tests = {
            {1, 2, 3, 4},        // expect [24,12,8,6]
            {-1, 1, 0, -3, 3},   // expect [0,0,9,0,0]
            {2, 3},              // expect [3,2]
            {0, 0},              // expect [0,0]
            {5, 0, 2},           // expect [0,10,0]
            {1, -1, 1, -1},      // expect [1,-1,1,-1]
            {10, 0, 5, 2},       // expect [0,100,0,0]
        };

        for (int t = 0; t < tests.length; t++) {
            int[] nums = tests[t];
            int[] result = solver.productExceptSelf(nums);
            System.out.printf("Test %d: nums=%s -> output=%s%n",
                t + 1,
                Arrays.toString(nums),
                Arrays.toString(result)
            );
        }
    }
}