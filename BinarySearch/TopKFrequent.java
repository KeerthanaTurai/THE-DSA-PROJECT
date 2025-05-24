import java.util.*;

/**
 * Problem 347. Top K Frequent Elements
 *
 * Intuition:
 *   We need the k elements that occur most frequently in the array. We don't
 *   need a full sort by frequency; we only need the top k. A min-heap of size k
 *   lets us keep track of the k largest frequencies: we push each unique number
 *   onto the heap (keyed by its frequency) and, if the heap grows beyond k,
 *   pop the smallest frequency element. At the end, the heap contains the k
 *   most frequent elements.
 *
 * Approach:
 *   1. Count frequencies in a HashMap<Integer,Integer> freq.
 *   2. Create a min-heap (PriorityQueue<Integer>) that orders keys by freq.get(key).
 *   3. For each key in freq:
 *        - offer(key) to the heap.
 *        - if heap.size() > k, poll() to remove the least frequent.
 *   4. Extract the heap into an array of length k; the order can be any.
 *
 * Time Complexity:
 *   - O(n) to build the frequency map (n = nums.length).
 *   - Let m = number of unique keys. We do m heap operations of O(log k) each.
 *   Overall: O(n + m log k). Since m ≤ n, that's O(n log k).
 *
 * Space Complexity:
 *   - O(m) for the frequency map and the heap.
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // 1) Build frequency map
        Map<Integer,Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 2) Min-heap ordered by frequency ascending
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> freq.get(a) - freq.get(b)
        );

        // 3) Maintain heap of size k
        for (int key : freq.keySet()) {
            minHeap.offer(key);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 4) Extract results
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        TopKFrequent solver = new TopKFrequent();

        // Helper to print arrays
        class Test {
            int[] nums;
            int k;
            Test(int[] nums, int k) { this.nums = nums; this.k = k; }
        }

        List<Test> tests = Arrays.asList(
            new Test(new int[]{1,1,1,2,2,3}, 2),      // expect [1,2]
            new Test(new int[]{1}, 1),                // expect [1]
            new Test(new int[]{-1, -1, 2, 2, 2, 3}, 2),// expect [2,-1]
            new Test(new int[]{4,4,4,6,6,7,7,7,7}, 1), // expect [7]
            new Test(new int[]{5,3,1,1,1,3,73,1}, 2)   // expect [1,3]
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int[] result = solver.topKFrequent(t.nums, t.k);
            System.out.printf("Test %d: nums = %s, k = %d → %s%n",
                i + 1,
                Arrays.toString(t.nums),
                t.k,
                Arrays.toString(result)
            );
        }
    }
}