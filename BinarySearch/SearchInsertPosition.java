/**
 * This class demonstrates how to find the index at which a target value should be
 * inserted in a sorted array such that the array remains sorted.
 *
 * Intuition:
 * -----------
 * The problem is a modification of the binary search algorithm. Instead of only looking
 * for the target, we also want to determine the correct insertion point when the target
 * is not found. When the binary search completes, the low pointer will represent the
 * correct index where the target can be inserted.
 *
 * Approach:
 * -----------
 * 1. Use binary search to traverse the sorted array:
 *    - Initialize two pointers: `low` (start of the array) and `high` (end of the array).
 *    - While `low` is less than or equal to `high`, compute the middle index.
 *    - If the target is found at the middle, return the mid index.
 *    - If the target is greater than the middle element, shift `low` to `mid + 1`.
 *    - If the target is less than the middle element, shift `high` to `mid - 1`.
 * 2. When the loop terminates, `low` will be pointing to the insertion index.
 *
 * Complexity:
 * -----------
 * Time Complexity  : O(log n) - Since binary search halves the search space every iteration.
 * Space Complexity : O(1) - No additional space is used apart from a few variables.
 */

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;
        
        // Early boundary checks (optional since binary search will cover these cases)
        if (target > nums[high]) return n;
        if (target < nums[0]) return 0;
        
        // Standard binary search loop.
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // If the target is found, return the current mid index.
            if (nums[mid] == target) {
                return mid;
            }
            // If the middle element is less than the target, move to the right subarray.
            else if (nums[mid] < target) {
                low = mid + 1;
            }
            // Else, move to the left subarray.
            else {
                high = mid - 1;
            }
        }
        // When the loop ends, 'low' is the correct insertion index.
        return low;
    }

    public static void main(String[] args) {
        SearchInsertPosition sip = new SearchInsertPosition();

        // Test Cases:
        // Test Case 1: Target is already present.
        int[] testArray1 = {1, 3, 5, 6};
        int target1 = 5;
        // Expected output: 2
        int index1 = sip.searchInsert(testArray1, target1);

        // Test Case 2: Target is not present and should be inserted in the middle.
        int target2 = 2;
        // Expected output: 1
        int index2 = sip.searchInsert(testArray1, target2);

        // Test Case 3: Target is greater than all elements.
        int target3 = 7;
        // Expected output: 4
        int index3 = sip.searchInsert(testArray1, target3);

        // Test Case 4: Target is smaller than all elements.
        int target4 = 0;
        // Expected output: 0
        int index4 = sip.searchInsert(testArray1, target4);

        // Test Case 5: Another middle insertion scenario.
        int target5 = 4;
        // Expected output: 2
        int index5 = sip.searchInsert(testArray1, target5);

        // Printing the results:
        System.out.println("Test Case 1: Array [1, 3, 5, 6], Target " + target1 + " -> Insert Position: " + index1);
        System.out.println("Test Case 2: Array [1, 3, 5, 6], Target " + target2 + " -> Insert Position: " + index2);
        System.out.println("Test Case 3: Array [1, 3, 5, 6], Target " + target3 + " -> Insert Position: " + index3);
        System.out.println("Test Case 4: Array [1, 3, 5, 6], Target " + target4 + " -> Insert Position: " + index4);
        System.out.println("Test Case 5: Array [1, 3, 5, 6], Target " + target5 + " -> Insert Position: " + index5);
    }
}

