/**
 * Intuition:
 * -----------
 * The binary search algorithm works on the divide-and-conquer principle.
 * Given a sorted array, we can efficiently locate a target by repeatedly
 * dividing the search space in half. At each step, we compare the middle
 * element to the target:
 *    - If it is the target, we return the index.
 *    - If the target is smaller, we search in the left half.
 *    - If the target is larger, we search in the right half.
 * This continues until the target is found or the search space is empty.
 *
 * Approach:
 * -----------
 * 1. Initialize two pointers: low (start of array) and high (end of array).
 * 2. While low is less than or equal to high:
 *    - Compute the middle index without causing integer overflow.
 *    - If the middle element is equal to the target, return the index.
 *    - Otherwise, adjust the low or high pointer depending on whether the
 *      middle element is less than or greater than the target.
 * 3. If the target is not found, return -1.
 *
 * Complexity:
 * ------------
 * Time Complexity  : O(log n) - The search space is halved with each iteration.
 * Space Complexity : O(1) - Only constant extra space is used.
 */

 public class BinarySearchAlgorithm {
    public int search(int[] nums, int target) {
        // Get the total number of elements in the array.
        int n = nums.length;
        // Initialize pointers for the low and high ends of the array.
        int low = 0, high = n - 1;
        
        // Continue searching while the search space is not empty.
        while (low <= high) {
            // Use low + (high - low) / 2 to avoid potential overflow.
            int mid = low + (high - low) / 2;
            
            // If the middle element is the target, return its index.
            if (nums[mid] == target) {
                return mid;
            } else {
                // If target is smaller, narrow the search to the left subarray.
                if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    // If target is larger, narrow the search to the right subarray.
                    low = mid + 1;
                }
            }
        }
        
        // Target not found, return -1.
        return -1;
    }
    public static void main(String[] args) {
        BinarySearchAlgorithm bs = new BinarySearchAlgorithm();

        // Test case 1: Target is in the middle of the array.
        int[] testArray1 = {1, 2, 3, 4, 5};
        int target1 = 3;
        System.out.println("Test Case 1: Index of " + target1 + " is " + bs.search(testArray1, target1));
        // Expected output: 2

        // Test case 2: Target is the first element.
        int[] testArray2 = {2, 4, 6, 8, 10};
        int target2 = 2;
        System.out.println("Test Case 2: Index of " + target2 + " is " + bs.search(testArray2, target2));
        // Expected output: 0

        // Test case 3: Target is the last element.
        int[] testArray3 = {5, 10, 15, 20, 25};
        int target3 = 25;
        System.out.println("Test Case 3: Index of " + target3 + " is " + bs.search(testArray3, target3));
        // Expected output: 4

        // Test case 4: Target is not present in the array.
        int[] testArray4 = {1, 3, 5, 7, 9};
        int target4 = 6;
        System.out.println("Test Case 4: Index of " + target4 + " is " + bs.search(testArray4, target4));
        // Expected output: -1

        // Test case 5: Single element array with target present.
        int[] testArray5 = {100};
        int target5 = 100;
        System.out.println("Test Case 5: Index of " + target5 + " is " + bs.search(testArray5, target5));
        // Expected output: 0

        // Test case 6: Single element array with target absent.
        int[] testArray6 = {50};
        int target6 = 10;
        System.out.println("Test Case 6: Index of " + target6 + " is " + bs.search(testArray6, target6));
        // Expected output: -1
    }
}