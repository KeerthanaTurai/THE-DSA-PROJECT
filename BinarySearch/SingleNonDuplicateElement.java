/**
 * This class finds the single element in a sorted array where every other element appears exactly twice.
 *
 * Intuition:
 * -----------
 * In a sorted array with pairs of duplicates, the first occurrence of a number should appear at an even index,
 * and the second occurrence at an odd index. When a single element is introduced, this pattern is broken.
 *
 * Approach:
 * -----------
 * We use a binary search to locate the single element:
 * 1. Compute the middle index.
 * 2. Compare the element at mid with its neighbors.
 *    - If nums[mid] equals one of its neighbors, check the parity (even/odd) of the index section
 *      to decide which half contains the single element.
 *    - If neither neighbor is equal to nums[mid], then nums[mid] is the single element.
 *
 * Complexity:
 * -----------
 * Time Complexity  : O(log n), since the binary search reduces the search space by half on each iteration.
 * Space Complexity : O(1), as only a few extra variables are used.
 */
public class SingleNonDuplicateElement {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // Boundary checks: if mid is at the boundaries, return that element.
            if (mid == 0) return nums[mid];
            if (mid == n - 1) return nums[mid];
            
            if (nums[mid] == nums[mid - 1]) {
                // nums[mid] is the second element in its pair.
                // Check if the left portion (from low to mid - 1) has an odd number of elements.
                if ((mid - 1 - low) % 2 == 1) {
                    // An odd count indicates the single element is in the left half.
                    high = mid - 2;
                } else {
                    // Otherwise, search right.
                    low = mid + 1;
                }
            } else if (nums[mid] == nums[mid + 1]) {
                // nums[mid] is the first element in its pair.
                // Check if the left portion (from low to mid) has an odd number of elements.
                if ((mid - low) % 2 == 1) {
                    // An odd count means the single element is in the left portion.
                    high = mid - 1;
                } else {
                    // Otherwise, the single element is in the right half.
                    low = mid + 2;
                }
            } else {
                // nums[mid] does not match either neighbor, so it is the single element.
                return nums[mid];
            }
        }
        
        // If not found in the loop, return the element at low (should be the single element).
        return nums[low];
    }

    public static void main(String[] args) {
        SingleNonDuplicateElement solution = new SingleNonDuplicateElement();

        // Define an array of test cases.
        int[][] testCases = {
            {1, 1, 2, 3, 3, 4, 4, 8, 8},  // Expected output: 2
            {3, 3, 7, 7, 10, 11, 11},      // Expected output: 10
            {1},                         // Expected output: 1 (only one element)
            {1, 1, 2, 2, 3, 3, 4, 4, 5}    // Expected output: 5
        };

        // Process and print each test case.
        for (int i = 0; i < testCases.length; i++) {
            int[] testCase = testCases[i];
            int result = solution.singleNonDuplicate(testCase);
            
            // Build and print the array content as a string.
            StringBuilder arrayStr = new StringBuilder();
            arrayStr.append("[");
            for (int j = 0; j < testCase.length; j++) {
                arrayStr.append(testCase[j]);
                if (j < testCase.length - 1) {
                    arrayStr.append(", ");
                }
            }
            arrayStr.append("]");
            
            // Print the test case number, input array, and output result.
            System.out.println("Test Case " + (i + 1) + ": Array = " + arrayStr.toString() +
                               " -> Single Element = " + result);
        }
    }
}
