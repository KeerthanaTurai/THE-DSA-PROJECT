import java.util.*;

/**
 * Problem 253. Meeting Rooms II
 *
 * Intuition:
 *   We need to schedule all meetings into the fewest number of rooms so that
 *   no two overlapping meetings share a room. If we sort meetings by start time,
 *   then for each meeting we can check if any existing room becomes free by
 *   comparing with the earliest ending meeting.
 *
 * Approach:
 *   1. Sort the intervals by start time.
 *   2. Use a min-heap (priority queue) to track end times of meetings currently occupying rooms.
 *      - The heap’s top is the smallest end time among all rooms in use.
 *   3. Iterate through the sorted intervals:
 *      a. If the heap is non-empty and the smallest end time ≤ current start,
 *         that room is freed—pop from the heap.
 *      b. Allocate a room for the current meeting by pushing its end time onto the heap.
 *   4. After processing all meetings, the heap size is the number of rooms needed.
 *
 * Time Complexity:
 *   - Sorting: O(n log n)
 *   - Heap operations: O(n log n) in total (each of n intervals causes at most one push and one pop)
 *   Overall: O(n log n)
 *
 * Space Complexity:
 *   O(n) for the heap in the worst case (all meetings overlap)
 */
public class MeetingRoomsII {

    /**
     * Returns the minimum number of conference rooms required.
     *
     * @param intervals array of [start, end] meeting times
     * @return the minimum number of rooms needed
     */
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) return n;

        // 1) Sort by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // 2) Min-heap of end times
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 3) Allocate rooms
        for (int[] meeting : intervals) {
            int start = meeting[0], end = meeting[1];

            // If the earliest-ending room is free, reuse it
            if (!minHeap.isEmpty() && minHeap.peek() <= start) {
                minHeap.poll();
            }

            // Allocate a room (new or just freed) to this meeting
            minHeap.offer(end);
        }

        // 4) Number of rooms is heap size
        return minHeap.size();
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        MeetingRoomsII solver = new MeetingRoomsII();

        int[][][] testIntervals = {
            { {0, 30}, {5, 10}, {15, 20} },   // Example 1
            { {7, 10}, {2, 4} },              // Example 2
            { {1, 5}, {5, 10}, {10, 15} },    // Touching endpoints
            { {2, 6}, {1, 5}, {5, 7}, {8, 12} }, // Mixed overlaps
            { {1, 10}, {2, 3}, {4, 5}, {6, 7} } // One long, several short
        };
        int[] expected = {2, 1, 1, 2, 2};

        for (int i = 0; i < testIntervals.length; i++) {
            int rooms = solver.minMeetingRooms(testIntervals[i]);
            System.out.printf("Test %d: intervals = %s → rooms needed = %d (expected %d)%n",
                i + 1,
                Arrays.deepToString(testIntervals[i]),
                rooms,
                expected[i]
            );
        }
    }
}