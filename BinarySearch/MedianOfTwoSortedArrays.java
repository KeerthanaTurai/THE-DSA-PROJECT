/**
 * This program finds the median of two sorted arrays.
 * 
 * Intuition:
 * -----------
 * The goal is to find a partition of the two arrays such that:
 *    - The left half (from both arrays) contains half the total number of elements.
 *    - Every element on the left is less than or equal to every element on the right.
 * 
 * Approach:
 * -----------
 * 1. Always binary search on the smaller array (nums1) to ensure O(log(min(m, n))) time complexity.
 * 2. Let m and n be the sizes of nums1 and nums2 respectively. We compute:
 *         median = (m + n + 1) / 2;
 *    This represents the total count of numbers in the left halves combined.
 * 3. Use binary search to find an index 'mid1' for nums1 such that:
 *         mid2 = median - mid1 (the required partition index in nums2).
 * 4. Define:
 *         l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
 *         l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
 *         r1 = (mid1 == m) ? Integer.MAX_VALUE : nums1[mid1];
 *         r2 = (mid2 == n) ? Integer.MAX_VALUE : nums2[mid2];
 * 5. We check if the partition is correct:
 *         If l1 <= r2 && l2 <= r1, then the median is calculated:
 *              - If total number is odd: median = max(l1, l2)
 *              - If even: median = (max(l1, l2) + min(r1, r2)) / 2.0
 *         Otherwise, adjust the binary search bounds:
 *              - If l1 > r2, then reduce the partition index in nums1.
 *              - Else, increase the partition index in nums1.
 * 
 * Complexity:
 * ------------
 * Time Complexity  : O(log(min(m, n))) - binary search is performed over the smaller array.
 * Space Complexity : O(1) - constant space.
 */

 public class MedianOfTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Get lengths of both arrays.
        int m = nums1.length, n = nums2.length;
        
        // Ensure that nums1 is the smaller array to minimize binary search iterations.
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // Calculate the half partition point (median index for left parts).
        int median = (m + n + 1) / 2;
        int low = 0, high = m;

        // Binary search over the smaller array.
        while (low <= high) {
            // Partition index for nums1.
            int mid1 = low + (high - low) / 2;
            // Partition index for nums2 such that left parts include 'median' elements.
            int mid2 = median - mid1;

            // l1: largest element on the left of nums1 partition, or MIN_VALUE if partition is at start.
            int l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            // l2: largest element on the left of nums2 partition, or MIN_VALUE if partition is at start.
            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            // r1: smallest element on the right of nums1 partition, or MAX_VALUE if partition is at end.
            int r1 = (mid1 == m) ? Integer.MAX_VALUE : nums1[mid1];
            // r2: smallest element on the right of nums2 partition, or MAX_VALUE if partition is at end.
            int r2 = (mid2 == n) ? Integer.MAX_VALUE : nums2[mid2];

            // Check if the partitions are valid.
            if (l1 <= r2 && l2 <= r1) {
                // If the combined arrays have even total elements, the median is the average of the two middle values.
                if ((m + n) % 2 == 0) {
                    return ((double)(Math.max(l1, l2) + Math.min(r1, r2))) / 2;
                } else {
                    // For an odd total, the median is the maximum of the left partition elements.
                    return (double) Math.max(l1, l2);
                }
            } else {
                // Adjust binary search:
                // If left element of nums1 is greater than right element of nums2, decrease partition index in nums1.
                if (l1 > r2) {
                    high = mid1 - 1;
                } else {
                    // Otherwise, increase partition index in nums1.
                    low = mid1 + 1;
                }
            }
        }
        // If no valid partition is found, return -1. (This should not happen if arrays are valid and sorted.)
        return -1;
    }

    /**
     * Main method to test the findMedianSortedArrays function with various test cases.
     */
    public static void main(String[] args) {
        // Test case 1: Arrays with even total number of elements.
        int[] nums1a = {1, 3};
        int[] nums2a = {2, 4};
        System.out.println("Test Case 1: " + findMedianSortedArrays(nums1a, nums2a));
        // Expected output: 2.5

        // Test case 2: Arrays with odd total number of elements.
        int[] nums1b = {1, 3, 8};
        int[] nums2b = {7, 9, 10, 11};
        System.out.println("Test Case 2: " + findMedianSortedArrays(nums1b, nums2b));
        // Expected output: 8.0

        // Test case 3: One array is empty.
        int[] nums1c = {};
        int[] nums2c = {2, 3};
        System.out.println("Test Case 3: " + findMedianSortedArrays(nums1c, nums2c));
        // Expected output: 2.5

        // Test case 4: Arrays with different lengths.
        int[] nums1d = {1, 2};
        int[] nums2d = {3, 4, 5, 6, 7};
        System.out.println("Test Case 4: " + findMedianSortedArrays(nums1d, nums2d));
        // Expected output: 4.0

        // Test case 5: Arrays where all elements in one array are smaller than all elements in the other.
        int[] nums1e = {1, 2, 3};
        int[] nums2e = {10, 11, 12, 13};
        System.out.println("Test Case 5: " + findMedianSortedArrays(nums1e, nums2e));
        // Expected output: 10.0
    }
}
