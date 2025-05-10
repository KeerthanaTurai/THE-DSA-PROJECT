import java.util.Stack;
import java.util.Arrays;

/**
 * Problem 84. Largest Rectangle in Histogram
 *
 * Given an array of integers heights representing the histogram's bar heights
 * where the width of each bar is 1, return the area of the largest rectangle
 * in the histogram.
 *
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 *
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 *
 * Intuition:
 *   Maintain a monotonic increasing stack of bar indices. When the current bar
 *   height is less than the height at the stack's top, we know that the bar
 *   at the top can no longer extend to the right. We pop it and compute the
 *   maximal rectangle with that bar as the limiting height.
 *
 * Approach:
 *   1. Push the index 0 onto stack `hist`.
 *   2. For i from 1 to n-1:
 *        - While stack is not empty and heights[i] < heights[hist.peek()]:
 *            max = getMax(heights, hist, max, i)
 *        - Push i onto hist.
 *   3. After the loop, pop any remaining bars:
 *        while stack not empty:
 *            max = getMax(heights, hist, max, n)
 *   4. `getMax` pops the top index `popped`, computes:
 *        height = heights[popped]
 *        width  = (stack empty) ? i : (i - 1 - stack.peek())
 *      and returns max(max, height * width).
 *
 * Time Complexity: O(n) — each index is pushed and popped at most once.
 * Space Complexity: O(n) — in the worst case the stack holds all indices.
 */
public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> hist = new Stack<>();
        int n = heights.length;
        int max = 0, i;

        if (n == 0) return 0;

        // Start with the first index
        hist.push(0);

        // Process bars from index 1 to n-1
        for (i = 1; i < n; i++) {
            while (!hist.isEmpty() && heights[i] < heights[hist.peek()]) {
                max = getMax(heights, hist, max, i);
            }
            hist.push(i);
        }

        // Pop any remaining bars
        while (!hist.isEmpty()) {
            max = getMax(heights, hist, max, n);
        }
        return max;
    }

    public static int getMax(int[] arr, Stack<Integer> stack, int max, int i) {
        int popped = stack.pop();
        int height = arr[popped];
        int width;
        if (stack.isEmpty()) {
            width = i;
        } else {
            width = i - 1 - stack.peek();
        }
        int area = height * width;
        return Math.max(area, max);
    }

    public static void main(String[] args) {
        LargestRectangleArea sol = new LargestRectangleArea();

        int[][] testCases = {
            {2, 1, 5, 6, 2, 3},    // expect 10
            {2, 4},                // expect 4
            {2, 2, 2, 2},          // expect 8
            {4, 3, 2, 1},          // expect 6
            {},                    // expect 0
            {5}                    // expect 5
        };

        for (int[] heights : testCases) {
            System.out.printf("heights = %s → largest area = %d%n",
                Arrays.toString(heights),
                sol.largestRectangleArea(heights));
        }
    }
}
