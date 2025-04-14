/*
 * Intuition:
 * =========
 * In a sorted array, there are no inversions; an inversion is a pair (i, j) such that i < j and arr[i] > arr[j].
 * A brute-force approach to count inversions would check each pair of elements in O(n²) time.
 * We can improve this by using a modified merge sort algorithm that counts inversions while sorting the array.
 * During the merge step, if an element from the right half is placed before an element from the left half,
 * it indicates that all remaining elements in the left half (which are all greater) form an inversion with that element.
 *
 * Approach:
 * =========
 * 1. Divide: Recursively split the array into two halves until each subarray has one element.
 * 2. Conquer: Count the number of inversions within each half recursively.
 * 3. Combine: Count the inversions across the two halves during the merge step.
 *    - When merging, if an element from the left subarray is greater than the element from the right subarray,
 *      then all the remaining elements in the left subarray (from the current index to the end) form inversions
 *      with the current element in the right subarray.
 * 4. The total inversion count is the sum of inversions in the left part, right part, and the merge step.
 *
 * Complexity:
 * ===========
 * Time Complexity: O(n log n)
 *   - The divide and merge process takes O(log n) levels and each level processes n elements.
 *
 * Space Complexity: O(n)
 *   - Extra space is required to hold temporary arrays during the merge process.
 */

import java.util.*;
public class CountInversion {
    // Merge two sorted subarrays [low...mid] and [mid+1...high] while counting inversions
    public static long merge(List<Integer> arr, int low, int mid, int high) {
        int i = low, j = mid + 1;
        long count = 0;
        List<Integer> temp = new ArrayList<>();

        // Merge the two subarrays while counting inversions.
        // When arr.get(i) > arr.get(j), then all elements from index i to mid are greater than arr.get(j).
        while (i <= mid && j <= high) {
            if (arr.get(i) <= arr.get(j)) {
                temp.add(arr.get(i));
                i++;
            } else {
                temp.add(arr.get(j));
                j++;
                // Count inversions: All remaining elements in left subarray (from i to mid) are inversions.
                count += (mid - i + 1);
            }
        }

        // Add remaining elements from the left subarray (if any).
        while (i <= mid) {
            temp.add(arr.get(i));
            i++;
        }

        // Add remaining elements from the right subarray (if any).
        while (j <= high) {
            temp.add(arr.get(j));
            j++;
        }

        // Copy sorted elements back to the original list for indices low to high.
        for (int p = 0; p < temp.size(); p++) {
            arr.set(low + p, temp.get(p));
        }
        return count;
    }

    // Recursively divide the array and count inversions.
    public static long divide(List<Integer> arr, int low, int high) {
        long count = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;
            count += divide(arr, low, mid);      // Count inversions in left half.
            count += divide(arr, mid + 1, high);   // Count inversions in right half.
            count += merge(arr, low, mid, high);   // Count split inversions while merging.
        }
        return count;
    }

    // Main function to count inversions in the array.
    public static long countInversions(List<Integer> arr) {
        int n = arr.size();
        return divide(arr, 0, n - 1);
    }

    public static void main(String[] args) {
        // Define various test cases to cover different scenarios.
        List<List<Integer>> testCases = new ArrayList<>();
        testCases.add(Arrays.asList(2, 3, 7, 1, 3, 5));      // Expected inversions: 5
        testCases.add(Arrays.asList(1, 2, 3, 4, 5));          // Sorted array => 0 inversions.
        testCases.add(Arrays.asList(5, 4, 3, 2, 1));          // Reverse sorted => Maximum inversions.
        testCases.add(Arrays.asList(1, 3, 5, 2, 4, 6));       // Some inversions.
        testCases.add(Arrays.asList(1));                      // Single element => 0 inversions.
        testCases.add(Arrays.asList(2, 1));                   // Expected inversions: 1

        int testCaseNumber = 1;
        // Process each test case.
        for (List<Integer> test : testCases) {
            // Create a mutable copy since our algorithm modifies the list.
            List<Integer> arr = new ArrayList<>(test);
            long inversions = countInversions(arr);
            System.out.println("Test case " + testCaseNumber++ + ": " + test);
            System.out.println("Number of inversions: " + inversions);
            System.out.println("Sorted array (after merge sort): " + arr);
            System.out.println("------------------------------");
        }
    }
}
