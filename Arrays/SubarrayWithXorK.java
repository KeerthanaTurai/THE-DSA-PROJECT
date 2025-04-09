import java.util.ArrayList;
import java.util.HashMap;

public class SubarrayWithXorK {
    public static int subarrayWithXorK(ArrayList<Integer> A, int B) {
        int n=A.size();
        HashMap<Integer, Integer> xor_sum = new HashMap<>();
        xor_sum.put(0,1);
        int x,count=0, xor=0;
        for(int i=0;i<n;i++){
            xor = xor ^ A.get(i);
            x = xor ^ B;
            int t = xor_sum.getOrDefault(x,0);
            count += t;
            xor_sum.put(xor, xor_sum.getOrDefault(xor, 0) + 1);
        }
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
