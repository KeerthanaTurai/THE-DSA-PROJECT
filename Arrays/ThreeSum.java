/*
# Intuition
To solve the problem, we first sort the array so that we can efficiently skip duplicate values and use the two-pointer technique. 
The idea is to fix one number and then look for two other numbers in the remaining part of the array such that their sum equals the negative of the fixed number.

# Approach
1. Sort the array.
2. Loop through the array and fix one element.
3. Use two pointers (one from the left and one from the right) to find the two remaining numbers that sum to zero with the fixed element.
4. Skip duplicates to avoid repeated triplets.

# Complexity
- Time complexity: O(n^2)
- Space complexity: O(1) (ignoring the space for output)
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        // List to store the result triplets
        List<List<Integer>> res = new ArrayList<>();
        
        // Sort the array to facilitate two-pointer approach and skip duplicates
        Arrays.sort(nums);
        int n = nums.length;
        int i, j, k;
        
        // Iterate through the array, fixing one element at a time
        for (i = 0; i < n; i++) {
            // Skip duplicate elements to avoid duplicate triplets
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // Initialize two pointers: j is the next element, k is at the end
            j = i + 1;
            k = n - 1;
            while (j < k) {
                // Calculate the sum of the triplet
                int t = nums[i] + nums[j] + nums[k];
                if (t == 0) {
                    // Found a valid triplet, add it to the result list
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k]);
                    res.add(temp);
                    j++;
                    k--;    
                    // Skip duplicate elements for j
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    // Skip duplicate elements for k
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else {
                    // Adjust pointers based on the sum
                    if (t > 0) {
                        k--; // Sum too large, decrease it by moving k left
                    } else {
                        j++; // Sum too small, increase it by moving j right
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = { -1, 0, 1, 2, -1, -4, -3, 2};
        List<List<Integer>> ans = threeSum(arr);
        for (List<Integer> it : ans) {
            System.out.print("[");
            for (Integer i : it) {
                System.out.print(i + " ");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}
