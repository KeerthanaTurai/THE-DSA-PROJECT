/*
# Intuition
<!--
The maximum subarray problem requires us to find a contiguous subarray with the largest possible sum. 
A well-known approach to this is Kadane's algorithm, which involves maintaining a running sum and updating the maximum sum encountered as you iterate through the array.
If the running sum falls below zero, it is reset because a negative sum would only detract from the sum of future contiguous subarrays.
However, a twist here is the need to handle the case where all numbers are negative, as Kadane's algorithm would otherwise incorrectly return 0.
-->

# Approach
<!--
1. Initialize:
   - A running sum (`sum`) to accumulate the current subarray's sum.
   - A variable (`max`) to hold the maximum subarray sum found so far.
   - A flag (`check`) to indicate if there's at least one non-negative number in the array.
2. Iterate over the array:
   - For each element, update `sum` by adding the current element.
   - If the element is non-negative, set the `check` flag.
   - If `sum` becomes negative, reset it to zero.
   - Update `max` if the current `sum` exceeds it.
3. Final result:
   - If there is at least one non-negative number (`check` is set), return `max`.
   - Otherwise, if all numbers are negative, iterate over the array to find and return the maximum (least negative) value.

# Complexity
- Time complexity: O(n) The algorithm processes each element of the array once (or at most twice in the all-negative case), resulting in linear time complexity.
- Space complexity: O(1) Only a fixed number of variables are used regardless of the input size, so the space complexity is constant.
*/
public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        int i, n = nums.length, max = Integer.MIN_VALUE, sum = 0, check = 0;
        
        // Iterate through the array to find the maximum subarray sum using Kadane's algorithm.
        for (i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                check = 1; // Set flag if there's at least one non-negative number.
            }
            sum += nums[i]; // Add the current number to the running sum.
            
            // If the running sum becomes negative, reset it to 0.
            if (sum < 0) {
                sum = 0;
            }
            
            // Update max if the current running sum is greater.
            if (max < sum) {
                max = sum;
            }
        }
        
        // If there is at least one non-negative number, return the maximum subarray sum.
        if (check == 1) {
            return max;
        } else {
            // All numbers are negative, so return the maximum (least negative) element.
            max = Integer.MIN_VALUE;
            for (i = 0; i < n; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
            }
            return max;
        }
    }

    public static void main(String args[]) {
        int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4};
        long maxSum = maxSubArray(arr);
        System.out.println("The maximum subarray sum is: " + maxSum);
        int[] arr1 = { -2, -3, -1, -5};
        maxSum = maxSubArray(arr1);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}
