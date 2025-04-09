import java.util.HashMap;

public class LargestSubarray {
    public static int maxLen(int arr[]) {
        // code here
        HashMap<Integer,Integer> pre_sum=new HashMap<>();
        int i, n=arr.length, max_length=0, prefix=0;
        for(i=0; i<n; i++){
            prefix+=arr[i];
            if(prefix==0){
                if((i+1) > max_length){
                    max_length=i+1;
                }
            }
            int t=pre_sum.getOrDefault(prefix,-1);
            if(t==-1){
                pre_sum.put(prefix,i);
            }
            else{
                int temp=i-t;
                if(max_length<temp){
                    max_length=temp;
                }
            }
        }
        return max_length;
    }

    public static void main(String[] args) throws Exception {
        int[] arr={15, -2, 2, -8, 1, 7, 10, 23};
        System.out.println(maxLen(arr));
    }
}
