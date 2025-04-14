/* Intuition
In a perfect array from 1 to n, the sum and sum of squares of elements follow known formulas. When one number is missing and one number is repeated, the total sum and square sum of the array will deviate from the expected values. By comparing the expected vs actual sums and solving two equations, we can derive the values of the missing and repeating numbers. -->
We know: 
Sum of first n natural numbers: 𝑆𝑛 = n(n+1)/2
​Sum of squares of first n natural numbers: n(n+1)(2n+1)/6
​
Let:
x = missing number
y = repeating number

Then:
actual_sum - S_n = y - x
actual_sq_sum - S_{n^2} = y^2 - x^2 = (y - x)(y + x)
Using these two equations, we solve for x and y.

Approach
1. Calculate the actual sum and sum of squares of the given array. 
2. Calculate the expected sum (`S_n`) and square sum (`S_n2`) using formulas. 
3. Let `diff1 = sum_actual - S_n = y - x` 
4. Let `diff2 = sq_sum_actual - S_n2 = y^2 - x^2 = (y - x)(y + x)` 
5. From the two equations: - `(y + x) = diff2 / diff1` 
   - Solving: `y = (diff1 + diff2/diff1) / 2` 
    `x = y - diff1` 
    6. Return `[missing, repeating]`.

Complexity
- Time complexity: O(n) Only one pass through the array is needed to compute the actual sum and square sum.
- Space complexity: O(1) No extra space is used beyond a few variables — everything is computed in-place. 
*/

import java.util.Arrays;

public class MissingRepeatingNumber {
    public static int[] findMissingRepeatingNumbers(int[] nums) {
        int n=nums.length;
        long arr_sum = 0;
        long sn = (n*(n+1))/2;
        long arr_sq_sum = 0; 
        long sn2 = (n*(n+1)*(2*n+1))/6;
        for(int i=0;i<n;i++){
            arr_sum += nums[i];
            arr_sq_sum += (nums[i]*nums[i]);
        } 
        int var1 = (int)(arr_sum - sn); // x-y=sum of arr - sum of n natural numbers
        int var2 = (int)(arr_sq_sum - sn2)/var1; // x+y = (sum of squares of arr - sum of squares of n natural numbers)/(x-y)
        // adding both the equations we get 2x 
        int x=(var1 - var2)/2; // missing value
        int y=x-(var1); // repeating value
        return new int[] {Math.abs(x),Math.abs(y)};
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {1, 2, 2, 4},                // Basic case
            {3, 1, 3},                   // Repeating in middle
            {1, 1},                      // Minimal input
            {4, 3, 6, 2, 1, 1},          // Larger input
            {5, 3, 2, 1, 5},             // Repeating at end
            {2, 2},                      // Missing is 1
            {1, 2, 3, 4, 4},             // Missing is 5
            {4, 2, 1, 3, 4},             // Repeating at end, missing 5
            {1, 3, 3, 4, 5},             // Missing is 2
        };

        for (int[] test : testCases) {
            System.out.println("Input: " + Arrays.toString(test));
            int[] result = findMissingRepeatingNumbers(test);
            System.out.println("Repeating number: " + result[1]);
            System.out.println("Missing number: " + result[0] + "\n");
        }
    }
}
