/**
 * The following code checks if the i-th bit of a number is set or not.
 * It uses bitwise operations to determine the status of the i-th bit.
 * The algorithm works as follows:
 * 1. Create a mask by left-shifting 1 by i positions (1 << i).
 * 2. Perform a bitwise AND operation between the number and the mask.
 * 3. If the result is not zero, it means the i-th bit is set; otherwise, it is not set.
 * This method is efficient and does not require any additional memory.
 * Note: This method only works for integer types and may not be suitable for floating-point numbers.
 * It is also important to note that this method may not be safe for all data types due to potential issues with signed integers.
 */
#include <iostream>
using namespace std;

bool check_ith_bit(int &num, int &i)
{
    if ((num & (1<<i)) != 0)
        return true;
    else
        return false; 
}
int main()
{
    int num, i;
    cout << "Enter number : ";
    cin >> num;
    cout << "Enter the bit you want to check : ";
    cin >> i;
    if (check_ith_bit(num, i))
        cout << "The " << i << "-th bit is set." << endl;
    else
        cout << "The " << i << "-th bit is not set." << endl;
}