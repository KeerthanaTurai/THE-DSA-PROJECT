import java.util.HashMap;

public class SubArraySum {
    public static int subarraySumCount(int[] nums, int k) {
        int n=nums.length;
        int i,preSum=0,count=0,remove;
        HashMap<Integer,Integer> preSumMap=new HashMap<>();
        preSumMap.put(0,1);
        for(i=0;i<n;i++){
            preSum+=nums[i];
            remove=preSum-k;
            count+=preSumMap.getOrDefault(remove,0);
            preSumMap.put(preSum, preSumMap.getOrDefault(preSum,0)+1);
        }
        return count;
    }
    public static void main(String args[]){
        int[] arr = {3, 1, 2, 4, -1, -2, 6};
        int k = 6;
        int cnt = subarraySumCount(arr, k);
        System.out.println("The number of subarrays is: " + cnt);
    }
}