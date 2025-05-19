/**
 * Problem 16. 3Sum Closest
 *
 * Given an integer array nums of length n and an integer target, find three
 * integers in nums such that the sum is closest to target. Return the sum of
 * the three integers. You may assume that each input would have exactly one solution.
 *
 * Intuition:
 *   Sort the array and use a two-pointer approach for each fixed i. By moving
 *   the left (j) or right (k) pointer inward depending on whether the current
 *   sum is less or greater than target, we efficiently zero in on the closest sum.
 *
 * Approach:
 *   1. Sort nums.
 *   2. Initialize diff = |nums[0] + nums[1] + nums[n-1] - target| and
 *      close_sum = nums[0] + nums[1] + nums[n-1].
 *   3. For i from 0 to n-3:
 *        a. Let j = i+1, k = n-1.
 *        b. While j < k:
 *             - t = nums[i] + nums[j] + nums[k].
 *             - If t == target, return t immediately.
 *             - If t < target:
 *                 • If (target - t) < diff, update diff and close_sum = t.
 *                 • j++.
 *             - Else (t > target):
 *                 • If (t - target) < diff, update diff and close_sum = t.
 *                 • k--.
 *   4. Return close_sum.
 *
 * Time Complexity: O(n^2) — outer loop over i and inner two-pointer scan.
 * Space Complexity: O(log n) or O(n) depending on sorting implementation.
 */
import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int diff = Math.abs(nums[0] + nums[1] + nums[n - 1] - target);
        int close_sum = nums[0] + nums[1] + nums[n - 1];
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int t = nums[i] + nums[j] + nums[k];
                if (t == target) {
                    return t;
                } else if (t < target) {
                    int d = target - t;
                    if (d < diff) {
                        diff = d;
                        close_sum = t;
                    }
                    j++;
                } else {
                    int d = t - target;
                    if (d < diff) {
                        diff = d;
                        close_sum = t;
                    }
                    k--;
                }
            }
        }
        return close_sum;
    }

    public static void main(String[] args) {
        ThreeSumClosest solver = new ThreeSumClosest();
        int[][] testArrays = {
            {-1, 2, 1, -4},
            {0, 0, 0},
            {1, 1, -1, -1, 3},
            {1, 2, 3, 4},
            {-3, -2, -5, 3, -4},
            {5, 2, 7, 1, 6}
        };
        int[] targets = {1, 1, 1, 1, -1, 10};
        int[] expected = {2, 0, 1, 6, -4, 14};

        for (int i = 0; i < testArrays.length; i++) {
            int result = solver.threeSumClosest(testArrays[i], targets[i]);
            System.out.printf(
                "Test %d: nums=%s, target=%d → closest sum = %d (expected %d)%n",
                i + 1,
                Arrays.toString(testArrays[i]),
                targets[i],
                result,
                expected[i]
            );
        }
    }
}