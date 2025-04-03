import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        int m=matrix.length;
        int n=matrix[0].length;
        int i,top=0,bottom=m-1,right=n-1,left=0;
        while(top<=bottom && left<=right){
            for(i=left; i<=right; i++){
                res.add(matrix[top][i]);
            }
            top++;
            for(i=top;i<=bottom;i++){
                res.add(matrix[i][right]);
            }
            right--;
            if (top <= bottom) {
            for(i=right;i>=left;i--){
                res.add(matrix[bottom][i]);
            }
            bottom--;
            }
            if (left <= right) {
            for(i=bottom; i>=top; i--){
                res.add(matrix[i][left]);
            }
            left++;
            }
        }
        return res;
    }
}

public class Spiral_matrix{
public static void main(String args[]){
    Solution sol=new Solution();
    List<Integer> result = new ArrayList<Integer>();
    result = sol.spiralOrder(new int[][] {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    });
    for(int i=0;i<result.size();i++){
       System.out.println(result.get(i));
    }
  }
}
