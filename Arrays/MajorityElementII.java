import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MajorityElementII {
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> res=new ArrayList<>();
        HashMap<Integer,Integer> countMap=new HashMap<>();
        int i, n=nums.length;
        int l=n/3;
        for(i=0;i<n;i++){
            countMap.put(nums[i], countMap.getOrDefault(nums[i],0)+1);
        }
        for (Integer key : countMap.keySet()) {
            if(countMap.get(key)>l){
                res.add(key);
            }
        }
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
