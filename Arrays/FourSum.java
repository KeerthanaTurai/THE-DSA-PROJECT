import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n=nums.length;
        int i=0,j,k,l;
        Arrays.sort(nums);
        for(i=0;i<n;i++){
        // Skip duplicates for the first element
            if(i!=0 && nums[i]==nums[i-1]){
                continue;
            }
            for(j=i+1;j<n;j++){
            // Skip duplicates for the second element
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                k=j+1;
                l=n-1;
                while(k<l){
                    // Use long to prevent integer overflow
                    long t = (long)nums[i]+nums[j]+nums[k]+nums[l];
                    if(t==target){
                        List<Integer> temp = Arrays.asList(nums[i],nums[j],nums[k],nums[l]);
                        res.add(temp);
                        k++;
                        l--;
                        // Skip duplicates for the third element
                        while (k < l && nums[k] == nums[k - 1]) k++;
                        // Skip duplicates for the fourth element
                        while (k < l && nums[l] == nums[l + 1]) l--;
                    }
                    else{
                        // If the sum is greater than target, decrease it by moving the right pointer
                        if (t > target) {
                            l--;
                        } else {
                        // If the sum is less than target, increase it by moving the left pointer
                            k++;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 3, 4, 4, 2, 1, 2, 1, 1};
        int target = 9;
        List<List<Integer>> ans = fourSum(nums, target);
        System.out.println("The quadruplets are: ");
        for (List<Integer> it : ans) {
            System.out.print("[");
            for (int ele : it) {
                System.out.print(ele + " ");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}
