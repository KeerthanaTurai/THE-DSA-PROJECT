/*
# Intuition
We need to determine the minimum eating speed (bananas per hour) such that Koko can finish all piles within a given number of hours (h). 
The maximum pile size gives us an upper bound for the possible speeds. With a candidate eating speed, we can calculate the total hours needed by summing the ceiling of each pile divided by that candidate. 
Using binary search on this range (from 1 to max pile size) allows us to efficiently narrow down to the smallest speed that allows Koko to finish within h hours.

# Approach
1. Determine the maximum number of bananas in any pile, which acts as our upper bound.
2. Set the search boundaries for eating speed: low = 1 and high = max pile.
3. Use binary search:
   - For each candidate speed (mid), calculate the total hours required by summing up Math.ceil(pile / mid) for each pile.
   - If the total hours are less than or equal to h, try to find a smaller speed by adjusting the right boundary.
   - If the total hours exceed h, increase the candidate speed by adjusting the left boundary.
4. Continue until the search converges, and return the smallest speed that works.

# Complexity
- Time complexity: O(n * log(m))
  (Binary search runs in O(log(m)) iterations, and each iteration processes all n piles.)
- Space complexity: O(1)
  (Only a few extra variables are used, independent of input size.)
*/

public class KokoEatingBananas {

    // Utility function to find the maximum element in the array.
    public static int max(int[] piles, int n) {
        int maxEle = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (piles[i] > maxEle) {
                maxEle = piles[i];
            }
        }
        return maxEle;
    }

    // Function to compute the minimum eating speed so that Koko can finish all bananas in h hours.
    public static int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int m = max(piles, n);

        // If the number of piles equals h, Koko must eat a whole pile per hour, requiring the maximum pile size.
        if (n == h) return m;

        int low = 1, high = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int hours = 0;
            for (int i = 0; i < n; i++) {
                // Calculate hours needed for current pile with speed 'mid'
                hours += Math.ceil((double) piles[i] / mid);
            }
            // If Koko can finish in the allotted hours, try a slower speed.
            if (hours <= h) {
                high = mid - 1;
            } else {
                // Otherwise, if she can't, increase the speed.
                low = mid + 1;
            }
        }
        // 'low' is the minimum valid eating speed.
        return low;
    }

    // Main function to run sample test cases.
    public static void main(String[] args) {
        // Test case 1:
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        int result1 = minEatingSpeed(piles1, h1);
        System.out.println("Minimum eating speed for piles {3, 6, 7, 11} with h=8 is: " + result1);
        // Expected output: 4

        // Test case 2:
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        int result2 = minEatingSpeed(piles2, h2);
        System.out.println("Minimum eating speed for piles {30, 11, 23, 4, 20} with h=5 is: " + result2);
        // Expected output: 30

        // Test case 3:
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        int result3 = minEatingSpeed(piles3, h3);
        System.out.println("Minimum eating speed for piles {30, 11, 23, 4, 20} with h=6 is: " + result3);
        // Expected output: 23
    }
}
