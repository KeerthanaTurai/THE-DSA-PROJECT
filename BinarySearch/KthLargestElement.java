import java.util.*;

/**
 * Problem 215. Kth Largest Element in an Array
 *
 * Given an integer array nums and an integer k, return the kth largest element
 * in the array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 *
 * Intuition:
 *   To find the kth largest element without sorting the entire array, we can
 *   maintain a min-heap (priority queue) of size k. We iterate through the
 *   numbers, pushing each onto the heap. Once the heap grows larger than k,
 *   we pop the smallest element. At the end, the heap contains the k largest
 *   elements, and its root is the kth largest.
 *
 * Approach:
 *   1. Initialize a min-heap (PriorityQueue<Integer>).
 *   2. For each num in nums:
 *        - Add num to the heap.
 *        - If heap size exceeds k, remove the smallest element (heap.poll()).
 *   3. After processing all elements, the top of the heap (heap.peek()) is
 *      the kth largest.
 *
 * Time Complexity: O(n log k), where n = nums.length, since each push/pop
 *   on a size-k heap costs O(log k).
 * Space Complexity: O(k) for the heap.
 */
public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        KthLargestElement solver = new KthLargestElement();

        // Example 1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.printf("Input: %s, k = %d → %d (expected 5)%n",
                          Arrays.toString(nums1), k1,
                          solver.findKthLargest(nums1, k1));

        // Example 2
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.printf("Input: %s, k = %d → %d (expected 4)%n",
                          Arrays.toString(nums2), k2,
                          solver.findKthLargest(nums2, k2));

        // Additional tests
        int[] nums3 = {1, 2, 3, 4, 5};
        int k3 = 1;
        System.out.printf("Input: %s, k = %d → %d (expected 5)%n",
                          Arrays.toString(nums3), k3,
                          solver.findKthLargest(nums3, k3));

        int[] nums5 = {5, 5, 5, 5};
        int k5 = 2;
        System.out.printf("Input: %s, k = %d → %d (expected 5)%n",
                          Arrays.toString(nums5), k5,
                          solver.findKthLargest(nums5, k5));
    }
}