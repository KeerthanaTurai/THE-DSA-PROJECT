#include<iostream>
#include<vector>
using namespace std;
int main()
{
	//vector<int> a;
	vector<int> a = {1,2,6,9};

    //fill constructor
	//vector<int> a(10,7); 

	//pop_back
	a.pop_back();

	//push_back 
	a.push_back(45);

	//Size of the vector (No.of elements)
	cout<<a.size()<<endl;

	//print all the elements
	for(int i=0;i<a.size();i++){
		cout<<a[i]<<endl;
	}
	//Capacity of the vector  
	cout<<a.capacity()<<endl;
	return 0;
}
