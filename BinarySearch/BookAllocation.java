/*
# Intuition
We need to allocate books to k students such that the maximum pages allocated to any student is minimized. 
Intuitively, the minimum possible maximum is at least the number of pages in the largest book (since that book must be allocated to one student), 
and the maximum possible sum of pages is the total pages of all books.
We therefore use binary search over this range (from max(book pages) to sum(book pages)) to find the smallest feasible allocation.

# Approach
1. Compute the sum and maximum of the pages in the array using the helper function `sumAndMaxElement`:
   - low bound = maximum number of pages in a single book.
   - high bound = sum of all pages.
2. Use binary search in the range [low, high]:
   - For each middle value `mid`, use the helper function `canAllocate` to check if it is feasible to allocate books such that no student reads more than `mid` pages.
   - If feasible, try to lower the maximum by adjusting the binary search to the left half.
   - Otherwise, move to the right half.
3. When the binary search concludes, `low` is the minimum possible maximum allocation.

# Complexity
- Time complexity:
  O(n \log(S)) 
  where n is the number of books and S is the total number of pages.  
  (Binary search requires O(log(S)) iterations, and each iteration processes n books.)
- Space complexity:
   O(1) 
  (We use a constant amount of extra space.)
*/

public class BookAllocation {

    // Utility function to find the sum and maximum element in an array.
    public static int[] sumAndMaxElement(int[] nums, int n) {
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            sum += nums[i];
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return new int[] { sum, max };
    }

    // Function to check if it is possible to allocate books to 'k' students such that
    // no student reads more than 'pages' pages.
    public static boolean canAllocate(int[] arr, int n, int k, int pages) {
        int student = 1, stud_pages = 0;
        for (int i = 0; i < n; i++) {
            if (stud_pages + arr[i] <= pages) {
                stud_pages += arr[i];
            } else {
                student++;
                stud_pages = arr[i];
            }
        }
        return student <= k;
    }

    // Function to find the minimum number of pages such that books can be allocated to 'k' students
    // while minimizing the maximum pages allocated to a student.
    public static int findPages(int[] arr, int k) {
        int n = arr.length;
        if (k > n) return -1;

        int[] res = sumAndMaxElement(arr, n);
        int low = res[1];  // Maximum element: lowest possible max allocation
        int high = res[0]; // Sum of all elements: highest possible max allocation

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canAllocate(arr, n, k, mid)) {
                high = mid - 1; // Try a smaller value
            } else {
                low = mid + 1;  // Need a larger value
            }
        }
        return low;
    }

    // Main function covering different test cases.
    public static void main(String[] args) {
        // Test Case 1
        int[] arr1 = {12, 34, 67, 90};
        int k1 = 2;
        int result1 = findPages(arr1, k1);
        System.out.println("Test Case 1: Minimum pages allocated = " + result1);
        // Expected Output: 113

        // Test Case 2
        int[] arr2 = {10, 20, 30, 40};
        int k2 = 2;
        int result2 = findPages(arr2, k2);
        System.out.println("Test Case 2: Minimum pages allocated = " + result2);
        // Expected Output: 60

        // Test Case 3: When the number of students is more than the number of books.
        int[] arr3 = {15, 17, 20};
        int k3 = 4;
        int result3 = findPages(arr3, k3);
        System.out.println("Test Case 3: Minimum pages allocated = " + result3);
        // Expected Output: -1

        // Test Case 4: Single book and single student.
        int[] arr4 = {100};
        int k4 = 1;
        int result4 = findPages(arr4, k4);
        System.out.println("Test Case 4: Minimum pages allocated = " + result4);
        // Expected Output: 100

        // Test Case 5: Multiple books with students equal to number of books.
        int[] arr5 = {5, 5, 5, 5};
        int k5 = 4;
        int result5 = findPages(arr5, k5);
        System.out.println("Test Case 5: Minimum pages allocated = " + result5);
        // Expected Output: 5
    }
}
