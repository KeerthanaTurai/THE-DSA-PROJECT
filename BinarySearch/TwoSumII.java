import java.util.Arrays;

/**
 * Problem 167. Two Sum II - Input Array Is Sorted
 *
 * Intuition:
 *   Because the array is already sorted in non-decreasing order, we can use
 *   the two-pointer technique: one pointer starts at the beginning (smallest
 *   values) and one at the end (largest values). Moving these pointers based
 *   on the current sum lets us find the target in linear time.
 *
 * Approach:
 *   1. Initialize two pointers, i = 0 and j = numbers.length - 1.
 *   2. While i < j:
 *        - Compute sum = numbers[i] + numbers[j].
 *        - If sum == target, return [i+1, j+1] (1-indexed).
 *        - If sum > target, decrement j to reduce the sum.
 *        - Else (sum < target), increment i to increase the sum.
 *   3. If no valid pair is found (though the problem guarantees one), return [-1, -1].
 *
 * Time Complexity: O(n), since each pointer moves at most n steps.
 * Space Complexity: O(1), only constant extra space for indices and sum.
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[] { i + 1, j + 1 };
            }
            if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[] { -1, -1 };
    }

    private static void printResult(int[] res) {
        System.out.println(Arrays.toString(res));
    }

    public static void main(String[] args) {
        TwoSumII solver = new TwoSumII();

        int[][] testNums = {
            {2, 7, 11, 15},    // example 1
            {2, 3, 4},         // example 2
            {-1, 0},           // example 3
            {1, 2, 3, 4, 5},   // custom: target at ends
            {1, 5, 5, 6, 7}    // custom: duplicate values
        };
        int[] targets = {
            9,   // expects [1,2]
            6,   // expects [1,3]
            -1,  // expects [1,2]
            6,   // expects [1,5] because 1+5=6
            10   // expects [2,3] because 5+5=10
        };

        for (int t = 0; t < testNums.length; t++) {
            System.out.printf("Test %d: numbers=%s, target=%d -> ",
                              t + 1, Arrays.toString(testNums[t]), targets[t]);
            int[] result = solver.twoSum(testNums[t], targets[t]);
            printResult(result);
        }
    }
}