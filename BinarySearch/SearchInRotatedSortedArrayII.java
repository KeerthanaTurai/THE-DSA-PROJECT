/* Intuition
The input array is a rotated sorted array that may contain duplicates. A standard binary search doesn't work directly because duplicates can make it hard to determine which half is sorted. So, the idea is to use a modified binary search that can handle duplicates by skipping over identical elements on both ends when necessary.

# Approach
1. Initialize two pointers, `low` and `high`, at the start and end of the array. 
2. Use a binary search loop (`while low <= high`) to narrow down the search space. 
3. If `nums[mid] == target`, return `true`. 
4. If `nums[low] == nums[mid] == nums[high]`, increment `low` and decrement `high` to skip duplicates that make it hard to decide the sorted side. 
5. Otherwise, determine whether the left or right half is sorted: - If the left half is sorted (`nums[low] <= nums[mid]`), check if the target lies in that half. - Else, the right half must be sorted — check if the target lies in that half. 
6. Narrow the search range accordingly. 7. If the loop ends without finding the target, return `false`.

# Complexity
- Time complexity: 𝑂(𝑛) In the worst case (e.g. all elements are the same), the algorithm may need to scan every element due to duplicates.
- Space complexity: 𝑂(1) Only a few pointers and variables are used regardless of input size.
*/

public class SearchInRotatedSortedArrayII {
    public static boolean search(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n-1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            // Skip duplicates on both sides if low, mid, and high are equal
            while(low < high && nums[low] == nums[mid] && nums[mid] == nums[high]){
                low++;
                high--;
                continue;
            }
            // Check if mid is the target
            if(nums[mid] == target){
                return true;
            }
            // Left half is sorted
            if(nums[low] <= nums[mid]){
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } 
                else {
                    low = mid + 1;
                }
            }
            // Right half is sorted
            else{
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } 
                else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {7, 8, 1, 2, 3, 3, 3, 4, 5, 6};
        int k = 5;
        boolean ans = search(arr, k);
        if (ans == false)
            System.out.println("Target is not present.");
        else
            System.out.println("Target "+k+" is present in the array.");
    }

}
