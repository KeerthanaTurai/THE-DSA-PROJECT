/*
 * Intuition:
 * =========
 * We want to place k cows in stalls such that the minimum distance between any two cows is maximized.
 * If we fix a candidate distance d, we can greedily check whether it is possible to place all cows
 * by starting at the first stall and placing each subsequent cow in the first stall that is at least d
 * away from the last occupied stall.
 * This "feasibility" check runs in O(n). Since the distance d lies between 1 and (max_stall - min_stall),
 * we can use binary search over d to find the largest d for which the cow placement is possible.
 * 
 * Approach:
 * =========
 * 1. Sort the stall positions.
 * 2. Set the binary search boundaries as:
 *    - low = 1 (minimum possible distance)
 *    - high = stalls[n-1] - stalls[0] (maximum possible distance).
 * 3. For each candidate distance mid, check if we can place k cows using a helper function (canWePlace).
 *    - In canWePlace(), place the first cow at stalls[0] and then iterate through the stalls.
 *      Every time the current stall is at least mid away from the last placed cow, place a cow.
 * 4. If k cows can be placed at distance mid, then try a larger distance by setting low = mid + 1.
 *    Otherwise, try a smaller distance by setting high = mid - 1.
 * 5. When the binary search terminates, high (or low) holds the optimal maximum minimum distance.
 *
 * Complexity:
 * ===========
 * Time Complexity: O(n * log(d)) where d = stalls[n-1] - stalls[0]
 *    - For each binary search iteration (log d iterations) we run a O(n) feasibility check.
 * Space Complexity: O(1)
 *    - Only a fixed number of variables are used.
 */
import java.util.Arrays;
public class AggressiveCows {

    // Helper function to check if we can place k cows with a minimum distance of 'dist' between them.
    public static boolean canWePlace(int[] stalls, int n, int k, int dist) {
        int cow = 1; // Place the first cow at stalls[0]
        int lastPlaced = stalls[0];
        for (int i = 1; i < n; i++) {
            // If current stall is at least 'dist' away from last placed cow, then place a cow here.
            if (stalls[i] - lastPlaced >= dist) {
                cow++;
                lastPlaced = stalls[i];
            }
        }
        return cow >= k;
    }

    // Function to compute the maximum minimum distance (i.e. the optimal "aggressive" arrangement)
    // for placing k cows in the given stalls.
    public static int aggressiveCows(int[] stalls, int k) {
        // Sort the stall positions.
        Arrays.sort(stalls);
        int n = stalls.length;
        
        // The maximum possible distance is between the first and last stall.
        int low = 1, high = stalls[n - 1] - stalls[0];
        //int ans = 0;
        
        // Binary search for the maximum feasible minimum distance.
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canWePlace(stalls, n, k, mid)) {
                //ans = mid;  // Candidate is possible; try for an even larger distance.
                low = mid + 1;
            } else {
                // Candidate distance too high; try a smaller distance.
                high = mid - 1;
            }
        }
        return high;
    }

    // Main function to run various test cases.
    public static void main(String[] args) {
        // Test case 1: Classic example from many sources.
        int[] stalls1 = {1, 2, 4, 8, 9};
        int k1 = 3;  // Expected answer: 3 (e.g., cows at positions 1, 4, 8 or 1,4,9)
        
        // Test case 2: When there are only two cows to be placed.
        int[] stalls2 = {10, 20};
        int k2 = 2;  // Expected answer: 10
        
        // Test case 3: Single stall edge case.
        int[] stalls3 = {5};
        int k3 = 1;  // Expected answer: 0 (only one stall, so no gap)
        
        // Test case 4: Stalls in descending order.
        int[] stalls4 = {20, 15, 10, 5};
        int k4 = 2;  // Expected answer: 15 (largest distance between 5 and 20)
        
        // Test case 5: Provided test case which may be problematic.
        int[] stalls5 = {805306368, 805306368, 805306368};
        int k5 = 3; // All stalls are identical, so maximum minimum distance is 0.
        
        // Organize test cases.
        int[][] testStalls = {stalls1, stalls2, stalls3, stalls4, stalls5};
        int[] kValues = {k1, k2, k3, k4, k5};
        
        // Run each test case.
        for (int i = 0; i < testStalls.length; i++) {
            System.out.println("Test case " + (i + 1) + ": ");
            System.out.println("Stalls: " + Arrays.toString(testStalls[i]));
            System.out.println("Number of Cows (k): " + kValues[i]);
            int result = aggressiveCows(testStalls[i], kValues[i]);
            System.out.println("Maximum minimum distance: " + result);
            System.out.println("-------------------------------------");
        }
    }
}
