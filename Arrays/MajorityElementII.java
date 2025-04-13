/*
 # Complexity
- Time Complexity: O(n)
    - The loop to populate the countMap runs once over all n elements → O(n). 
    - The loop to check which keys have count > n/3 iterates over the keys in the map: In the worst case, all elements are unique, so this loop also runs O(n).Therefore, the overall time complexity is: O(n + n) = O(n)

- Space Complexity: O(n)
    - The countMap can store up to n unique keys in the worst case (if all elements are different)
    - The res list can store at most 2 elements (since there can be at most 2 elements that appear more than ⌊n/3⌋ times), but this is still considered O(1) additional space
    - Dominated by the map, so total space complexity is: O(n)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MajorityElementII {
    public static List<Integer> majorityElement(int[] nums) {
        // List to store elements that appear more than n/3 times
        List<Integer> res = new ArrayList<>();

        // HashMap to count the frequency of each element
        HashMap<Integer, Integer> countMap = new HashMap<>();

        int n = nums.length;
        int l = n / 3; // Threshold: element must appear more than n/3 times

        // Count the frequency of each element in the array
        for (int i = 0; i < n; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        // Check which elements appear more than n/3 times and add them to the result
        for (Integer key : countMap.keySet()) {
            if (countMap.get(key) > l) {
                res.add(key);
            }
        }

        // Return the list of majority elements
        return res;    
    }
    public static void main(String args[]) {
        int[] arr = {3,2,3,3,1,1,7,1};
        List<Integer> ans = majorityElement(arr);
        System.out.print("The majority elements are: ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();
    }
}
