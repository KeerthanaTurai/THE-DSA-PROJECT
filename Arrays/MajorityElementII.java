/*
 * Intuition:
 * ============
 * In any array of size n, there can be at most 2 elements that appear more than ⌊ n/3 ⌋ times.
 * Instead of using extra space (like a HashMap) to count frequencies, we can use the Boyer-Moore
 * voting algorithm extension to select at most two candidates that may be the majority elements.
 * Then, we verify these candidates by counting their occurrences.
 *
 * Approach:
 * ===========
 * 1. First Pass (Candidate Selection):
 *    - Initialize two candidate variables (ele1 and ele2) and their counts (c1 and c2).
 *    - Iterate through the array:
 *         - If the current number equals ele1, increment c1.
 *         - Else if it equals ele2, increment c2.
 *         - Else if c1 is 0, set ele1 to the current number and c1 to 1.
 *         - Else if c2 is 0, set ele2 to the current number and c2 to 1.
 *         - Otherwise, decrement both c1 and c2.
 *
 * 2. Second Pass (Validation):
 *    - Reset c1 and c2 to 0.
 *    - Count the occurrences of ele1 and ele2.
 *    - If a candidate appears more than ⌊ n/3 ⌋ times, add it to the result list.
 *
 * Complexity:
 * ============
 * Time Complexity: O(n)
 *    - The algorithm makes two passes through the array.
 *
 * Space Complexity: O(1)
 *    - Only a fixed number of extra variables are used.
 */

import java.util.*;
public class MajorityElementII {

    public static List<Integer> majorityElement(int[] nums) {
        // List to store elements that appear more than n/3 times.
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        
        // Initialize two candidate variables and their corresponding counts.
        int ele1 = Integer.MIN_VALUE, ele2 = Integer.MIN_VALUE;
        int c1 = 0, c2 = 0;
        
        // First pass: Candidate selection using the extended Boyer-Moore algorithm.
        for (int i = 0; i < n; i++) {
            if (nums[i] == ele1) {
                c1++;
            } else if (nums[i] == ele2) {
                c2++;
            } else if (c1 == 0) {
                ele1 = nums[i];
                c1 = 1;
            } else if (c2 == 0) {
                ele2 = nums[i];
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }
        
        // Second pass: Verify the candidates by counting their occurrences.
        c1 = 0;
        c2 = 0;
        for (int num : nums) {
            if (num == ele1) {
                c1++;
            } else if (num == ele2) {
                c2++;
            }
        }
        
        // If a candidate appears more than n/3 times, add it to the result list.
        if (c1 > n / 3) {
            res.add(ele1);
        }
        if (c2 > n / 3) {
            res.add(ele2);
        }
        
        return res;
    } 

    public static void main(String[] args) {
        int[][] testCases = {
            {3, 2, 3},                // Expected output: [3]
            {1, 1, 1, 3, 3, 2, 2, 2},   // Expected output: [1, 2] (both appear 3 times when n = 8)
            {1, 2, 3},                // Expected output: [] (each appears once; none appear more than ⌊3/3⌋ = 1 time, if strictly > n/3)
            {1, 2, 2, 3, 2},          // Expected output: [2]
            {1, 1, 1, 2, 3, 4},       // Expected output: [1]
            {2, 2, 2, 2}              // Expected output: [2]
        };
        
        int testCaseNum = 1;
        for (int[] test : testCases) {
            System.out.println("Test Case " + testCaseNum++ + ": " + Arrays.toString(test));
            List<Integer> result = majorityElement(test);
            System.out.println("Majority Elements (> n/3 times): " + result);
            System.out.println("---------------------------");
        }
    }

    // below functions works well, but it take 0(n) space
    // public static List<Integer> majorityElement(int[] nums) {
    //     // List to store elements that appear more than n/3 times
    //     List<Integer> res = new ArrayList<>();

    //     // HashMap to count the frequency of each element
    //     HashMap<Integer, Integer> countMap = new HashMap<>();

    //     int n = nums.length;
    //     int l = n / 3; // Threshold: element must appear more than n/3 times

    //     // Count the frequency of each element in the array
    //     for (int i = 0; i < n; i++) {
    //         countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
    //     }

    //     // Check which elements appear more than n/3 times and add them to the result
    //     for (Integer key : countMap.keySet()) {
    //         if (countMap.get(key) > l) {
    //             res.add(key);
    //         }
    //     }

    //     // Return the list of majority elements
    //     return res;    
    // }
}
