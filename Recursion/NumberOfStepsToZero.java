/**
 * Problem 1342. Number of Steps to Reduce a Number to Zero
 *
 * Given an integer num, return the number of steps to reduce it to zero.
 *
 * In one step, if the current number is even, you have to divide it by 2;
 * otherwise, you have to subtract 1 from it.
 *
 * Example 1:
 * Input: num = 14
 * Output: 6
 * Explanation:
 * 14 → 7 → 6 → 3 → 2 → 1 → 0  (6 steps)
 *
 * Example 2:
 * Input: num = 8
 * Output: 4
 * Explanation:
 * 8 → 4 → 2 → 1 → 0  (4 steps)
 *
 * Example 3:
 * Input: num = 123
 * Output: 1
 *
 * Intuition:
 *   At each step we either halve the number if it’s even, or subtract one if it’s odd.
 *   We simply count how many operations until we reach zero.
 *
 * Approach:
 *   1. Base case: if num == 0, return 0.
 *   2. If num is even, return 1 + numberOfSteps(num / 2).
 *   3. Otherwise (odd), return 1 + numberOfSteps(num - 1).
 *
 * Time Complexity: O(k), where k is the number of steps (≤ O(log num) for even divisions plus
 *                  subtractions for odd numbers).
 * Space Complexity: O(k) for recursion stack (or O(1) if converted to an iterative loop).
 */
public class NumberOfStepsToZero {

    public int numberOfSteps(int num) {
        if (num == 0) return 0;
        if (num % 2 == 0) {
            return 1 + numberOfSteps(num / 2);
        } else {
            return 1 + numberOfSteps(num - 1);
        }
    }

    public static void main(String[] args) {
        NumberOfStepsToZero solver = new NumberOfStepsToZero();
        int[] tests = {14, 8, 123};

        for (int n : tests) {
            int steps = solver.numberOfSteps(n);
            System.out.printf("numberOfSteps(%d) = %d%n", n, steps);
        }
    }
}
