import java.util.ArrayList;
import java.util.HashMap;

public class SubarrayWithXorK {
    public static int subarrayWithXorK(ArrayList<Integer> A, int B) {
        int n = A.size();   
        // HashMap to store prefix XOR frequencies
        HashMap<Integer, Integer> xor_sum = new HashMap<>();
        // Initial prefix XOR of 0 has occurred once (important base case)
        xor_sum.put(0, 1);
        int count = 0; // To store the total count of valid subarrays
        int xor = 0;   // Prefix XOR
        // Iterate through the array
        for (int i = 0; i < n; i++) {
            // Update the running XOR of the subarray ending at index i
            xor = xor ^ A.get(i);
            // x is the value such that xor ^ x = B => x = xor ^ B
            // If x has been seen as a prefix XOR before, it means there's a subarray ending at i with XOR = B
            int x = xor ^ B;
            // Add the number of times x has appeared to the count
            count += xor_sum.getOrDefault(x, 0);
            // Update the frequency of the current prefix XOR in the map
            xor_sum.put(xor, xor_sum.getOrDefault(xor, 0) + 1);
        }
        // Return the total count of subarrays with XOR equal to B
        return count;
    }
    
    public static void main(String[] args) {
        ArrayList<Integer> a= new ArrayList<>();
        a.add(4);
        a.add(2);
        a.add(2);
        a.add(6);
        a.add(4);
        int k = 6;
        int ans = subarrayWithXorK(a, k);
        System.out.println("The number of subarrays with XOR k is: " + ans);
    }
}
