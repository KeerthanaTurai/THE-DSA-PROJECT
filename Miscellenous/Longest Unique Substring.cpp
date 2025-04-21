/*
Given a string, find the largest substring with unique characters.
Sample Input1:
keerthana
Sample Output1:
erthan
*/
#include <iostream>
#include<unordered_map>
#include<cstring>
using namespace std;

string unique_substring(string s){
    int i=0, j=0;
    int start_win=-1, curr_win_len=0, max_win_len=0;
    int n=s.length();
    unordered_map<char,int> hm;
    
    while(j<n){
        char ch = s[j];
        //if it is inside hm and its index >= start of the current window
        if(hm.count(ch) and hm[ch]>=i){
            i = hm[ch]+1;
            curr_win_len = j-i; 
        }
        
        //update the last occ
        hm[ch] = j;
        curr_win_len++;
        j++;
        
        //update max_win_len
        if(curr_win_len > max_win_len){
            max_win_len=curr_win_len;
            start_win=i;
        }
    }
    return s.substr(start_win,max_win_len);
}

int main()
{
    string s;
    cin>>s;
    cout<<unique_substring(s);
    return 0;
}
