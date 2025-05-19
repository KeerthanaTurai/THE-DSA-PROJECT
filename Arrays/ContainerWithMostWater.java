import java.util.*;

/**
 * Problem 11. Container With Most Water
 *
 * You are given an integer array height of length n. There are n vertical lines
 * drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the
 * container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * You may not slant the container.
 *
 * Example 1:
 *   Input: height = [1,8,6,2,5,4,8,3,7]
 *   Output: 49
 *
 * Example 2:
 *   Input: height = [1,1]
 *   Output: 1
 *
 * Intuition:
 *   The width between two lines decreases as we move pointers toward each
 *   other, so to maximize area = min(height[i], height[j]) * (j - i), we
 *   use a two-pointer approach. We start with the widest container (ends of
 *   the array) and move the pointer at the shorter line inward, since moving
 *   the taller line cannot increase the area.
 *
 * Approach:
 *   1. Initialize two pointers, i = 0 (left) and j = n - 1 (right).
 *   2. While i < j:
 *        - Compute cur_area = min(height[i], height[j]) * (j - i).
 *        - Update maxArea if cur_area is larger.
 *        - Move the pointer at the shorter line inward:
 *            • If height[i] < height[j], i++;
 *            • Else j--;
 *   3. Return maxArea.
 *
 * Time Complexity: O(n), each pointer moves at most n steps.
 * Space Complexity: O(1), only a few variables are used.
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int n = height.length;
        if (n == 0) return 0;
        if (n == 2) {
            // Only two lines, area is height of shorter * width = 1
            return Math.min(height[0], height[1]);
        }
        int maxArea = 0;
        int i = 0, j = n - 1;
        int cur_area;
        while (i < j) {
            if (height[i] < height[j]) {
                cur_area = height[i] * (j - i);
                i++;
            } else {
                cur_area = height[j] * (j - i);
                j--;
            }
            if (maxArea < cur_area) {
                maxArea = cur_area;
            }
        }
        return maxArea;
    }

    // Simple test harness
    public static void main(String[] args) {
        ContainerWithMostWater solver = new ContainerWithMostWater();
        List<int[]> tests = Arrays.asList(
            new int[]{1,8,6,2,5,4,8,3,7},   // example 1 → 49
            new int[]{1,1},               // example 2 → 1
            new int[]{4,3,2,1,4},         // symmetric → 16
            new int[]{1,2,1},             // peak in middle → 2
            new int[]{2,3,10,5,7,8,9},    // increasing to peak → best at ends j
            new int[]{},                  // empty → 0
            new int[]{5},                 // single line → 0
            new int[]{5,4,3},             // decreasing → 8
            new int[]{3,4,3,4},           // multiple peaks → 12
            new int[]{100000,1,100000}    // large values → 200000
        );

        for (int k = 0; k < tests.size(); k++) {
            int[] height = tests.get(k);
            int result = solver.maxArea(height);
            System.out.printf("Test %d: height=%s → maxArea = %d%n",
                k + 1, Arrays.toString(height), result);
        }
    }
}
