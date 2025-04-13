/*
 # Intuition
The goal is to rearrange the numbers into the next lexicographically greater permutation. If such arrangement is not possible (i.e., the array is in descending order), we rearrange it to the lowest possible order (ascending). The key intuition is that we want to make the smallest change that results in a greater number. This is done by identifying the first decreasing element from the end and swapping it with the smallest number just larger than it, then reversing the suffix. 

# Approach
1. Find the first decreasing element from the end (let's call its index `i`). This is the pivot that needs to be swapped. 
2. If no such element exists, the entire array is in descending order. Simply reverse it to get the smallest permutation. 
3. If such an element exists, find the next larger element than `nums[i]` from the end of the array (index `j`) and swap them. 
4. Reverse the suffix starting from `i+1` to the end to get the next smallest lexicographical permutation. 
5. In-place operations and no extra space are used throughout.

# Complexity
- Time complexity: O(n)
Each step (finding the pivot, finding the next greater element, and reversing the suffix) takes linear time.
- Space complexity: O(1)
No additional data structures are used; the algorithm works in-place.
 */

import java.util.Arrays;
public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2; // Start from the second last element

        // Step 1: Find the first decreasing element from the end
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If a decreasing element was found
        if (i >= 0) {
            // Step 3: Find the next greater element on the right of i
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Step 4: Swap the two elements
            swap(nums, i, j);
        }

        // Step 5: Reverse the suffix starting from i+1
        reverse(nums, i + 1, n - 1);
    }

    // Helper method to swap elements at index i and j
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Helper method to reverse a portion of the array
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {1, 2, 3},        // ascending order
            {3, 2, 1},        // descending order
            {1, 1, 5},        // duplicate elements
            {1, 3, 2},        // mixed order
            {2, 3, 1},        // another permutation
            {1, 5, 1},        // pivot in middle
            {1},              // single element
            {1, 2},           // two elements
        };

        for (int[] test : testCases) {
            System.out.print("Before: " + Arrays.toString(test) + " -> ");
            nextPermutation(test);
            System.out.println("After: " + Arrays.toString(test));
        }
    }
}
