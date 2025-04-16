/**
 * Given a sorted array of integers (in non-decreasing order), this class finds the starting and 
 * ending position of a given target value. If the target is not found, it returns [-1, -1].
 *
 * Intuition:
 * -----------
 * Since the array is sorted, binary search is an ideal approach for efficiently locating the target.
 * However, to find the boundaries (first and last occurrences) of the target, we perform two binary searches:
 *
 * 1. One binary search to find the leftmost (first) occurrence of the target.
 * 2. Another binary search to find the rightmost (last) occurrence of the target.
 *
 * Approach:
 * ---------
 * - We define a helper method "findBoundary" that searches for the target. The method takes an extra
 *   boolean parameter "isLeft" to determine if the search is for the left or right boundary.
 * - If a target is found:
 *      - When searching for the left boundary, we continue the search on the left side to check for a
 *        previous occurrence.
 *      - When searching for the right boundary, we continue on the right side to check for a later occurrence.
 * - If the target is not present, the helper returns -1.
 * - Finally, the main method "searchRange" returns an array of two integers representing the first and last positions.
 *
 * Complexity:
 * ------------
 * Time Complexity  : O(log n) - Each binary search runs in O(log n), so overall the algorithm takes O(log n) time.
 * Space Complexity : O(1) - The solution uses only a constant amount of extra space.
 */

 public class FirstLastPosOfElementInSortedArray {
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // Find the leftmost (first) occurrence of target.
        result[0] = findBoundary(nums, target, true);
        // Find the rightmost (last) occurrence of target.
        result[1] = findBoundary(nums, target, false);
        return result;
    }

    public static int findBoundary(int[] nums, int target, boolean isLeft) {
        int index = -1;
        int low = 0;
        int high = nums.length - 1;
        
        while (low <= high) {
            // Compute middle index to avoid overflow.
            int mid = low + (high - low) / 2;
            
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                // When target is found, record the index.
                index = mid;
                // Continue the search on the left side if finding the left boundary.
                if (isLeft) {
                    high = mid - 1;
                } else {
                    // Otherwise, continue on the right side to find the right boundary.
                    low = mid + 1;
                }
            }
        }
        return index;
    }

    public static void main(String[] args) {
        // Test Case 1: Example provided in the problem statement.
        int[] testArray1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] result1 = searchRange(testArray1, target1);
        // Expected output: [3,4]
        System.out.println("Test Case 1: Array [5, 7, 7, 8, 8, 10], Target " + target1 +
                " -> Output: [" + result1[0] + ", " + result1[1] + "]");

        // Test Case 2: Target not found in the array.
        int[] testArray2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        int[] result2 = searchRange(testArray2, target2);
        // Expected output: [-1,-1]
        System.out.println("Test Case 2: Array [5, 7, 7, 8, 8, 10], Target " + target2 +
                " -> Output: [" + result2[0] + ", " + result2[1] + "]");

        // Test Case 3: Empty array input.
        int[] testArray3 = {};
        int target3 = 0;
        int[] result3 = searchRange(testArray3, target3);
        // Expected output: [-1,-1]
        System.out.println("Test Case 3: Empty Array, Target " + target3 +
                " -> Output: [" + result3[0] + ", " + result3[1] + "]");

        // Test Case 4: Array where all elements are the target.
        int[] testArray4 = {2, 2, 2, 2, 2};
        int target4 = 2;
        int[] result4 = searchRange(testArray4, target4);
        // Expected output: [0,4]
        System.out.println("Test Case 4: Array [2, 2, 2, 2, 2], Target " + target4 +
                " -> Output: [" + result4[0] + ", " + result4[1] + "]");

        // Test Case 5: Array with single element that matches the target.
        int[] testArray5 = {1};
        int target5 = 1;
        int[] result5 = searchRange(testArray5, target5);
        // Expected output: [0,0]
        System.out.println("Test Case 5: Array [1], Target " + target5 +
                " -> Output: [" + result5[0] + ", " + result5[1] + "]");

        // Test Case 6: Array with single element that does not match the target.
        int[] testArray6 = {1};
        int target6 = 2;
        int[] result6 = searchRange(testArray6, target6);
        // Expected output: [-1,-1]
        System.out.println("Test Case 6: Array [1], Target " + target6 +
                " -> Output: [" + result6[0] + ", " + result6[1] + "]");
    }
}