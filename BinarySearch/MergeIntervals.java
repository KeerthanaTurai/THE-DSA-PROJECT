import java.util.*;

/**
 * Problem 56. Merge Intervals
 *
 * Intuition:
 *   When intervals overlap, we can merge them into a larger interval that
 *   spans from the earliest start to the latest end of the overlapping set.
 *   By sorting intervals by their start times, any overlap must occur between
 *   an interval and the one immediately before it in sorted order.
 *
 * Approach:
 *   1. Sort the intervals by start time ascending.
 *   2. Initialize an empty list merged of int[].
 *   3. For each interval in the sorted list:
 *        - If merged is empty, or the last merged interval ends before the
 *          current interval starts, there is no overlap: add current as is.
 *        - Otherwise, overlap exists: merge by updating the end of the last
 *          merged interval to the max of its current end and the current end.
 *   4. Convert merged list back to int[][] and return.
 *
 * Time Complexity: O(n log n) for sorting, plus O(n) to scan and merge → O(n log n).
 * Space Complexity: O(n) for the output list (plus O(log n) recursion for sort).
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        // 1) Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // 2) Merge in one pass
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // If no overlap, append
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                // Overlap: extend the end if needed
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        // 3) Convert to array
        return merged.toArray(new int[merged.size()][2]);
    }

    // -------------------- Main with Test Cases --------------------
    public static void main(String[] args) {
        MergeIntervals solver = new MergeIntervals();

        // Helper to print results
        class Test {
            int[][] intervals;
            String description;
            Test(int[][] intervals, String desc) {
                this.intervals = intervals;
                this.description = desc;
            }
        }

        List<Test> tests = Arrays.asList(
            new Test(new int[][]{{1,3},{2,6},{8,10},{15,18}}, "Example 1"),
            new Test(new int[][]{{1,4},{4,5}}, "Example 2 (touching)"),
            new Test(new int[][]{{1,4},{0,2},{3,5}}, "Overlap chain"),
            new Test(new int[][]{{1,4}}, "Single interval"),
            new Test(new int[][]{}, "Empty input"),
            new Test(new int[][]{{5, 5},{1,2},{2,3},{3,4}}, "Nested and single-point intervals")
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            System.out.println("---- " + t.description + " ----");
            System.out.println("Input: " + Arrays.deepToString(t.intervals));
            int[][] output = solver.merge(t.intervals);
            System.out.println("Merged: " + Arrays.deepToString(output));
            System.out.println();
        }
    }
}