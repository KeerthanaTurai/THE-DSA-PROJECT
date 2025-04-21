/* 
Given an array of elements find all the sequences whose sum is equal to k
Sample Input:
11
1 3 2 1 4 1 3 2 1 1 2
8
Sample Output:
2 5 
4 6
5 9
*/
#include <iostream>
using namespace std;

void housing(int *arr, int n, int k){
    int i=0,j=0,cs=0;
    while (j<n){
        
        //move towards right
        cs += arr[j];
        j++;
        
        //remove elements from left if cs>k and i<j
        while (cs > k and i<j){
            cs -= arr[i];
            i++;
        }
        
        //checking if cs == k
        if (cs==k){
            cout<<i<<" "<<j-1<<endl;
        }
    }
    return;
}
int main()
{
    int n,k;
    //cout<<"Enter size: ";
    cin>>n;
    int arr[n];
    //cout<<"Enter array elements:\n";
    for(int i=0;i<n;i++){
        cin>>arr[i];
    }
    //cout<<"Enter k value: ";
    cin>>k;
    housing(arr,n,k);
    return 0;
}
