import java.util.Arrays;

/**
 * File: TrappingRainWater.java
 *
 * Problem 42. Trapping Rain Water
 *
 * Intuition:
 *   We use two pointers (l and r) starting at the ends of the array. We keep
 *   track of the maximum height encountered so far from the left (leftMax)
 *   and from the right (rightMax). At each step, we move the pointer at the
 *   lower height inward, because the trapped water at that side is limited
 *   by the lower of the two maximums.
 *
 * Approach:
 *   1. Initialize l = 0, r = n-1.
 *   2. Set leftMax = height[l], rightMax = height[r].
 *   3. While l < r:
 *        - Update leftMax = max(leftMax, height[l]).
 *        - Update rightMax = max(rightMax, height[r]).
 *        - If height[l] <= height[r]:
 *            • water trapped at l = min(leftMax, rightMax) - height[l]
 *            • area += that amount; then l++.
 *          Else:
 *            • water trapped at r = min(leftMax, rightMax) - height[r]
 *            • area += that amount; then r--.
 *   4. Return total area.
 *
 * Time Complexity: O(n) — each pointer moves inward exactly n steps total.
 * Space Complexity: O(1) — only a few integer variables are used.
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int size = height.length;
        if (size < 3) return 0;  // too short to trap any water

        int l = 0, r = size - 1;
        int leftMax = height[l], rightMax = height[r];
        int area = 0;

        while (l < r) {
            // update the maxes
            leftMax  = Math.max(leftMax,  height[l]);
            rightMax = Math.max(rightMax, height[r]);

            // trap at the lower side
            if (height[l] <= height[r]) {
                area += Math.min(leftMax, rightMax) - height[l];
                l++;
            } else {
                area += Math.min(leftMax, rightMax) - height[r];
                r--;
            }
        }
        return area;
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();

        int[][] testCases = {
            {0,1,0,2,1,0,1,3,2,1,2,1},  // expect 6
            {4,2,0,3,2,5},              // expect 9
            {5},                        // expect 0
            {0,1,2,3,4},                // expect 0
            {4,3,2,1,0},                // expect 0
            {5,5,5},                    // expect 0
            {2,0,2},                    // expect 2
            {3,0,1,3,0,5}               // expect 8
        };
        int[] expected = {6, 9, 0, 0, 0, 0, 2, 8};

        for (int i = 0; i < testCases.length; i++) {
            int[] height = testCases[i];
            int result = solution.trap(height);
            System.out.printf("Test %d: height=%s → trapped = %d (expected %d)%n",
                i + 1, Arrays.toString(height), result, expected[i]);
        }
    }
}