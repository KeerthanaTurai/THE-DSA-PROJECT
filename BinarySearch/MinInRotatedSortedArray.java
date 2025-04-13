import java.util.Arrays;

public class MinInRotatedSortedArray {
    public static int findMin(int[] nums) {
        int n=nums.length;
        int low=0, high=n-1;
        while(low<high){
            int mid = low + (high - low) / 2;
            // Minimum must be in the unsorted part
            if (nums[mid] > nums[high]) {
                // Pivot (and hence the minimum) is in the right half
                low = mid + 1;
            } else {
                // Pivot is in the left half (including mid)
                high = mid;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {4, 5, 6, 7, 0, 1, 2},    // rotated
            {3, 4, 5, 1, 2},          // rotated
            {11, 13, 15, 17},         // not rotated
            {2, 1},                   // rotated small
            {1},                      // single element
            {1, 2},                   // sorted
            {2, 3, 4, 5, 1},          // rotated at end
            {5, 1, 2, 3, 4}           // rotated at beginning
        };
        for (int[] test : testCases) {
            System.out.println("Input: " + Arrays.toString(test));
            int min = findMin(test);
            System.out.println("Minimum element: " + min + "\n");
        }
    }
}
