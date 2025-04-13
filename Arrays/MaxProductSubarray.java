/* Intuition
In a subarray product problem, multiplying negative numbers can flip the sign of the product, making it tricky to track maximum values. 
To capture the maximum product, we need to consider both forward (prefix) and backward (suffix) scans, because the maximum product could 
arise after ignoring a zero or flipping signs due to negative numbers.

Approach
1. Initialize: - `pre` for prefix product (left to right) 
- `suf` for suffix product (right to left)
- `ans` to store the maximum product found so far 
2. Iterate over the array: - Multiply the prefix and suffix products with the current elements. 
- Update `ans` as the max of itself, prefix, and suffix. 
- Reset `pre` or `suf` to 1 when a zero is encountered (since multiplying by zero resets product). 
3. This captures all subarray product possibilities, accounting for zeros and negative values from both directions. 

Complexity
- Time complexity: O(n)
We scan the array once from left to right and right to left simultaneously.
- Space complexity: O(1)
Only a constant number of variables are used.
*/

import java.util.Arrays;

public class MaxProductSubarray {
    public static int maxProduct(int[] nums) {
        int n = nums.length;

        int pre = 1, suf = 1; // Prefix and suffix product
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            pre *= nums[i]; // Forward pass
            suf *= nums[n - i - 1]; // Backward pass

            ans = Math.max(ans, Math.max(pre, suf)); // Update maximum

            // Reset prefix/suffix product if a zero is encountered
            if (pre == 0) pre = 1;
            if (suf == 0) suf = 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {2, 3, -2, 4},           // Expected: 6 (2*3)
            {-2, 0, -1},             // Expected: 0
            {-2, 3, -4},             // Expected: 24 (-2*3*-4)
            {0, -2, 0},              // Expected: 0
            {2, -5, -2, -4, 3},      // Expected: 240
            {1, 2, 3, 4},            // Expected: 24
            {-1, -2, -3, 0},         // Expected: 6 (-1*-2*-3)
            {-1},                    // Expected: -1
            {0},                     // Expected: 0
            {-2, -3, 7}              // Expected: 42 (-2*-3*7)
        };

        for (int[] test : testCases) {
            System.out.println("Input: " + Arrays.toString(test));
            int result = maxProduct(test);
            System.out.println("Maximum Product Subarray: " + result + "\n");
        }
    }
}
