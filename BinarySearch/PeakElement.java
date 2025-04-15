/*
 * Intuition:
 * =========
 * A peak element is defined as an element that is not smaller than its neighbors.
 * In an array, especially one that is not strictly increasing or decreasing,
 * a peak element always exists.
 * We can use binary search to narrow down the search space for a peak because if
 * we examine the middle element, we can tell by comparing its neighbors which half
 * must contain a peak.
 *
 * Approach:
 * =========
 * 1. Handle base cases:
 *    - If the array is empty, return -1 (no peak).
 *    - If it contains one element, that element is the peak.
 *    - Check the boundaries: if the first element is greater than the second, it is a peak;
 *      similarly, if the last element is greater than the second last, it is a peak.
 * 2. Apply binary search for indices 1 to n-2:
 *    - Compute mid = (low + high) / 2.
 *    - If the element at mid is greater than both its neighbors, return mid.
 *    - Else, if the left neighbor is lower (indicating an upward slope), move low to mid+1.
 *      Otherwise, move high to mid-1.
 *
 * Complexity:
 * ===========
 * Time Complexity: O(log n)
 *   - Each binary search iteration halves the search space.
 * Space Complexity: O(1)
 *   - Only a constant amount of extra space is used.
 */

import java.util.Arrays;
public class PeakElement {
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        
        // Base cases for empty and single element arrays.
        if (n == 0) return -1;
        if (n == 1) return 0;
        
        // Check boundaries.
        if (nums[0] > nums[1]) return 0;
        if (nums[n - 1] > nums[n - 2]) return n - 1;
        
        // Apply binary search in the range [1, n - 2].
        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            
            // If current element is a peak
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            // If the sequence is ascending, the peak lies to the right.
            else if (nums[mid] > nums[mid - 1]) {
                low = mid + 1;
            }
            // Otherwise, the peak lies to the left.
            else {
                high = mid - 1;
            }
        }
        // Should never reach here if input array has a peak, but added for compilation.
        return -1;
    }
    
    // Main function with various test cases.
    public static void main(String[] args) {
        int[][] testCases = {
            {1, 2, 3, 1},        // Expected: index 2 (value 3)
            {1, 2, 1, 3, 5, 6, 4}, // Expected: index 5 (value 6) or similar
            {1},                 // Expected: index 0 (only element)
            {2, 1},              // Expected: index 0 (value 2)
            {1, 2},              // Expected: index 1 (value 2)
            {1, 2, 3, 4, 5},     // Expected: index 4 (value 5)
            {5, 4, 3, 2, 1},     // Expected: index 0 (value 5)
            {1, 3, 20, 4, 1, 0}  // Expected: index 2 (value 20)
        };
        
        for (int i = 0; i < testCases.length; i++) {
            int peakIndex = findPeakElement(testCases[i]);
            System.out.println("Test case " + (i + 1) + ": " + Arrays.toString(testCases[i]));
            if (peakIndex != -1) {
                System.out.println("Peak Index: " + peakIndex + ", Peak Value: " + testCases[i][peakIndex]);
            } else {
                System.out.println("No peak element found.");
            }
            System.out.println("--------------------------------------");
        }
    }
}
