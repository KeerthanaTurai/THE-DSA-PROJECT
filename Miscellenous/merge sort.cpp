#include<iostream>
#include<vector>
using namespace std;

void merge(vector<int> &arr,int s,int e){
    
    //as the array will be in two parts 
    //1st array start to mid
    //2nd array mid+1 to end
    int i=s, m=(s+e)/2, j=m+1;
    vector<int> temp;
    
    while(i<=m and j<=e){
        if (arr[i] < arr[j]){
            temp.push_back(arr[i]);
            i++;
        }
        else{
            temp.push_back(arr[j]);
            j++;
        }
    }
    
    //if first array has any remaining elements add them to temp
    while(i<=m){
        temp.push_back(arr[i]);
        i++;
    }
    
    //if second array has any remaining elements add them to temp
    while(j<=e){
        temp.push_back(arr[j]);
        j++;
    }
    
    //copy back the elements from temp to original arr
    int k=0;
    for(int i=s;i<=e;i++){
        arr[i]=temp[k++];
    }
    return;
}

void mergesort(vector<int> &arr,int s,int e){
    
    // base case
    if(s>=e){
        return;
    }
    
    //recursive case
    int mid=(s+e)/2;
    mergesort(arr,s,mid);
    mergesort(arr,mid+1,e);
    return merge(arr,s,e);
    
}

int main()
{
    vector<int> a{10,5,2,0,7,6,4};
    int s=0;
    int e=a.size()-1;
    mergesort(a,s,e);
    for(int i:a){
        cout<<i<<" ";
    }
    return 0;
}