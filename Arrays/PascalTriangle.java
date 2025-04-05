import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {   
    public static List<List<Integer>> pascalTriangle(int numRows) {
        int i,j;
        List<List<Integer>> result = new ArrayList<>();
        for(i=1; i<=numRows; i++){
            List<Integer> l = new ArrayList<>();
            l.add(1);
            int temp=1;
            for(j=1; j<i; j++){
                temp=temp*(i-j);
                temp=temp/(j);
                l.add(temp);
            }
            result.add(l);
        }
        return result;
    }
    public static void main(String args[]){
        int n = 5;
        List<List<Integer>> ans = new ArrayList<>();
        ans=pascalTriangle(n);
        for (List<Integer> it : ans) {
            for (int ele : it) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
