public class MergeSort {
    public static void divide(int[] nums,int low,int high){
        if(low>=high) return;
        int mid = low+(high-low)/2;
        divide(nums, low, mid);
        divide(nums, mid+1, high);
        merge(nums, low, mid, high);
    }

    public static void merge(int[] nums, int low, int mid, int high){
        int i=low, j=mid+1, k=0;
        int[] temp = new int[high - low + 1];
        while(i<=mid && j<=high){
            if(nums[i]<=nums[j]){
                temp[k]=nums[i];
                k++;
                i++;
            }
            else{
                temp[k]=nums[j];
                k++;
                j++;
            }
        }
        while(i<=mid){
            temp[k]=nums[i];
            k++;
            i++;
        }
        while(j<=high){
            temp[k]=nums[j];
            k++;
            j++;
        }
        for (int p = 0; p < temp.length; p++) {
            nums[low + p] = temp[p];
        }
    }

    public static void main(String args[]) {
        int n = 7;
        int arr[] = { 9, 4, 7, 6, 3, 1, 5 };
        System.out.println("Before sorting array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        divide(arr, 0, n - 1);
        System.out.println("After sorting array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
