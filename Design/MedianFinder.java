import java.util.*;

/**
 * Problem 295. Find Median from Data Stream
 *
 * The median is the middle value in an ordered integer list. If the list size
 * is even, the median is the average of the two middle values.
 *
 * Design a data structure that supports:
 *   - addNum(int num): add an integer from the data stream.
 *   - findMedian(): return the median of all added elements.
 *
 * Example:
 *   MedianFinder mf = new MedianFinder();
 *   mf.addNum(1);    // [1]
 *   mf.addNum(2);    // [1,2]
 *   mf.findMedian(); // -> 1.5
 *   mf.addNum(3);    // [1,2,3]
 *   mf.findMedian(); // -> 2.0
 *
 * Intuition:
 *   We need to quickly access the middle element(s) of a growing list. By
 *   maintaining two heaps (a max-heap for the lower half, a min-heap for the
 *   upper half), we can balance the sizes so that the median is either the
 *   top of one heap (odd count) or the average of both tops (even count).
 *
 * Approach:
 *   - maxHeap (left side) stores the smaller half of numbers, root = largest of them.
 *   - minHeap (right side) stores the larger half, root = smallest of them.
 *   - On addNum:
 *       1. If maxHeap is empty or num < maxHeap.peek(), push to maxHeap; else minHeap.
 *       2. Rebalance: if one heap has size > other + 1, move the root across.
 *   - On findMedian:
 *       • If sizes equal, median = (maxHeap.peek() + minHeap.peek()) / 2.
 *       • Else median = root of the larger heap.
 *
 * Time Complexity:
 *   - addNum: O(log n) for heap insertion and possible rebalance.
 *   - findMedian: O(1).
 *
 * Space Complexity:
 *   O(n) total for the two heaps.
 */
public class MedianFinder {
    private PriorityQueue<Integer> minHeap;  // holds the larger half
    private PriorityQueue<Integer> maxHeap;  // holds the smaller half (as a max-heap)

    /** Initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }
    
    /** Adds a number into the data structure. */
    public void addNum(int num) {
        // Decide which heap to add to
        if (maxHeap.isEmpty() || maxHeap.peek() > num) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        // Rebalance so that the heaps differ in size by at most 1
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    /** Returns the median of current data stream */
    public double findMedian() {
        int sz1 = maxHeap.size(), sz2 = minHeap.size();
        if (sz1 == sz2) {
            // Even number of elements
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else if (sz1 > sz2) {
            return maxHeap.peek();
        } else {
            return minHeap.peek();
        }
    }

    /** Test harness */
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();

        // Test 1: Example from prompt
        mf.addNum(1);
        mf.addNum(2);
        System.out.println("Expected 1.5, got: " + mf.findMedian());
        mf.addNum(3);
        System.out.println("Expected 2.0, got: " + mf.findMedian());

        // Test 2: Single element
        MedianFinder mf2 = new MedianFinder();
        mf2.addNum(42);
        System.out.println("Expected 42.0, got: " + mf2.findMedian());

        // Test 3: Even count
        MedianFinder mf3 = new MedianFinder();
        mf3.addNum(5);
        mf3.addNum(15);
        mf3.addNum(1);
        mf3.addNum(3);
        // Data = [1,3,5,15]
        System.out.println("Expected (3+5)/2=4.0, got: " + mf3.findMedian());

        // Test 4: Negative and positive
        MedianFinder mf4 = new MedianFinder();
        int[] arr4 = {-5, -10, 0, 5, 10};
        for (int x : arr4) mf4.addNum(x);
        // Data = [-10,-5,0,5,10]
        System.out.println("Expected 0.0, got: " + mf4.findMedian());

        // Test 5: Repeated values
        MedianFinder mf5 = new MedianFinder();
        int[] arr5 = {2, 2, 2, 2};
        for (int x : arr5) {
            mf5.addNum(x);
            System.out.println("After adding " + x + ", median = " + mf5.findMedian());
        }

        // Test 6: Large sequence
        MedianFinder mf6 = new MedianFinder();
        Random rand = new Random(0);
        for (int i = 0; i < 20; i++) {
            int x = rand.nextInt(100) - 50;
            mf6.addNum(x);
            System.out.printf("Insert %3d, median now = %.2f%n", x, mf6.findMedian());
        }
    }
}