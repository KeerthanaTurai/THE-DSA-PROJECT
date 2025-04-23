/**
     * Finds the length of the largest subarray with sum equal to zero.
     *
     * Intuition:
     *   Keep track of prefix sums. If the same prefix sum appears twice at
     *   indices i and j, then the subarray (i+1 … j) sums to zero. Also, if
     *   a prefix sum equals zero at index j, then the subarray (0 … j) sums to zero.
     *
     * Approach:
     *   1. Use a HashMap to record the first occurrence index of each prefix sum.
     *   2. Iterate over the array, accumulating prefix sum.
     *   3. If prefix == 0, update maxLength = i + 1.
     *   4. Else if prefix has been seen before at index prev, update
     *        maxLength = max(maxLength, i - prev).
     *      If prefix is new, store prefix → i in the map.
     *   5. Return maxLength.
     *
     * Time Complexity: O(n) — single pass with O(1) map operations.
     * Space Complexity: O(n) — the map may store up to n distinct prefix sums.
**/
import java.util.Arrays;
import java.util.HashMap;

public class LargestSubarray {
    public static int maxLen(int arr[]) {
        // code here
        HashMap<Integer,Integer> pre_sum=new HashMap<>();
        int i, n=arr.length, max_length=0, prefix=0;
        for(i=0; i<n; i++){
            prefix+=arr[i];
            if(prefix==0){
                if((i+1) > max_length){
                    max_length=i+1;
                }
            }
            int t=pre_sum.getOrDefault(prefix,-1);
            if(t==-1){
                pre_sum.put(prefix,i);
            }
            else{
                int temp=i-t;
                if(max_length<temp){
                    max_length=temp;
                }
            }
        }
        return max_length;
    }

   public static void main(String[] args) {
        int[][] testCases = {
            {15, -2, 2, -8, 1, 7, 10, 23},  // expected 5
            {1, 2, 3},                      // expected 0
            {1, -1, 3},                     // expected 2
            {0, 0, 0, 0},                   // expected 4
            {}                              // expected 0
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] arr = testCases[i];
            System.out.println("Test " + (i + 1) + ": " + Arrays.toString(arr));
            int length = maxLen(arr);
            System.out.println("Largest zero‑sum subarray length = " + length);
            System.out.println();
        }
   }
}
